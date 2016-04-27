package com.example.ooosu.quanlysanbong.dbhelper;

/**
 * Created by Noname on 26/4/2016.
 */
public enum UserTable {

    TABLE_NAME("users"),
    USER_ID("user_id"),
    USERNAME("username"),
    PASSWORD("password"),
    EMAIL("email"),
    PHONE_NUMBER("phone_number"),
    STATUS("status"),
    DISTRICT_ID("district_id"),
    USER_TYPE("user_type"),
    LAST_LOGIN("last_login"),
    VERIFIED("is_verified"),
    VERIFICATION_CODE("verification_code"),
    CREATED("created"),
    UPDATED("updated"),
    DELETED("deleted");

    private final String value;

    private UserTable(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
