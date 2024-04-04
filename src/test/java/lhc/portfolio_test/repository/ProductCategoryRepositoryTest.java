package lhc.portfolio_test.repository;

import lhc.portfolio_test.entity.CategoryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class ProductCategoryRepositoryTest {

    @Autowired
    ProductCategoryRepository categoryRepository;
    @Test
    void test() {
        List<CategoryEntity> byParentCategoryIsNull = categoryRepository.findAllWhereParentIsNull();
        for (CategoryEntity category : byParentCategoryIsNull) {
            System.out.println("category = " + category);
        }
    }

}