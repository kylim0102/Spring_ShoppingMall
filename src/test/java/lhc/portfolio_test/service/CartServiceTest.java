package lhc.portfolio_test.service;

import lhc.portfolio_test.repository.ProductCategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class CartServiceTest {

    @Autowired CartService cartService;
    @Autowired ProductCategoryRepository productCategoryRepository;
    @Test
    void saveCartTest() {
        System.out.println(productCategoryRepository.findById(1L).get().getCategoryName());
    }


}