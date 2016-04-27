package com.example.ooosu.quanlysanbong.dbhelper;

/**
 * Created by Noname on 26/4/2016.
 */
public enum SlotTable {
    TABLE_NAME("slots"),
    MATCH_ID("match_id"),
    USER_ID("user_id"),
    QUANTITY("quantity"),
    VERIFIED("is_verified"),
    VERIFICATION_CODE("verification_code"),
    CREATED("created"),
    UPDATED("updated"),
    DELETED("deleted");

    private final String value;

    private SlotTable(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
