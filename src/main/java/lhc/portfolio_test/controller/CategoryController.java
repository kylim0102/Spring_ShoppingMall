package lhc.portfolio_test.controller;

import lhc.portfolio_test.dto.CategoryDTO;
import lhc.portfolio_test.entity.CategoryEntity;
import lhc.portfolio_test.entity.ProductEntity;
import lhc.portfolio_test.entity.ProductImgEntity;
import lhc.portfolio_test.repository.ProductCategoryRepository;
import lhc.portfolio_test.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
public class CategoryController {

    @Autowired
    private ProductService productService;
    @Autowired
    ProductCategoryRepository categoryRepository;

    @Transactional
    @GetMapping("/category/{parentCategoryName}")
    public String categoryPage(Model model, @PathVariable String parentCategoryName, @PageableDefault(page = 0, size = 3) Pageable pageable) {
        Page<ProductEntity> productPage = productService.findProductsByParentCategoryName(parentCategoryName, pageable);
        int blockLimit = 3;
        int startPage = ((int)Math.ceil((double)(pageable.getPageNumber() + 1) / blockLimit) - 1) * blockLimit + 1;
        int lastPage = startPage + blockLimit - 1;
        int endPage;
        int totalPage = productPage.getTotalPages();


        if(lastPage < totalPage) {
            endPage = lastPage;
        } else {
            endPage = totalPage;
        }

        model.addAttribute("productList", productPage);
        model.addAttribute("parentCategoryName", parentCategoryName);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", pageable.getPageNumber());

        for (ProductEntity product : productPage.getContent()) {
            Long productId = product.getIdx();
            List<ProductImgEntity> productImages = productService.findProductImages(productId);
            if (!productImages.isEmpty()) {
                model.addAttribute("productImages", productImages.get(0).getS_file());
            }
        }
        return "/productlist";
    }

    @Transactional
    @GetMapping("/category/{parentCategoryName}/{childCategoryName}")
    public String childCategoryPage(Model model, @PathVariable String parentCategoryName, @PathVariable String childCategoryName, @PageableDefault(page = 0, size = 5) Pageable pageable) {
        Page<ProductEntity> productPage = productService.findProductsByChildCategoryName(childCategoryName, pageable);
        int blockLimit = 3;
        int startPage = ((int)Math.ceil((double)(pageable.getPageNumber() + 1) / blockLimit) - 1) * blockLimit + 1;
        int lastPage = startPage + blockLimit - 1;
        int endPage;
        int totalPage = productPage.getTotalPages();


        if(lastPage < totalPage) {
            endPage = lastPage;
        } else {
            endPage = totalPage;
        }
        model.addAttribute("productList", productPage.getContent());
        model.addAttribute("parentCategoryName", parentCategoryName);
        model.addAttribute("childCategoryName", childCategoryName);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", pageable.getPageNumber());

        for (ProductEntity product : productPage.getContent()) {
            Long productId = product.getIdx();
            List<ProductImgEntity> productImages = productService.findProductImages(productId);
            if (!productImages.isEmpty()) {
                model.addAttribute("productImages", productImages.get(0).getS_file());
            }
        }
        return "/productlist";
    }

    @GetMapping ("/api/categories")
    @ResponseBody
    public List<CategoryDTO> parentCategory () {
        List<CategoryEntity> allWhereParentIsNull = categoryRepository.findAllWhereParentIsNull();
        List<CategoryDTO> dto = new ArrayList<>();
        for (CategoryEntity category : allWhereParentIsNull) {
            dto.add(new CategoryDTO(category.getIdx(), category.getCategoryName()));
        }
        return dto;
    }
}