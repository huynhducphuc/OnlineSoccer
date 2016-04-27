package com.example.ooosu.quanlysanbong.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.ooosu.quanlysanbong.dbhelper.CityTable;
import com.example.ooosu.quanlysanbong.dbhelper.DatabaseHelper;
import com.example.ooosu.quanlysanbong.model.bean.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Noname on 26/4/2016.
 */
public class CityService {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    public CityService(Context context) {
        this.databaseHelper = new DatabaseHelper(context);
    }

    public City getCity(int id) {
        City city = null;
        db = databaseHelper.getWritableDatabase();

        Cursor cursor = db.query(CityTable.TABLE_NAME.getValue(),
                new String[]{CityTable.CITY_ID.getValue(), CityTable.CITY_NAME.getValue()},
                CityTable.CITY_ID.getValue() + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            city = new City(cursor.getInt(0), cursor.getString(1));
        }

        return city;
    }

    public long addCity(City city) {
        db = databaseHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(CityTable.CITY_NAME.getValue(), city.getName());

        return db.insert(CityTable.TABLE_NAME.getValue(), null, cv);
    }

    public long updateCity(City city) {
        if (city.getId() == 0) {
            return -1;
        } else {
            db = databaseHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(CityTable.CITY_NAME.getValue(), city.getName());

            return db.update(CityTable.TABLE_NAME.getValue(), cv, CityTable.CITY_ID.getValue() + "=?", new String[]{String.valueOf(city.getId())});
        }
    }

    public long deleteCity(City city) {
        if (city.getId() == 0) {
            return -1;
        } else {
            db = databaseHelper.getWritableDatabase();
            return db.delete(CityTable.TABLE_NAME.getValue(), CityTable.CITY_ID.getValue() + "=?", new String[]{String.valueOf(city.getId())});
        }
    }

    public List<City> getAllCities() {
        List<City> results = new ArrayList<>();
        City city = null;
        String query = "SELECT " + CityTable.CITY_ID.getValue() + ", " + CityTable.CITY_NAME.getValue() + " FROM " + CityTable.TABLE_NAME.getValue() + ";";
        db = databaseHelper.getWritableDatabase();

        databaseHelper.getDatabaseName();

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                city = new City(cursor.getInt(0), cursor.getString(1));
                results.add(city);
            } while (cursor.moveToNext());
        }

        return results;
    }
}
