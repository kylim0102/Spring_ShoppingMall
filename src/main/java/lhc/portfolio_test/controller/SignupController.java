package lhc.portfolio_test.controller;

import lhc.portfolio_test.dto.RegisterDTO;
import lhc.portfolio_test.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SignupController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/signup")
    public String signupPage() {

        return "signup";
    }

    @PostMapping("/signupProc")
    public String signupProcess(RegisterDTO memberDTO) {

        memberService.Signup(memberDTO);

        return "/login";
    }

    @PostMapping("/idCheck")
    public @ResponseBody int idCheck(@RequestParam(name = "id") String id) {
        boolean exists = memberService.idCheck(id);
        if (exists) {
            return 0;
        } else {
            return 1;
        }
    }
}
