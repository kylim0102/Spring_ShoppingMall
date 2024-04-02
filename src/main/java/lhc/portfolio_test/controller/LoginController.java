package lhc.portfolio_test.controller;

import lhc.portfolio_test.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
