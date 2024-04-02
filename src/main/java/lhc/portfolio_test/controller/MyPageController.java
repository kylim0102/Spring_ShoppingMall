package lhc.portfolio_test.controller;

import lhc.portfolio_test.dto.CustomUserDetails;
import lhc.portfolio_test.dto.MyPageDTO;
import lhc.portfolio_test.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPageController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/my/mypage")
    public String myPage(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model) {
        String userid = customUserDetails.getUsername();
        MyPageDTO memberDTO = memberService.findByUserid(userid);

        model.addAttribute("memberDTO", memberDTO);

        return "/my/mypage";
    }

    @GetMapping("/my/buy")
    public String buyPage(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model) {
        String userid = customUserDetails.getUsername();

        return "/my/buy";
    }
}
