package lhc.portfolio_test.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lhc.portfolio_test.entity.CartEntity;
import lhc.portfolio_test.entity.ProductEntity;
import lhc.portfolio_test.entity.QProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

import static lhc.portfolio_test.entity.QCartEntity.cartEntity;
import static lhc.portfolio_test.entity.QProductEntity.productEntity;

public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;
    private final ProductCategoryRepository categoryRepository;

    @Autowired
    public ProductRepositoryImpl(EntityManager entityManager, ProductCategoryRepository categoryRepository) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<ProductEntity> findAllFetch(Pageable pageable) {
        QueryResults<ProductEntity> results = queryFactory
                .selectFrom(productEntity)
                .leftJoin(productEntity.productImgEntityList).fetchJoin() // productImgEntityList를 함께 로딩합니다.
                .leftJoin(productEntity.category).fetchJoin() // category도 함께 로딩합니다.
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    @Override
    public Page<ProductEntity> findProductsByParentCategoryName(String parentCategoryName, Pageable pageable) {
        // 부모 카테고리의 idx 값을 찾습니다.
        Long parentCategoryId = categoryRepository.findCategoryIdByCategoryName(parentCategoryName);

        QProductEntity qProduct = QProductEntity.productEntity;
        BooleanExpression predicate;
        if (parentCategoryId != null) {
            predicate = qProduct.category.parentCategory.idx.eq(parentCategoryId);
        } else {
            predicate = qProduct.category.parentCategory.isNull();
        }

        // 찾은 idx 값을 이용하여 상품 테이블에서 해당 부모 카테고리에 속하는 상품들을 뽑아옵니다.
        QueryResults<ProductEntity> results = queryFactory.selectFrom(productEntity)
                .join(productEntity.category).fetchJoin()
                .where(predicate)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    @Override
    public Page<ProductEntity> findProductsByChildCategoryName(String childCategoryName, Pageable pageable) {
        // 자식 카테고리의 idx 값을 찾습니다.
        Long childCategoryId = categoryRepository.findCategoryIdByCategoryName(childCategoryName);

        QProductEntity qProduct = QProductEntity.productEntity;
        BooleanExpression predicate;
        if (childCategoryId != null) {
            predicate = qProduct.category.idx.eq(childCategoryId); // 하위 카테고리의 idx 값으로 검색합니다.
        } else {
            // 찾지 못한 경우 빈 결과를 반환합니다.
            return new PageImpl<>(Collections.emptyList());
        }

        // 찾은 idx 값을 이용하여 상품 테이블에서 해당 하위 카테고리에 속하는 상품들을 뽑아옵니다.
        QueryResults<ProductEntity> results = queryFactory.selectFrom(productEntity)
                .join(productEntity.category).fetchJoin()
                .where(predicate)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    @Override
    public List<CartEntity> findAllWithProduct () {

        return queryFactory.selectFrom(cartEntity)
                .leftJoin(cartEntity.cartProducts, productEntity).fetchJoin()
                .fetch();
    }
}
