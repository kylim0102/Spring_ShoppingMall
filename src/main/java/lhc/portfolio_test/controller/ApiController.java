package lhc.portfolio_test.controller;

import lhc.portfolio_test.dto.SubCategoryDTO;
import lhc.portfolio_test.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {
    @Autowired
    private ProductService productService; // 서브 카테고리 정보를 관리하는 서비스

    @GetMapping("/api/subcategories")
    public List<SubCategoryDTO> getSubCategories(@RequestParam("parentIdx") Long parentIdx) {
        // categoryId에 해당하는 서브 카테고리 목록을 조회하는 서비스 메소드 호출
        return productService.findSubCategoriesByParentIdx(parentIdx);
    }
}
