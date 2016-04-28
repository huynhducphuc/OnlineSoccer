package com.example.ooosu.quanlysanbong.model.bean;

/**
 * Created by oOosu on 4/28/2016.
 */
public class ViewMatch {
    private int id;
    private String fieldName;
    private int price;
    private String startTime;
    private long seats;

    public ViewMatch(int id, String fieldName, int price, String startTime, long seats) {
        this.id = id;
        this.fieldName = fieldName;
        this.price = price;
        this.startTime = startTime;
        this.seats = seats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public long getSeats() {
        return seats;
    }

    public void setSeats(long seats) {
        this.seats = seats;
    }
}
