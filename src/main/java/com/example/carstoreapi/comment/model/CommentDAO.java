package com.example.carstoreapi.comment.model;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDAO {

   // 댓글쓰기
   int postComment(CommentDTO commentDTO);

   // 글 별로 댓글 리스트 불러오기
   List<CommentDTO> getCommentsByCarId(int carId);

   // cid에 해당하는 댓글 불러오기
   CommentDTO getCommentByCid(int cid);

   // 댓글 수정
   int putComment(CommentDTO commentDTO);

   // 댓글 삭제
   int delComment(int cid);

}
