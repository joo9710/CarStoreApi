package com.example.carstoreapi.member.service;

import com.example.carstoreapi.car.model.CarDAO;
import com.example.carstoreapi.member.model.MemberDAO;
import com.example.carstoreapi.member.model.MemberDTO;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class MemberService {
    public final CarDAO carDAO;

    public final MemberDAO memberDAO;

    final PasswordEncoder passwordEncoder;

    //회원정보 수정(닉네임, 연락처)
    public String putMember(long mid, MemberDTO memberDTO) throws Exception {
        memberDTO.setMid(mid);

        int putMember = memberDAO.putMember(memberDTO);
        int putAuthor = carDAO.putAuthor(memberDTO.getMid(), memberDTO.getNickName());


        if(putMember > 0 ) {
            return putAuthor + "member updated";
        }
        throw new Exception("failed to update " + mid + "content");
    }

    //회원정보 불러오기
    public List<MemberDTO> getMemberInfo(long mid) throws Exception {
        return memberDAO.getMemberInfo(mid);
    }

    //비밀번호 수정
    public String putPwd(long mid, MemberDTO memberDTO) throws Exception{
        memberDTO.setMid(mid);

        String encodedPassword = passwordEncoder.encode(memberDTO.getPassword());
        memberDTO.setPassword(encodedPassword);

        int putMemberPwd = memberDAO.putPwd(memberDTO);
        if(putMemberPwd > 0) {
            return putMemberPwd + "member Password updated.";
        }
        throw new Exception("failed to update " + mid + "content");
    }

    //닉네임 중복체크
    public Boolean duplicateCheck(String nickName) throws Exception {
        return memberDAO.duplicateCheck(nickName);
    }

    //이메일 중복체크
    public Boolean duplicateCheckEmail(String email) throws Exception {
        return memberDAO.duplicateCheckEmail(email);
    }
}
