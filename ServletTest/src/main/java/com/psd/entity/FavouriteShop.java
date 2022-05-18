package com.psd.entity;

public class FavouriteShop {
    private Integer user_id;
    private Integer shop_id;

    public FavouriteShop(Integer user_id, Integer shop_id) {
        this.user_id = user_id;
        this.shop_id = shop_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getShop_id() {
        return shop_id;
    }

    public void setShop_id(Integer shop_id) {
        this.shop_id = shop_id;
    }

    public FavouriteShop() {
    }
}
