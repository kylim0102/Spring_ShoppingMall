package lhc.portfolio_test.controller;

import lhc.portfolio_test.dto.MemberDTO;
import lhc.portfolio_test.dto.ProductDTO;
import lhc.portfolio_test.entity.CategoryEntity;
import lhc.portfolio_test.service.MemberService;
import lhc.portfolio_test.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private ProductService productService;

    @GetMapping("/admin/memberlist")
    public String memberList(Model model) {
        List<MemberDTO> members = memberService.findAll();
        model.addAttribute("memberList", members);
        return "/admin/memberlist";
    }

    @GetMapping("/admin/write")
    public String writeProduct() {
        return "/admin/write";
    }

    @PostMapping("/admin/write")
    public String writeProductProc(@ModelAttribute ProductDTO productDTO) throws IOException {

        productService.write(productDTO);

        return "redirect:/";
    }

    @GetMapping("/admin/categorywrite")
    public String categorywrite() {
        return "/admin/categorywrite";
    }

    @PostMapping("/admin/categorywrite")
    public String addSubCategory(@RequestParam(name = "parentCategory", required = false) Long parentId, @RequestParam(name = "subCategories") String subCategoryName) {
        if(parentId == null) {
            // 부모 카테고리만 저장하는 메소드 호출
            productService.save(new CategoryEntity(subCategoryName));
        } else {
            // 서브카테고리를 저장하는 메소드 호출
            productService.saveSubCategory(parentId, new CategoryEntity(subCategoryName));
        }
        return "/main";
    }
}