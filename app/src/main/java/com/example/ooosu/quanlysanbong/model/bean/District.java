package com.example.ooosu.quanlysanbong.model.bean;

/**
 * Created by Noname on 26/4/2016.
 */
public class District {
    private int id;
    private String name;
    private int cityId;

    public District() {
    }

    public District(int id, String name, int cityId) {
        this.id = id;
        this.name = name;
        this.cityId = cityId;
    }

    public District(String name, int cityId) {
        this.name = name;
        this.cityId = cityId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return "District{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cityId=" + cityId +
                '}';
    }
}
