package com.example.carstoreapi.car.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Getter
@Setter
public class CarDTO {
    private int carId;
    private String mid;
    private String author;
    private String carName;
    private String content;
    private LocalDate writeDate;
    private LocalTime writeTime;
    private int readCount;
    private String isDel;

    @Override
    public String toString() {
        return "CarDTO{" +
                "carId" + carId +
                ", mid=" + mid + '\'' +
                ", author=" + author + '\'' +
                ", carName=" + carName + '\'' +
                ", content=" + content + '\'' +
                ", writeDate=" + writeDate + '\'' +
                ", writeTime=" + writeTime + '\'' +
                ", readCount=" + readCount + '\'' +
                ", readCount=" + isDel +
                '}';
    }
}
