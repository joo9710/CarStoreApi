package com.example.carstoreapi.car.service;

import com.example.carstoreapi.car.model.CarDAO;
import com.example.carstoreapi.car.model.CarDTO;
import com.example.carstoreapi.car.model.FileDAO;
import com.example.carstoreapi.car.model.FileDTO;
import com.example.carstoreapi.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

@RequiredArgsConstructor
@Service
@Slf4j
public class CarService {

    public final CarDAO carDAO;

    public final FileDAO fileDAO;

    @Value("${uploadImg.path}")
    private String uploadFolder;

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
}
