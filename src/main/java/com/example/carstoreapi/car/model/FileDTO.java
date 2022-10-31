package com.example.carstoreapi.car.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FileDTO {
    private int fid;
    private int carId;
    private String fileName;

    @Override
    public String toString() {
        return "FileDTO{" +
                "fid" + fid +
                ",carId=" + carId + '\'' +
                ",fileName=" + fileName +
                '}';
    }

}
