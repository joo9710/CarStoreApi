package com.example.carstoreapi.comment.service;

import com.example.carstoreapi.car.model.CarDAO;
import com.example.carstoreapi.car.model.CarDTO;
import com.example.carstoreapi.comment.model.CommentDAO;
import com.example.carstoreapi.comment.model.CommentDTO;
import com.example.carstoreapi.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class CommentService {

    public final CarDAO carDAO;
    public final CommentDAO commentDAO;

    // 댓글 쓰기
    public ApiResponse<CommentDTO> postComment(CommentDTO commentDTO) throws Exception{

        // 요청받은 id값에 해당하는 Car컨텐츠가 있는지? 없는지?
        CarDTO selectedCar = carDAO.getCarById(commentDTO.getCarId());
        commentDTO.setWriteDate(LocalDate.now());
        commentDTO.setWriteTime(LocalTime.now());

        if (selectedCar == null) {
            String failedMessage = "id value " + commentDTO.getCarId() + "is not exists in board";
            return new ApiResponse(false, failedMessage);
        } else {
            commentDAO.postComment(commentDTO);
            String successMessage = "success to insert comment data id " + commentDTO.getCarId();
            return new ApiResponse(true, successMessage, commentDTO);
        }
    }

    // 글 별로 댓글보기
    public ApiResponse<CommentDTO>  getCommentsByCarId(int carId) {
        // 요청받은 carId가 가진 comment테이블의 데이터 목록
        List<CommentDTO> comments = commentDAO.getCommentsByCarId(carId);
        return new ApiResponse(true, "success to get comments by carId" + carId,comments);
    }

    // cid에 해당하는 댓글 불러오기
    public ApiResponse<CommentDTO> getCommentByCid(int cid) {
        CommentDTO data = commentDAO.getCommentByCid(cid);
        return new ApiResponse(true, "success to get comment by cid" + cid, data);
    }

    // 댓글 수정
    public String putComment(int cid, CommentDTO commentDTO) throws Exception {

        commentDTO.setCid(cid);
        commentDTO.setWriteDate(LocalDate.now());
        commentDTO.setWriteTime(LocalTime.now());

        int result = commentDAO.putComment(commentDTO);

        if(result > 0){
            return result + "comment updated";
        }
        throw new Exception("failed to update " + cid + " comment");
    }

    // 댓글 삭제
    public ApiResponse<CommentDTO> delComment(int cid) throws Exception {

        int delRow = commentDAO.delComment(cid);

        if(delRow > 0) {
            return new ApiResponse(true, "cid: " + cid + " is successfully deleted");

        }
        return new ApiResponse(false, "failed to delete comment id: " + cid);
    }

}
