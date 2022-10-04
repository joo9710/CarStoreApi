package com.example.carstoreapi.member.controller;

import com.example.carstoreapi.member.Member;
import com.example.carstoreapi.member.model.MemberDTO;
import com.example.carstoreapi.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/member")
public class MemberControllerMybatis {

    public final MemberService memberService;

    //회원정보 수정(닉네임, 연락처)
    @PutMapping(value = "/{mid}")
    public String putMember(@PathVariable long mid,
                            @RequestBody MemberDTO memberDTO) throws Exception {
        return memberService.putMember(mid, memberDTO);
    }


    @GetMapping(value = "/{mid}")
    public List<MemberDTO> getMemberInfo(@PathVariable long mid) throws Exception {
        return memberService.getMemberInfo(mid);
    }

    //패스워드 변경
    @PutMapping(value = "/pwd/{mid}")
    public String putPwd(@PathVariable long mid,
                         @RequestBody MemberDTO memberDTO) throws Exception {
        return memberService.putPwd(mid, memberDTO);
    }

    //닉네임 중복확인
    @GetMapping(value = "/check/{nickName}")
    public Boolean duplicateCheck(@PathVariable String nickName) throws  Exception {
        return memberService.duplicateCheck(nickName);
    }

    @GetMapping(value = "/checkEmail/{email}")
    public Boolean duplicateCheckEmail(@PathVariable String email) throws Exception {
        return memberService.duplicateCheckEmail(email);
    }
}
