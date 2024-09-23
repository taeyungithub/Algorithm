package nhn.academy.controller;

import nhn.academy.model.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    @GetMapping("/name")
    public String getName(){
        return "신건영";
    }

    @GetMapping("/me")
    public Member getMe(){
        return new Member("신건영", 20);
    }
}
