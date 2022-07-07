package com.example.carstoreapi.security.service;

import com.example.carstoreapi.member.Member;
import com.example.carstoreapi.member.MemberRepository;
import com.example.carstoreapi.member.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MemberRepository memberRepository;

    @Override
    // email,password가져오기 및 권한부여
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(Role.USER.getValue()));
        if(email.equals("superuser@email.com")){
            grantedAuthorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        }
        return new User(member.getEmail(), member.getPassword(), grantedAuthorities);
    }

    //패스워드 일치 검사
    public Member authenticateByEmailAndPassword(String email, String password){
        Member member = memberRepository.findByEmail(email)                     // memberRepository의 findByEmail에 넣어서 Member가 있는지 확인
                .orElseThrow(() -> new UsernameNotFoundException(email));
        if(!passwordEncoder.matches(password, member.getPassword())){
            throw new BadCredentialsException("Password not matched");
        }
        log.debug("login");
        return member;
    }
}
