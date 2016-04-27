package com.example.ooosu.quanlysanbong.dbhelper;

/**
 * Created by Noname on 26/4/2016.
 */
public enum FieldTable {

    TABLE_NAME("fields"),
    FIELD_ID("field_id"),
    FIELD_NAME("field_name"),
    DISTRICT_ID("district_id"),
    ADDRESS("address"),
    LATITUDE("latitude"),
    LONGITUDE("longitude"),
    PHONE_NUMBER("phone_number"),
    CREATED("created"),
    UPDATED("updated"),
    DELETED("deleted");

    private final String value;

    private FieldTable(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
