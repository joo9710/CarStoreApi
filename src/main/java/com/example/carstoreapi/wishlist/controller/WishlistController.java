package com.example.carstoreapi.wishlist.controller;

import com.example.carstoreapi.common.ApiResponse;
import com.example.carstoreapi.wishlist.model.WishlistDTO;
import com.example.carstoreapi.wishlist.service.WishlistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/wishlist")
public class WishlistController {

    public final WishlistService wishlistservice;

    //찜목록 추가
    @PostMapping(value = "/")
    public ApiResponse<WishlistDTO> postWishList(@RequestBody WishlistDTO wishlistDTO) throws Exception{
        return wishlistservice.postWishList(wishlistDTO);
    }

    //해당하는 mid 찜목록 보기
    @GetMapping(value= "/{mid}")
    public List<WishlistDTO> getWishListByMid(@PathVariable long mid) throws Exception{
        log.debug("data" + mid);
        return wishlistservice.getWishListByMid(mid);
    }

    //찜 목록 삭제
    @DeleteMapping(value= "/{wid}")
    public ApiResponse<WishlistDTO> delWishList(@PathVariable long wid) throws Exception{
        return wishlistservice.delWishList(wid);
    }
}
