package com.example.carstoreapi.member;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mid")
    private Long mid;

    @Column(unique = true)
    private String fullName;
    private String email;
    private String password;
    private String nickName;
    private String area;
    private String phoneNumber;


    public Member(String fullName, String email, String password, String nickName, String area , String phoneNumber) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.area = area;
        this.phoneNumber = phoneNumber;
    }

    public static Member createMember(String fullName, String email, String password, String nickName, String area, String phoneNumber){
        return new Member(fullName, email, password, nickName, area, phoneNumber);
    }
}
