package com.example.carstoreapi.car.controller;

import com.example.carstoreapi.car.model.CarDTO;
import com.example.carstoreapi.car.model.FileDTO;
import com.example.carstoreapi.car.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import com.example.carstoreapi.common.ApiResponse;
import org.springframework.web.multipart.MultipartFile;

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

     //   CarDTO dto = carService.postCar(carDTO);
      //  return new ApiResponse(true, dto);
        return null;
    }

    // 글 및 파일 등록
    @SneakyThrows
    @PostMapping("/img")
    public String uploadFile(@RequestParam("author")String author,
                             @RequestParam("carName")String carName,
                             @RequestParam("year")String year,
                             @RequestParam("distance")String distance,
                             @RequestParam("fuel")String fuel,
                             @RequestParam("area")String area,
                             @RequestParam("price")String price,
                             @RequestParam("content")String content,
                             @RequestParam("file")MultipartFile multipartFile){


        CarDTO carDTO = new CarDTO();
        carDTO.setAuthor(author.replace("\"", ""));
        carDTO.setCarName(carName.replace("\"", ""));
        carDTO.setYear(year.replace("\"", ""));
        carDTO.setDistance(distance.replace("\"", ""));
        carDTO.setFuel(fuel.replace("\"", ""));
        carDTO.setArea(area.replace("\"", ""));
        carDTO.setPrice(price.replace("\"", ""));
        carDTO.setContent(content.replace("\"", ""));

       int newId = carService.postCar(carDTO);
       log.debug("test : " + newId);

       return carService.saveFile(newId, multipartFile);

    }

    // 파일 읽기
    @GetMapping(value = "/showFile/{carId}")
    public ApiResponse<FileDTO> getFileByCarId(@PathVariable int carId) throws Exception {
        log.debug("FileData: " + carId);
        return carService.getFileByCarId(carId);
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
