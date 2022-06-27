package com.example.carstoreapi.car.model;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarDAO {

    //글 읽기
    List<CarDTO> getCarList();

    //글 쓰기
    int postCar(CarDTO carDTO);

    //글 수정
    int putCar(CarDTO carDTO);

    //글 삭제
    int updateIsDelCarById(int carId);

    //carId 가져오기
    CarDTO getCarById(int carId);

}
