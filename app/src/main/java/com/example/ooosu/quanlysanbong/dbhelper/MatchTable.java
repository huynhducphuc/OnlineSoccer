package com.example.ooosu.quanlysanbong.dbhelper;

/**
 * Created by Noname on 26/4/2016.
 */
public enum MatchTable {
    TABLE_NAME("matches"),
    MATCH_ID("match_id"),
    FIELD_ID("field_id"),
    HOST_ID("host_id"),
    STATUS("status"),
    MAX_PLAYERS("maximum_players"),
    PRICE("price"),
    START_TIME("start_time"),
    END_TIME("end_time"),
    VERIFIED("is_verified"),
    VERIFICATION_CODE("verification_code"),
    CREATED("created"),
    UPDATED("updated"),
    DELETED("deleted");

    private final String value;

    private MatchTable(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
