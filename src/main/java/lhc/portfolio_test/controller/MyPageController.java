package lhc.portfolio_test.controller;

import lhc.portfolio_test.dto.CartDTO;
import lhc.portfolio_test.dto.CustomUserDetails;
import lhc.portfolio_test.dto.MyPageDTO;
import lhc.portfolio_test.repository.CartRepository;
import lhc.portfolio_test.repository.ProductRepository;
import lhc.portfolio_test.service.CartService;
import lhc.portfolio_test.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MyPageController {

    @Autowired
    private MemberService memberService;
    private CartService cartService;
    private CartRepository cartRepository;
    private ProductRepository productRepository;

    public MyPageController(MemberService memberService, CartService cartService, CartRepository cartRepository, ProductRepository productRepository) {
        this.memberService = memberService;
        this.cartService = cartService;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/my/mypage")
    public String myPage(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model) {
        String userid = customUserDetails.getUsername();
        MyPageDTO memberDTO = memberService.findByUserid(userid);

        model.addAttribute("memberDTO", memberDTO);

        return "/my/mypage";
    }

    @Transactional
    @GetMapping("/my/cart")
    public String cartPage(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                           @RequestParam(value = "product_idx", required = false) Long idx, @RequestParam(value = "quantity", required = false) Integer quantity,
                           Model model) {
        String userid = customUserDetails.getUsername();

        if(idx != null && quantity != null && idx != 0 && quantity != 0) {
            cartService.saveCart(userid, idx, quantity);
        }
        List<CartDTO> cart = cartService.findByUserId(userid);

        model.addAttribute("dto", cart);
        return "/my/cart";
    }
}
