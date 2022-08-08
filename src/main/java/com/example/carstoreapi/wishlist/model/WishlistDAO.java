package com.example.carstoreapi.wishlist.model;

import org.springframework.stereotype.Repository;

@Repository
public interface WishlistDAO {

    // 찜목록 추가
    int postWishList(WishlistDTO wishlistDTO);
}
