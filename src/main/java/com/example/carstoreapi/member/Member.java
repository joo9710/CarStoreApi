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
    private String email;
    private String password;
    private String nickName;
    private String area;
    private String phoneNumber;


    public Member(String email, String password, String nickName, String area , String phoneNumber) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.area = area;
        this.phoneNumber = phoneNumber;
    }

    public static Member createMember(String email, String password, String nickName, String area, String phoneNumber){
        return new Member(email, password, nickName, area, phoneNumber);
    }
}
