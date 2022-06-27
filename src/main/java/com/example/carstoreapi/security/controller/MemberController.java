package com.example.carstoreapi.security.controller;

import com.example.carstoreapi.member.Member;
import com.example.carstoreapi.member.MemberRepository;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    final MemberRepository memberRepository;
    final PasswordEncoder passwordEncoder;

    @PostMapping("/api/member")
    public String saveMember(@RequestBody MemberDto memberDto){
        log.debug("de : " + memberDto);
        memberRepository.save(Member.createMember(memberDto.getEmail(),
                passwordEncoder.encode(memberDto.getPassword()), memberDto.getNickName(), memberDto.getArea(), memberDto.getPhoneNumber()));
        return "success";
    }
}

@Data
class MemberDto{
    private Long mid;
    private String email;
    private String password;
    private String nickName;
    private String area;
    private String phoneNumber;

}
