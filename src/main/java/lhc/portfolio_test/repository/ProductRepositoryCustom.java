package lhc.portfolio_test.repository;

import lhc.portfolio_test.entity.CartEntity;
import lhc.portfolio_test.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepositoryCustom {

    Page<ProductEntity> findProductsByParentCategoryName(String categoryName, Pageable pageable);

    Page<ProductEntity> findAllFetch(Pageable pageable);

    Page<ProductEntity> findProductsByChildCategoryName(String childCategoryName, Pageable pageable);

    List<CartEntity> findAllWithProduct();

}