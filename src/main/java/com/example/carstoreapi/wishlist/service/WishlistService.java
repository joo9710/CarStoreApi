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


    //찜목록 추가
    public ApiResponse<WishlistDTO> postWishList(WishlistDTO wishlistDTO) throws Exception{

        wishlistDAO.postWishList(wishlistDTO);
        return new ApiResponse(true, "success to insert wishList" );
    }

    //mid로 찜목록 불러오기
    public ApiResponse<WishlistDTO> getWishListByMid(long mid) {
        WishlistDTO data = wishlistDAO.getWishListByMid(mid);
        return new ApiResponse(true, data);
    }

    //찜목록 삭제
    public ApiResponse<WishlistDTO> delWishList(long wid) {
        int delWishCar = wishlistDAO.delWishList(wid);

        if (delWishCar > 0){
            return new ApiResponse<>(true, "delete WishCar successfull");
        }else
       return new ApiResponse<>(false, "failed to delete WishCar");
    }
}
