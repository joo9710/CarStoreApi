package com.example.carstoreapi.car.service;

import com.example.carstoreapi.car.Criteria;
import com.example.carstoreapi.car.model.*;
import com.example.carstoreapi.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Slf4j
public class CarService {
    public final CarRepository carRepository;

    public final CarDAO carDAO;

    public final FileDAO fileDAO;

    @Value("${uploadImg.path}")
    private String uploadFolder;

    @Value("${uploadThumb.path}")
    private String uploadThumbFolder;

    //글 읽기
    public List<CarDTO> getCarList() {

        return carDAO.getCarList();
    }

    //페이징 처리 Page<CarDTO>
    /*
    public List<CarDTO> getPageCarList(Criteria criteria) {
            int total = carDAO.getTotalCar();
        PageDTO pageDTO = new PageDTO(criteria, total);
        log.debug("pageDTO " + pageDTO);
        log.debug("totalCar" + total);

        return carDAO.getPageCarList(criteria);
    }

     */


    public List<CarDTO> getPageCarList(Criteria criteria) {
            int total = carDAO.getTotalCar();

        return carDAO.getPageCarList(criteria);
    }



    public Page<Car> getPageList(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return carRepository.findCarsByIsDel("N", pageRequest);
    }

    public Page<Car> getSearch(String category, String keyword, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        log.debug(category);
        if (Objects.equals(category, "차량명")) {
            return carRepository.findCarByCarNameContainingAndIsDel(keyword ,"N", pageRequest);
        }

         else if (Objects.equals(category, "지역")){
            log.debug("hi");
            return carRepository.findCarByAreaContainingAndIsDel(keyword,"N", pageRequest);
        }

         else if(Objects.equals(category, "작성자")) {
            return carRepository.findCarByAuthorContainingAndIsDel(keyword, "N", pageRequest);
        }

         return null;
    }


    public Page<Car> pageCar(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return carRepository.findCarsByIsDel("N", pageRequest);
    }


    //{carId} 글 읽기
    public ApiResponse<CarDTO> getCarById(int carId) {
        CarDTO data = carDAO.getCarById(carId);
        carDAO.updateReadCount(carId);
        return new ApiResponse(true, data);
    }

    //글 쓰기
    public Integer postCar(CarDTO carDTO) throws Exception {

        carDTO.setWriteDate(LocalDate.now());
        carDTO.setWriteTime(LocalTime.now());
        carDTO.setCarId(getNewCarIdValue());

        int insertedRowCount = carDAO.postCar(carDTO);
        if(insertedRowCount > 0){
            return carDTO.getCarId();
        }   else {
            throw new Exception("failed to insert car data");
        }
    }

    //파일 등록
    public String saveFile(int carId, MultipartFile multipartFile) {

        FileDTO fileDTO = new FileDTO();
        String imgFileName = carId + "carId" + multipartFile.getOriginalFilename();
        Path imgFilePath = Paths.get(uploadFolder + imgFileName);

        if (multipartFile.getSize() != 0) {
            try {
                Files.write(imgFilePath, multipartFile.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
            fileDTO.setCarId(carId);
            fileDTO.setFileName(imgFileName);
            int result = fileDAO.fileUpload(fileDTO);
            return "저장 결과 : " + result;
        }
        return null;
    }

    // 썸네일 등록
    public String saveThumb(int carId, MultipartFile multipartThumb) {


        String thumbFileName = carId + "carIdThumb" + multipartThumb.getOriginalFilename();
        Path thumbFilePath = Paths.get(uploadThumbFolder + thumbFileName);
        log.debug("thumbFileName :" + thumbFileName);
        if (multipartThumb.getSize() != 0) {
            try {
                Files.write(thumbFilePath, multipartThumb.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
            int result = carDAO.thumbUpload(carId, thumbFileName);
            log.debug("thumb result : " + result);
            return "저장 결과 : " + result;
        }
        return null;
    }

    // 글 추가 시 마지막 쓴 CarId +1로 세팅, (maxCarId)찾기
    public Integer getNewCarIdValue() {
        int result;
        int carOfMaxId = carDAO.maxCarId();
        if (carOfMaxId == 0) {
            result = 1;
            log.debug("no Car Data, maxId is 1");
        } else {
            result = carOfMaxId + 1;
            log.debug("maxIdFromCar= " + result);
        }
        log.debug("newCarIdValue= " + result);
        return result;
    }

    // 파일 읽기
    public ApiResponse<FileDTO> getFileByCarId(int carId) {
        FileDTO data = fileDAO.getFileByCarId(carId);
        return new ApiResponse(true, data);
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

    //판매 차량 개수
    public Long getCarCount(long mid) {
            long carCount = carDAO.getCarCount(mid);

            return carCount;
    }

    // 판매차량 가져오기 (댓글수 포함)
    public List<CarDTO> getMyCar (long mid) throws Exception {
        return carDAO.getMyCar(mid);
    }

    /*// 댓글수 전체 list
    public List<CountDTO> getCountComment() {
        return carDAO.getCountComment();
    }*/

}
