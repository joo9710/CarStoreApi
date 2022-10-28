package com.example.carstoreapi.car.controller;

import com.example.carstoreapi.car.Criteria;
import com.example.carstoreapi.car.model.*;
import com.example.carstoreapi.car.service.CarService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import com.example.carstoreapi.common.ApiResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    /*
    @GetMapping(value = "/pageList")
    public List<CarDTO> getPageCarList(Criteria criteria) {
        log.debug("criteria" + criteria);
            //int total = car.getTotalCar;
            return carService.getPageCarList(criteria);
    }

     */


    @GetMapping(value = "/pageList")
    public List<CarDTO> getPageCarList(Criteria criteria) {
        log.debug("criteria" + criteria);
        return carService.getPageCarList(criteria);
    }


    @PostMapping(value = "/page")
    public Page<Car> getPageList(@RequestBody Page2DTO page2DTO) {
        return carService.getPageList(page2DTO.getPage(), page2DTO.getSize());
    }

     @GetMapping(value = "/pageJPA")
    public Page<Car> pageCar(@RequestParam int page, @RequestParam int size) {
        log.debug(":"+page);
        return carService.pageCar(page,size);
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
                             @RequestParam("mid")Long mid,
                             @RequestParam("carName")String carName,
                             @RequestParam("national")String national,
                             @RequestParam("subCarName")String subCarName,
                             @RequestParam("year")String year,
                             @RequestParam("distance")String distance,
                             @RequestParam("fuel")String fuel,
                             @RequestParam("area")String area,
                             @RequestParam("price")String price,
                             @RequestParam("content")String content,
                             @RequestParam("thumb")MultipartFile multipartThumb,
                             @RequestParam("file") List<MultipartFile> multipartFile){


        CarDTO carDTO = new CarDTO();
        carDTO.setMid(mid);
        carDTO.setAuthor(author.replace("\"", ""));
        carDTO.setCarName(carName.replace("\"", ""));
        carDTO.setNational(national.replace("\"", ""));
        carDTO.setSubCarName(subCarName.replace("\"", ""));
        carDTO.setYear(year.replace("\"", ""));
        carDTO.setDistance(distance.replace("\"", ""));
        carDTO.setFuel(fuel.replace("\"", ""));
        carDTO.setArea(area.replace("\"", ""));
        carDTO.setPrice(price.replace("\"", ""));
        carDTO.setContent(content.replace("\"", ""));

       int newId = carService.postCar(carDTO);
       log.debug("test : " + newId);
       log.debug("Files : " + multipartFile);
       carService.saveThumb(newId, multipartThumb);
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

    //판매차량 개수
    @GetMapping(value = "/carCount/{mid}")
    public Long getCarCount(@PathVariable long mid) throws Exception {
        return carService.getCarCount(mid);
    }

    // 판매차량 가져오기 (댓글수 포함)
    @GetMapping(value="/myCar/{mid}")
    public List<CarDTO> getMyCar(@PathVariable long mid) throws Exception {
            log.debug("mid" + mid);
        return carService.getMyCar(mid);
    }

    /*//댓글수 전체 list
    @GetMapping(value= "/countCommentList")
    public List<CountDTO> getCountComment() {
        return carService.getCountComment();
    }
*/

    // 국산 or 수입차 list
    @GetMapping(value= "/national")
    public Page<Car> getNationalCar(@RequestParam String national,
                                    @RequestParam int page,
                                    @RequestParam int size) throws Exception {
        return carService.getNationalCar(national, page, size);
    }

    // 회사별 차량 조회
    @GetMapping(value= "/companies/{company}")
    public List<CarDTO> getCompanyOfCar(@PathVariable String company) throws Exception {
        return carService.getCompanyOfCar(company);
    }

    // 회사별 차량 조회 및 페이징
    @PostMapping(value = "/pageCompany")
    public Page<Car> getCompanyOfListPage(@RequestBody Page3DTO page3DTO) {
        return carService.getCompanyOfListPage(page3DTO.getCompany(), page3DTO.getPage(), page3DTO.getSize());
    }

    //검색 및 페이징
    @GetMapping(value = "/search")
    public Page<Car> getSearch(@RequestParam String category, @RequestParam String keyword, @RequestParam int page, @RequestParam int size) {
        log.debug(category);
        log.debug(keyword);
        log.debug(":"+page);
        return carService.getSearch(category, keyword, page, size);
    }


    // 회사별 차량 조회 및 페이징
    @GetMapping(value = "/pageCompany")
    public Page<Car> getCompanyOfListPage(@RequestParam String company,
                                          @RequestParam int page,
                                          @RequestParam int size) {
        return carService.getCompanyOfListPage(company, page, size);
    }

    //회사 선택시 지역 선택 조회 및 페이징
    @GetMapping(value="/pageCompanyOfArea")
    public Page<Car> getCompanyOfAreaList (@RequestParam String company,
                                           @RequestParam String area,
                                           @RequestParam int page,
                                           @RequestParam int size) {
        return carService.getCompanyOfAreaList(company, area, page, size);
    }

    // 판매중인 차량 모아보기
    @PostMapping (value = "/saleOfMyCar")
    public Page<Car> saleOfMyCar(@RequestBody Page3DTO page3DTO) {
        return carService.saleOfMyCar(page3DTO.getMid(), page3DTO.getPage(), page3DTO.getSize());
    }


//    @PostMapping(value = "/page")
//    public Page<Car> getPageList(@RequestBody Page2DTO page2DTO) {
//        return carService.getPageList(page2DTO.getPage(), page2DTO.getSize());
//    }


}
