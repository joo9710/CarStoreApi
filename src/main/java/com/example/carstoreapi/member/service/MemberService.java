package com.example.carstoreapi.member.service;

import com.example.carstoreapi.car.model.CarDAO;
import com.example.carstoreapi.member.model.MemberDAO;
import com.example.carstoreapi.member.model.MemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class MemberService {
    public final CarDAO carDAO;

    public final MemberDAO memberDAO;

    final PasswordEncoder passwordEncoder;


    public String putMember(long mid, MemberDTO memberDTO) throws Exception {
        memberDTO.setMid(mid);

        String encodedPassword = passwordEncoder.encode(memberDTO.getPassword());
        memberDTO.setPassword(encodedPassword);

        int putMember = memberDAO.putMember(memberDTO);
        int putAuthor = carDAO.putAuthor(memberDTO.getMid(), memberDTO.getNickName());


        if(putMember > 0 ) {
            return putAuthor + "member updated";
        }
        throw new Exception("failed to update " + mid + "content");
    }
}
