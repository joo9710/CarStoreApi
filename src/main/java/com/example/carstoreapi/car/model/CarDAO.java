package com.example.carstoreapi.car.model;

import com.example.carstoreapi.car.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Repository
public interface CarDAO {

    //글 읽기
    List<CarDTO> getCarList();

    //페이징 처리
    List<CarDTO> getPageCarList(Criteria criteria);

    //글 쓰기
    int postCar(CarDTO carDTO);

    //글 수정
    int putCar(CarDTO carDTO);

    //글 삭제
    int updateIsDelCarById(int carId);

    //carId 가져오기
    CarDTO getCarById(int carId);

    //조회수 증가
    int updateReadCount(int carId);

    //carId 1증가
    int maxCarId();

    //썸네일 업로드
    int thumbUpload(int carId, String thumb);
}
