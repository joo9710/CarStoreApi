package com.example.carstoreapi.car.controller;

import com.example.carstoreapi.car.model.CarDTO;
import com.example.carstoreapi.car.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import com.example.carstoreapi.common.ApiResponse;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/car")
public class CarController {

    public final CarService carService;

    //글 읽기
    @GetMapping(value = "/")
    public List<CarDTO> getCarList(){
        log.debug("car");
        return carService.getCarList();
    }

    //{carId} 글 읽기
    @GetMapping(value = "/{carId}")
    public ApiResponse<CarDTO> getCarById(@PathVariable int carId) throws Exception {
        log.debug("data" + carId);
        return carService.getCarById(carId);
    }

    //글 등록
    @PostMapping(value = "/")
    public ApiResponse<CarDTO> postCar(@RequestBody CarDTO carDTO) throws Exception{

        CarDTO dto = carService.postCar(carDTO);
        return new ApiResponse(true, dto);
    }

    //글 수정
    @PutMapping(value = "/{carId}")
    public String putCar(@PathVariable int carId,
                         @RequestBody CarDTO carDTO) throws Exception {
        return carService.putCar(carId, carDTO);
    }

    //글 삭제
    @DeleteMapping(value = "/{carId}")
    public ApiResponse<CarDTO> updateIsDelCarById(@PathVariable int carId) throws  Exception {
        return carService.updateIsDelCarById(carId);
    }
}
