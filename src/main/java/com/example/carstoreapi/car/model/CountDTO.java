package com.example.carstoreapi.car.model;


import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CountDTO {
    private int carId;
    private int commentCount;

    @Override
    public String toString() {
        return "CountDTO{" +
                "carId=" + carId +
                ", commentCount=" + commentCount +
                '}';
    }
}
