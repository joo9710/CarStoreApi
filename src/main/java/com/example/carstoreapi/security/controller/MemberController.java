package com.example.carstoreapi.security.controller;

import com.example.carstoreapi.member.Member;
import com.example.carstoreapi.member.MemberRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequiredArgsConstructor

public class MemberController {

    final MemberRepository memberRepository;
    final PasswordEncoder passwordEncoder;

    @PostMapping("/api/member")
    public String saveMember(@RequestBody MemberDto memberDto){
        memberRepository.save(Member.createMember(memberDto.getID(),
            passwordEncoder.encode(memberDto.getPassword())));
        return "success";
    }
}

@Data
class MemberDto{
    private String ID;
    private String password;
}