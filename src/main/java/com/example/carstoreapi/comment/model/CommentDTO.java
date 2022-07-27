package com.example.carstoreapi.comment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CommentDTO {
    private int cid;
    private int carId;
    private long mid;
    private String author;
    private String content;
    private LocalDate writeDate;
    private LocalTime writeTime;
    private String isDel;

    @Override
    public String toString() {
        return "CommentDTO{" +
                "cid=" + cid +
                ", carId=" +  carId+
                ", mid=" + mid +
                ", author=" + author + '\'' +
                ", content=" + content + '\'' +
                ", writeDate=" + writeDate +
                ", writeTime=" + writeTime +
                '}';
    }
}
