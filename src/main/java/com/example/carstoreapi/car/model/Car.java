package com.example.carstoreapi.car.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Table(name = "car")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "carId")
    private Integer carId;

    @Column(name = "mid")
    private Long mid;
    @Column(name = "author")
    private String author;
    @Column(name = "carName")
    private String carName;
    @Column(name = "subCarName")
    private String subCarName;

    @Column(name = "year")
    private String year;
    @Column(name = "distance")
    private String distance;
    @Column(name = "fuel")
    private String fuel;
    @Column(name = "area")
    private String area;
    @Column(name = "price")
    private String price;
    @Column(name = "content")
    private String content;
    @Column(name = "writeDate")
    private LocalDate writeDate;
    @Column(name = "writeTime")
    private LocalTime writeTime;
    @Column(name = "readCount")
    private Integer readCount;
    @Column(name = "isDel")
    private String isDel;
    @Column(name = "thumb")
    private String thumb;


    public Car(Integer carId,
                 Long mid,
                 String author,
                 String carName,
                 String subCarName,
                 String year,
                 String distance,
                 String fuel,
                 String area,
                 String price,
                 String content,
                 LocalDate writeDate,
                 LocalTime writeTime,
                 Integer readCount,
                 String isDel,
                 String thumb
                    ) {
        this.carId = carId;
        this.mid = mid;
        this.author = author;
        this.carName = carName;
        this.subCarName = subCarName;
        this.year = year;
        this.distance = distance;
        this.fuel = fuel;
        this.area = area;
        this.price = price;
        this.content = content;
        this.writeDate = writeDate;
        this.writeTime = writeTime;
        this.readCount = readCount;
        this.isDel = isDel;
        this.thumb = thumb;
    }
}

