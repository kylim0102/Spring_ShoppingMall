package lhc.portfolio_test.controller;

import lhc.portfolio_test.dto.CartDTO;
import lhc.portfolio_test.dto.CustomUserDetails;
import lhc.portfolio_test.dto.MyPageDTO;
import lhc.portfolio_test.service.CartService;
import lhc.portfolio_test.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyPageController {

    @Autowired
    private MemberService memberService;
    private CartService cartService;

    @GetMapping("/my/mypage")
    public String myPage(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model) {
        String userid = customUserDetails.getUsername();
        MyPageDTO memberDTO = memberService.findByUserid(userid);

        model.addAttribute("memberDTO", memberDTO);

        return "/my/mypage";
    }

    @GetMapping("/my/cart")
    public String cartPage(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                           @RequestParam("product_idx") Long idx, @RequestParam("quantity") Integer quantity,
                           Model model) {
        String userid = customUserDetails.getUsername();

        if(idx != null && quantity != null && idx != 0 && quantity != 0) {
            CartDTO dto = CartService.savecart(userid, idx, quantity);
        }

        MyPageDTO dto = memberService.findByUserid(userid);
        model.addAttribute("dto", dto);
        return "/my/cart";
    }
}
