package com.cleverDY.pojo;

import java.time.LocalDateTime;

public class Order {
    private int orderId;

    private String goodName;

    private int orderNumber;

    private LocalDateTime orderTime;

    private int orderPrice;

    public Order(int orderId, String goodName, int orderNum, LocalDateTime orderTime, int orderPrice) {
        this.orderId = orderId;
        this.goodName = goodName;
        this.orderNumber = orderNum;
        this.orderTime = orderTime;
        this.orderPrice = orderPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public int getOrderNum() {
        return orderNumber;
    }

    public void setOrderNum(int orderNum) {
        this.orderNumber = orderNum;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", goodName='" + goodName + '\'' +
                ", orderNum=" + orderNumber +
                ", orderTime=" + orderTime +
                ", orderPrice=" + orderPrice + "\n" +
                '}';
    }
}
