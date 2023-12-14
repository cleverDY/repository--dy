package com.cleverDY.pojo;

public class Goods {
    private int goodId;
    private String goodName;
    private int goodPrice;
    private int goodNumber;

    public Goods(int goodId, String goodName, int goodPrice, int goodNumber) {
        this.goodId = goodId;
        this.goodName = goodName;
        this.goodPrice = goodPrice;
        this.goodNumber = goodNumber;
    }

    public Goods() {

    }


    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public int getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(int goodPrice) {
        this.goodPrice = goodPrice;
    }

    public int getGoodNumber() {
        return goodNumber;
    }

    public void setGoodNumber(int goodNumber) {
        this.goodNumber = goodNumber;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodId=" + goodId +
                ", goodName='" + goodName +'\'' +
                ", goodPrice=" + goodPrice +
                ", goodNumber=" + goodNumber + "\n"+
                '}';
    }
}
