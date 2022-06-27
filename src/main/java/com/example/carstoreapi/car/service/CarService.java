package com.example.carstoreapi.car.service;

import com.example.carstoreapi.car.model.CarDAO;
import com.example.carstoreapi.car.model.CarDTO;
import com.example.carstoreapi.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class CarService {

    public final CarDAO carDAO;

    //글 읽기
    public List<CarDTO> getCarList() {

        return carDAO.getCarList();
    }

    //{carId} 글 읽기
    public ApiResponse<CarDTO> getCarById(int carId) {
        CarDTO data = carDAO.getCarById(carId);
        carDAO.updateReadCount(carId);
        return new ApiResponse(true, data);
    }
    //글 쓰기
    public CarDTO postCar(CarDTO carDTO) throws Exception {

        carDTO.setWriteDate(LocalDate.now());
        carDTO.setWriteTime(LocalTime.now());

        int insertedRowCount = carDAO.postCar(carDTO);
        if(insertedRowCount > 0){
            return carDTO;
        }   else {
            throw new Exception("failed to insert car data");
        }
    }

    //글 수정
    public String putCar(int carId, CarDTO carDTO) throws Exception {
        carDTO.setCarId(carId);
        carDTO.setWriteDate(LocalDate.now());
        carDTO.setWriteTime(LocalTime.now());

        int result = carDAO.putCar(carDTO);

        if(result > 0 ){
            return result + " rows updated";
        }
        throw new Exception("failed to update " + carId + " content");
    }

    //글 삭제
    public ApiResponse<CarDTO> updateIsDelCarById(int carId) {
        int updateRow = carDAO.updateIsDelCarById(carId);

        if (updateRow > 0) {
            return new ApiResponse(true, "car id" + carId + "is successfully deleted");
        }
        return new ApiResponse(false, "failed to delete board id ");
    }
}
