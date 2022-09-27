package com.example.carstoreapi.member.controller;

import com.example.carstoreapi.member.model.MemberDTO;
import com.example.carstoreapi.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/member")
public class MemberControllerMybatis {

    public final MemberService memberService;

    @PutMapping(value = "/{mid}")
    public String putMember(@PathVariable long mid,
                            @RequestBody MemberDTO memberDTO) throws Exception {
        return memberService.putMember(mid, memberDTO);
    }
}
