package lhc.portfolio_test.repository;

import lhc.portfolio_test.entity.ProductImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImgRepository extends JpaRepository<ProductImgEntity, Long> {
}
