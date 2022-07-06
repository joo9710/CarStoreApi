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
                ", mid=" + mid + '\'' +
                ", author=" + author + '\'' +
                ", carName=" + carName + '\'' +
                ", year=" + year + '\'' +
                ", distance=" + distance + '\'' +
                ", fuel=" + fuel + '\'' +
<<<<<<< HEAD
                ", price=" + price + '\'' +
=======
                ", area=" + area + '\'' +
>>>>>>> Vue
                ", content=" + content + '\'' +
                ", writeDate=" + writeDate + '\'' +
                ", writeTime=" + writeTime + '\'' +
                ", readCount=" + readCount + '\'' +
                ", readCount=" + isDel +
                ", thumb=" + thumb +
                '}';
    }
}

