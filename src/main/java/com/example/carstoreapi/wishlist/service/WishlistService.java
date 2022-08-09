package com.example.carstoreapi.wishlist.service;

import com.example.carstoreapi.common.ApiResponse;
import com.example.carstoreapi.wishlist.model.WishlistDAO;
import com.example.carstoreapi.wishlist.model.WishlistDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class WishlistService {

    public final WishlistDAO wishlistDAO;


    //찜목록 추가
    public ApiResponse<WishlistDTO> postWishList(WishlistDTO wishlistDTO) throws Exception{

        //위시리스트에 해당하는 글이 있는지?
        WishlistDTO selectWishCar = wishlistDAO.getWishCar(wishlistDTO.getCarId());

        //글이 없으면 true 리턴
        if(selectWishCar == null){
            wishlistDAO.postWishList(wishlistDTO);
            return new ApiResponse(true, "success to insert wishList" );
        }

        //글이 있으면 false 리턴
        else {
            return new ApiResponse(false, "failed to insert wishList");
        }


    }

    //mid로 찜목록 불러오기
    public List<WishlistDTO> getWishListByMid(long mid) {

        return wishlistDAO.getWishListByMid(mid);
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
