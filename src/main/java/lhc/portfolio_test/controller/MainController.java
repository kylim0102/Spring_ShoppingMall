package lhc.portfolio_test.controller;

import lhc.portfolio_test.entity.CategoryEntity;
import lhc.portfolio_test.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {

    @Autowired
    private ProductCategoryRepository categoryRepository;

    @GetMapping("/")
    public String mainPage(Model model) {

        List<CategoryEntity> categories = categoryRepository.findAll();


        List<String> parentCategoryNames = categories.stream()
                .filter(category -> category.getParentCategory() == null)
                .map(CategoryEntity::getCategoryName)
                .distinct() // 중복 제거
                .collect(Collectors.toList());

        model.addAttribute("category", parentCategoryNames);

        // 자식 카테고리 이름 추출
        for (String parentCategoryName : parentCategoryNames) {
            List<String> childCategoryNames = categories.stream()
                    .filter(category -> category.getParentCategory() != null && category.getParentCategory().getCategoryName().equals(parentCategoryName))
                    .map(CategoryEntity::getCategoryName)
                    .distinct()
                    .collect(Collectors.toList());
            model.addAttribute(parentCategoryName + "SubCategories", childCategoryNames);
        }
        return "main";
    }
}
