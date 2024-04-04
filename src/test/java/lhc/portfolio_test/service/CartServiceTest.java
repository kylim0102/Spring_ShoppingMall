package lhc.portfolio_test.service;

import lhc.portfolio_test.entity.CartEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CartServiceTest {

    @Autowired CartService cartService;

    @Test
    void saveCartTest() {
        CartEntity cartEntity = cartService.saveCart("TEST USERID", 1L, 2);

        System.out.println("categoryIdx = " + cartEntity.getIdx());
        System.out.println("categoryUsername = " + cartEntity.getUsername());
        System.out.println("categoryQuantity = " + cartEntity.getQuantity());
        System.out.println("productName = " + cartEntity.getCartProducts().getP_name());

    }


}