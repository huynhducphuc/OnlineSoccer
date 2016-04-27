package com.example.ooosu.quanlysanbong.dbhelper;

/**
 * Created by Noname on 26/4/2016.
 */
public enum DistrictTable {
    TABLE_NAME("districts"),
    DISTRICT_ID("district_id"),
    DISTRICT_NAME("district_name"),
    CITY_ID("city_id");

    private final String value;

    private DistrictTable(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
