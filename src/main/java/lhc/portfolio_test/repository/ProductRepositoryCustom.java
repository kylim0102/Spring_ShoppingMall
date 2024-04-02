package lhc.portfolio_test.repository;

import lhc.portfolio_test.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {

    Page<ProductEntity> findProductsByParentCategoryName(String categoryName, Pageable pageable);

    Page<ProductEntity> findAllFetch(Pageable pageable);

    Page<ProductEntity> findProductsByChildCategoryName(String childCategoryName, Pageable pageable);

}