package lhc.portfolio_test.repository;

import lhc.portfolio_test.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository <CartEntity, Long> {
    List<CartEntity> findByUsername(String username);
}
