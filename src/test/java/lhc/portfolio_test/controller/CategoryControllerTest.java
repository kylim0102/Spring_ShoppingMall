package lhc.portfolio_test.controller;

import lhc.portfolio_test.entity.ProductEntity;
import lhc.portfolio_test.entity.ProductImgEntity;
import lhc.portfolio_test.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class CategoryControllerTest {

    @Autowired
    ProductService productService;

    @Test
    void imageTest() {

        String parentCategoryName = "TOP";

        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<ProductEntity> productPage = productService.findProductsByParentCategoryName(parentCategoryName, pageRequest);

        List<ProductEntity> content = productPage.getContent();
        for (ProductEntity productEntity : content) {
            System.out.println("productEntity = " + productEntity);
            List<ProductImgEntity> productImgEntityList = productEntity.getProductImgEntityList();
            System.out.println("productImgEntityList = " + productImgEntityList);

        }

    }

}