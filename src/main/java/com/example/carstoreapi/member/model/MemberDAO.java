package com.example.carstoreapi.member.model;

import org.springframework.stereotype.Repository;

@Repository
public interface MemberDAO {

     int putMember(MemberDTO memberDTO);
}
