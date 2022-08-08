package com.example.carstoreapi.wishlist.model;

import org.springframework.stereotype.Repository;

@Repository
public interface WishlistDAO {

    // 찜목록 추가
    int postWishList(WishlistDTO wishlistDTO);

    // mid로 찜목록 불러오기
    WishlistDTO getWishListByMid(long mid);

    // 찜목록 삭제
    int delWishList(long wid);
}
