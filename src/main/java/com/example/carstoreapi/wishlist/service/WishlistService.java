package com.example.carstoreapi.wishlist.service;

import com.example.carstoreapi.common.ApiResponse;
import com.example.carstoreapi.wishlist.model.WishlistDAO;
import com.example.carstoreapi.wishlist.model.WishlistDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class WishlistService {

    public final WishlistDAO wishlistDAO;

    public ApiResponse<WishlistDTO> postWishList(WishlistDTO wishlistDTO) throws Exception{

        wishlistDAO.postWishList(wishlistDTO);
        return new ApiResponse(true, "success to insert wishList" );
    }
}
