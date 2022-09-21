package com.example.carstoreapi.car.model;


import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CarDTO {
    private int carId;
    private long mid;
    private String author;
    private String carName;
    private String subCarName;
    private String year;
    private String distance;
    private String fuel;
    private String area;
    private String price;
    private String content;
    private LocalDate writeDate;
    private LocalTime writeTime;
    private int readCount;
    private String isDel;
    private String thumb;

    @Override
    public String toString() {
        return "CarDTO{" +
                "carId" + carId +
                ", mid=" + mid +
                ", author=" + author + '\'' +
                ", carName=" + carName + '\'' +
                ", subCarName=" + subCarName + '\'' +
                ", year=" + year + '\'' +
                ", distance=" + distance + '\'' +
                ", fuel=" + fuel + '\'' +
                ", price=" + price + '\'' +
                ", area=" + area + '\'' +
                ", content=" + content + '\'' +
                ", writeDate=" + writeDate + '\'' +
                ", writeTime=" + writeTime + '\'' +
                ", readCount=" + readCount + '\'' +
                ", readCount=" + isDel +
                ", thumb=" + thumb +
                '}';
    }
}


