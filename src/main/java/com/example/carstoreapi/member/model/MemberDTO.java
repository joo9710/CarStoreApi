package com.example.carstoreapi.member.model;

import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class MemberDTO {
    private long mid;
    private String fullName;
    private String email;
    private String password;
    private String nickName;
    private String area;
    private String phoneNumber;

    @Override
    public String toString() {
        return "MemberDTO{" +
                "mid=" + mid +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", area='" + area + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
