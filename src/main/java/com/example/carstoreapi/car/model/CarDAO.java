package com.example.carstoreapi.car.model;

import com.example.carstoreapi.car.Criteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Repository
public interface CarDAO {

    //글 읽기
    List<CarDTO> getCarList();

    //페이징 처리
    List<CarDTO> getPageCarList(Criteria criteria);

    Page<CarDTO> getPageList(String isDel,PageRequest pageRequest);

    //total 글 목록
    int getTotalCar();

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
    Integer maxCarId();

    //썸네일 업로드
    int thumbUpload(int carId, String thumb);

    // 판매중인 차량 수
    long getCarCount(long mid);

    List getMyCar(long mid);

    /*// 댓글수 전체 list
    List<CountDTO>getCountComment();*/

    //nickName변경시 author 수정
    int putAuthor(long mid,String author);

    //국산 or 수입차 list
    List getNationalCar(String national);

    // 회사별 차량 조회
    List getCompanyOfCar(String company);
}
