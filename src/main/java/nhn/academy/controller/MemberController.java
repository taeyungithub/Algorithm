package nhn.academy.controller;

import nhn.academy.model.*;
import nhn.academy.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/accounts")
    public ResponseEntity addMember(@RequestBody MemberCreateCommand memberCreateCommand) {
        memberService.createMember(memberCreateCommand);
        System.out.println(memberCreateCommand);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/accounts")
    public List<Member> getMembers() {
        return memberService.getMembers();
    }

    @GetMapping("/accounts/{memberId}")
    public Member getMembers(@PathVariable String memberId) {
        return memberService.getMember(memberId);
    }

    @PutMapping("/accounts/{memberId}")
    public Member updateMember(@PathVariable String memberId,@RequestBody MemberCreateCommand memberCreateCommand) {
        return memberService.updateMember(memberId, memberCreateCommand);
    }

    @DeleteMapping("/accounts/{memberId}")
    public void deleteMember(@PathVariable String memberId) {
        memberService.deleteMember(memberId);
    }
}
