package com.example.carstoreapi.car.model;

import org.springframework.stereotype.Repository;

@Repository
public interface FileDAO {

    //파일 업로드
    int fileUpload(FileDTO data);

    //파일 읽기
    FileDTO getFileByCarId(int carId);

}
