package com.example.carstoreapi.member.model;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberDAO {
     // 회원정보 변경
     int putMember(MemberDTO memberDTO);

     List getMemberInfo(long mid);

     // 멤버 패스워드 변경
     int putPwd(MemberDTO memberDTO);

     //닉네임 중복검사
     Boolean duplicateCheck(String nickName);

     //이메일 중복검사
     Boolean duplicateCheckEmail(String email);
}
