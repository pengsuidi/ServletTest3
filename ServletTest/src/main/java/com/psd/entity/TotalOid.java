package com.psd.entity;

public class TotalOid {
    private int oid;
    private Float total_price;
    private int user_id;

    public Float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Float total_price) {
        this.total_price = total_price;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public TotalOid(int oid, Float total_price, int user_id) {
        this.oid = oid;
        this.total_price = total_price;
        this.user_id = user_id;
    }

    public TotalOid(int oid) {
        this.oid = oid;
    }
    public TotalOid() {

    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }
}
