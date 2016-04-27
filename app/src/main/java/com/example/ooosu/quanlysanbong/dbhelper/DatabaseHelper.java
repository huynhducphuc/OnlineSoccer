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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
