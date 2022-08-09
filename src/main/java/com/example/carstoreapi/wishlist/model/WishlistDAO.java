package com.example.carstoreapi.wishlist.model;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistDAO {

    // 찜목록 추가
    int postWishList(WishlistDTO wishlistDTO);

    // mid로 찜목록 불러오기
    List getWishListByMid(long mid);

    // 찜목록 삭제
    int delWishList(long wid);

    //위시리스트에서 해당 carId가 있는지?
    WishlistDTO getWishCar(int carId);
}
