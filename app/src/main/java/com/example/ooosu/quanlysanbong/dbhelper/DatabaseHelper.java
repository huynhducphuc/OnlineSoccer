package com.example.ooosu.quanlysanbong.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;
/**
 * Created by Noname on 26/4/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private static final String DB_NAME = "onlinesoccer.sqlite";
    private static final int VERSION = 1;

    // City table
    public static final String SQL_CREATE_CITY = "CREATE TABLE " + CityTable.TABLE_NAME.getValue() + " (" + CityTable.CITY_ID.getValue() + " INTEGER PRIMARY KEY AUTOINCREMENT," + CityTable.CITY_NAME.getValue() + " TEXT);";

    // District table
    public static final String SQL_CREATE_DISTRICT = "CREATE TABLE " + DistrictTable.TABLE_NAME.getValue() + " (" + DistrictTable.DISTRICT_ID.getValue() + " INTEGER PRIMARY KEY AUTOINCREMENT," + DistrictTable.DISTRICT_NAME.getValue() + " TEXT," + DistrictTable.CITY_ID.getValue() + " INTEGER REFERENCES " + CityTable.TABLE_NAME.getValue() + " (" + CityTable.CITY_ID.getValue() + ") ON DELETE CASCADE ON UPDATE CASCADE);";

    // Field table
    public static final String SQL_CREATE_FIELD = "CREATE TABLE " + FieldTable.TABLE_NAME.getValue() + " (" + FieldTable.FIELD_ID.getValue() + " INTEGER PRIMARY KEY AUTOINCREMENT," + FieldTable.FIELD_NAME.getValue() + " TEXT NOT NULL," + FieldTable.DISTRICT_ID.getValue() + " INTEGER REFERENCES " + DistrictTable.TABLE_NAME.getValue() + " (" + DistrictTable.DISTRICT_ID.getValue() + ")," + FieldTable.ADDRESS.getValue() + " TEXT," + FieldTable.LATITUDE.getValue() + " REAL," + FieldTable.LONGITUDE.getValue() + " REAL," + FieldTable.PHONE_NUMBER.getValue() + " TEXT," + FieldTable.CREATED.getValue() + " DATETIME," + FieldTable.UPDATED.getValue() + " DATETIME," + FieldTable.DELETED.getValue() + " DATETIME);";

    // User table
    public static final String SQL_CREATE_USER = "CREATE TABLE " + UserTable.TABLE_NAME.getValue() + " (" + UserTable.USER_ID.getValue() + " INTEGER PRIMARY KEY AUTOINCREMENT," + UserTable.USERNAME.getValue() + " TEXT NOT NULL," + UserTable.PASSWORD.getValue() + " TEXT NOT NULL," + UserTable.EMAIL.getValue() + " TEXT NOT NULL," + UserTable.PHONE_NUMBER.getValue() + " TEXT NOT NULL," + UserTable.STATUS.getValue() + " INTEGER," + UserTable.DISTRICT_ID.getValue() + " INTEGER REFERENCES " + DistrictTable.TABLE_NAME.getValue() + " (" + DistrictTable.DISTRICT_ID.getValue() + ")," + UserTable.USER_TYPE.getValue() + " INTEGER NOT NULL," + UserTable.LAST_LOGIN.getValue() + " DATETIME," + UserTable.VERIFIED.getValue() + " BOOLEAN," + UserTable.VERIFICATION_CODE.getValue() + " TEXT," + UserTable.CREATED.getValue() + " DATETIME," + UserTable.UPDATED.getValue() + " DATETIME," + UserTable.DELETED.getValue() + " DATETIME);";

    // Match table
    public static final String SQL_CREATE_MATCH = "CREATE TABLE " + MatchTable.TABLE_NAME.getValue() + " (" + MatchTable.MATCH_ID.getValue() + " INTEGER PRIMARY KEY AUTOINCREMENT," + MatchTable.FIELD_ID.getValue() + " INTEGER REFERENCES " + FieldTable.TABLE_NAME.getValue() + " (" + FieldTable.FIELD_ID.getValue() + ")," + MatchTable.HOST_ID.getValue() + " INTEGER REFERENCES " + UserTable.TABLE_NAME.getValue() + " (" + UserTable.USER_ID.getValue() + ")," + MatchTable.STATUS.getValue() + " INTEGER," + MatchTable.MAX_PLAYERS.getValue() + " INTEGER," + MatchTable.PRICE.getValue() + " INTEGER," + MatchTable.START_TIME.getValue() + " DATETIME," + MatchTable.END_TIME.getValue() + " DATETIME," + MatchTable.VERIFIED.getValue() + " BOOLEAN," + MatchTable.VERIFICATION_CODE.getValue() + " TEXT," + MatchTable.CREATED.getValue() + " DATETIME," + MatchTable.UPDATED.getValue() + " DATETIME," + MatchTable.DELETED.getValue() + " DATETIME);";

    // Slot table
    public static final String SQL_CREATE_SLOT = "CREATE TABLE " + SlotTable.TABLE_NAME.getValue() + " ( " + SlotTable.MATCH_ID.getValue() + " INTEGER  REFERENCES " + MatchTable.TABLE_NAME.getValue() + " (" + MatchTable.MATCH_ID.getValue() + ")," + SlotTable.USER_ID.getValue() + " INTEGER REFERENCES " + UserTable.TABLE_NAME.getValue() + " (" + UserTable.USER_ID + ")," + SlotTable.QUANTITY.getValue() + " INTEGER NOT NULL," + SlotTable.VERIFIED.getValue() + " BOOLEAN," + SlotTable.VERIFICATION_CODE.getValue() + " TEXT," + SlotTable.CREATED.getValue() + " DATETIME," + SlotTable.UPDATED.getValue() + " DATETIME," + SlotTable.DELETED.getValue() + " DATETIME,PRIMARY KEY (" + SlotTable.MATCH_ID.getValue() + ", " + SlotTable.USER_ID.getValue() + "));";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate running");
        db.execSQL(SQL_CREATE_CITY);
        db.execSQL(SQL_CREATE_DISTRICT);
        db.execSQL(SQL_CREATE_FIELD);
        db.execSQL(SQL_CREATE_USER);
        db.execSQL(SQL_CREATE_MATCH);
        db.execSQL(SQL_CREATE_SLOT);

        // insert data
        // city
        db.execSQL("insert into cities(city_name) values('Đà Nẵng');");
        db.execSQL("insert into cities(city_name) values('Hà Nội');");
        db.execSQL("insert into cities(city_name) values('Quảng Nam');");
        db.execSQL("insert into cities(city_name) values('Bình Định');");

        // district
        db.execSQL("insert into districts(district_name, city_id) values('Liên Chiểu' ,1);");
        db.execSQL("insert into districts(district_name, city_id) values('Cẩm Lệ' ,1);");
        db.execSQL("insert into districts(district_name, city_id) values('Thanh Khê' ,1);");
        db.execSQL("insert into districts(district_name, city_id) values('Ngũ Hành Sơn' ,1);");
        db.execSQL("insert into districts(district_name, city_id) values('Sơn Trà' ,1);");
        db.execSQL("insert into districts(district_name, city_id) values('Hòa Vang' ,1);");
        db.execSQL("insert into districts(district_name, city_id) values('Hải Châu' ,1);");

        // field
        db.execSQL("insert into fields(field_name, district_id, address, latitude, longitude, phone_number, created, updated, deleted) values ('Nguyễn Chánh', 1, 'Hoà Khánh Bắc Liên Chiểu Đà Nẵng', 16.085001, 108.151137, '098 1198132', datetime('now'), null, null);");
        db.execSQL("insert into fields(field_name, district_id, address, latitude, longitude, phone_number, created, updated, deleted) values ('Sân bóng Chuyên Việt', 7, '98 Tiểu La, Quận Hải Châu', 16.050577, 108.209351, '05113638555', datetime('now'), null, null);");
        db.execSQL("insert into fields(field_name, district_id, address, latitude, longitude, phone_number, created, updated, deleted) values ('Sân bóng MU', 1, '59 Ngô Thì Nhậm, Hòa Khánh Nam, Liên Chiểu', 16.071593, 108.155218, '05113388987', datetime('now'), null, null);");
        db.execSQL("insert into fields(field_name, district_id, address, latitude, longitude, phone_number, created, updated, deleted) values ('Sân bóng Tân Hưng yên', 1, '878 Tôn Đức Thắng – Liên Chiểu', 16.069858, 108.151479, '05113388987', datetime('now'), null, null);");
        db.execSQL("insert into fields(field_name, district_id, address, latitude, longitude, phone_number, created, updated, deleted) values ('Cụm sân bóng đá Làng thể thao Tuyên Sơn', 7, 'Số 22 đường 2/9 – Quận Hải Châu ', 16.032229, 108.223181, '(0511) 3630.666', datetime('now'), null, null);");
        db.execSQL("insert into fields(field_name, district_id, address, latitude, longitude, phone_number, created, updated, deleted) values ('Sân bóng Duy Tân (Quân Khu 5)', 7, 'số 07 đường Duy Tân, Quận Hải Châu, Tp.Đà Nẵng', 16.048863, 108.212171, '(0511) 6555 197', datetime('now'), null, null);");

        // user
        db.execSQL("insert into users(username, password, email, phone_number, status, district_id, user_type, last_login, is_verified, verification_code, created, updated, deleted) values('onlinesoccer', '123456', 'abc@onlinesoccer@gmail.com', '016434467809', 1, 1, 1, datetime('now'), 1, null, datetime('now'), null, null);");
        db.execSQL("insert into users(username, password, email, phone_number, status, district_id, user_type, last_login, is_verified, verification_code, created, updated, deleted) values('onlinesoccer1', '123456', 'abc@onlinesoccer@gmail.com', '016434467809', 1, 1, 1, datetime('now'), 1, null, datetime('now'), null, null);");

        // match
        db.execSQL("insert into matches(field_id, host_id, status, maximum_players, price, start_time, end_time, is_verified, verification_code, created, updated, deleted) values (1, 1, 1, 15, 100, datetime('now', '+1440 minutes'), datetime('now', '+1560 minutes'), 1, null, datetime('now'), null, null);");

        // slot
        db.execSQL("insert into slots(match_id, user_id, quantity, is_verified, verification_code, created, updated, deleted) values(1, 2, 4, 1, null, date('now'), null, null);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
