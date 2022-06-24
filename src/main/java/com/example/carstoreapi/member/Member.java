package com.example.carstoreapi.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(unique = true)
    private String ID;
    private String password;

    public Member(String ID, String password) {
        this.ID = ID;
        this.password = password;
    }

    public static Member createMember(String ID, String password) {
        return new Member(ID, password);
    }
}
