package com.example.ooosu.quanlysanbong.dbhelper;

/**
 * Created by Noname on 26/4/2016.
 */
public enum CityTable {
    TABLE_NAME("cities"),
    CITY_ID("city_id"),
    CITY_NAME("city_name");

    private final String value;

    private CityTable(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }
}
