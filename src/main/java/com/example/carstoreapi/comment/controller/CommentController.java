package com.example.carstoreapi.comment.controller;

import com.example.carstoreapi.comment.model.CommentDTO;
import com.example.carstoreapi.comment.service.CommentService;
import com.example.carstoreapi.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/comment")
public class CommentController {

    public final CommentService commentService;

    //댓글 쓰기
    @PostMapping(value = "/")
    public ApiResponse<CommentDTO> postComment(@RequestBody CommentDTO commentDTO) throws Exception{
        return commentService.postComment(commentDTO);
    }

    // 글 별로 댓글 보기
    @GetMapping(value = "/{carId}")
    public ApiResponse<CommentDTO> getCommentsByCarId(@PathVariable int carId) throws Exception {
        return commentService.getCommentsByCarId(carId);
    }

    // cid에 해당하는 댓글 불러오기
    @GetMapping(value= "/load/{cid}")
    public ApiResponse<CommentDTO> getCommentByCid(@PathVariable int cid) throws Exception {
        return commentService.getCommentByCid(cid);
    }

    // 댓글 수정
    @PutMapping(value = "/{cid}")
    public String putComment(@PathVariable int cid,
                             @RequestBody CommentDTO commentDTO) throws Exception {
        log.debug("cid: " + cid);
        return commentService.putComment(cid, commentDTO);
    }

    // 댓글 삭제
    @DeleteMapping(value = "/{cid}")
    public ApiResponse<CommentDTO> delComment(@PathVariable int cid) throws Exception{
        return commentService.delComment(cid);
    }

}
