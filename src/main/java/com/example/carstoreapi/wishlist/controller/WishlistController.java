package com.example.carstoreapi.wishlist.controller;

import com.example.carstoreapi.common.ApiResponse;
import com.example.carstoreapi.wishlist.model.WishlistDTO;
import com.example.carstoreapi.wishlist.service.WishlistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
