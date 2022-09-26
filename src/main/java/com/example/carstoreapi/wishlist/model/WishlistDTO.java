package com.example.carstoreapi.wishlist.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class WishlistDTO {
    private long wid;
    private long mid;
    private int carId;
    private String carName;
    private String subCarName;
    private String year;
    private String distance;
    private String area;
    private String price;
    private String thumb;

    @Override
    public String toString() {
        return "WishlistDTO{" +
                "wid=" + wid +
                ", mid=" +  mid+
                ", carId=" + carId +
                ", carName=" + carName + '\'' +
                ", subCarName=" + subCarName + '\'' +
                ", year=" + year + '\'' +
                ", distance=" + distance + '\'' +
                ", area=" + area + '\'' +
                ", price=" + price + '\'' +
                ", thumb=" + thumb + '\'' +
                '}';
    }
}
