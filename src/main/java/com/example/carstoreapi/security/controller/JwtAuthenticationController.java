package com.example.carstoreapi.security.controller;

import com.example.carstoreapi.config.JwtTokenUtil;
import com.example.carstoreapi.member.Member;
import com.example.carstoreapi.security.service.JwtUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class JwtAuthenticationController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        log.debug("login : " + authenticationRequest.getEmail() + " , " + authenticationRequest.getPassword());
        final Member member = userDetailsService
                .authenticateByEmailAndPassword(authenticationRequest.getEmail(),
                                                authenticationRequest.getPassword());
        final String token = jwtTokenUtil.generateToken(member.getEmail());
        return ResponseEntity.ok(new JwtResponse(token));
    }
}

@Data
class JwtRequest {
    private String email;
    private String password;
}

@Data
@AllArgsConstructor
class JwtResponse {
    private String token;
}
