package com.example.ooosu.quanlysanbong.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.noname.onlinesoccer.bean.District;
import com.example.noname.onlinesoccer.dbhelper.DatabaseHelper;
import com.example.noname.onlinesoccer.dbhelper.DistrictTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Noname on 26/4/2016.
 */
public class DistrictService {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    public DistrictService(Context context) {
        this.databaseHelper = new DatabaseHelper(context);
    }

    public District getDistrict(int id) {
        District district = null;
        if (id <= 0) {
            return null;
        } else {
            db = databaseHelper.getWritableDatabase();
            Cursor cursor = db.query(DistrictTable.TABLE_NAME.getValue(),
                    new String[]{DistrictTable.DISTRICT_ID.getValue(),
                            DistrictTable.DISTRICT_NAME.getValue(),
                            DistrictTable.CITY_ID.getValue()},
                    DistrictTable.DISTRICT_ID + "=?",
                    new String[]{String.valueOf(id)},
                    null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                district = new District(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
            }
            return district;
        }
    }

    public List<District> getAllDistricts() {
        List<District> results = new ArrayList<>();
        District district = null;
        String query = "SELECT * FROM " + DistrictTable.TABLE_NAME.getValue();
        db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                district = new District(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
                results.add(district);
            } while (cursor.moveToNext());
        }

        return results;
    }

    public long addDistrict(District district) {
        db = databaseHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DistrictTable.DISTRICT_NAME.getValue(), district.getName());
        cv.put(DistrictTable.CITY_ID.getValue(), district.getCityId());

        return db.insert(DistrictTable.TABLE_NAME.getValue(), null, cv);

    }

    public long updateDistrict(District district) {
        if (district.getId() <= 0) {
            return -1;
        } else {
            db = databaseHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(DistrictTable.DISTRICT_NAME.getValue(), district.getName());
            cv.put(DistrictTable.CITY_ID.getValue(), district.getCityId());

            return db.update(DistrictTable.TABLE_NAME.getValue(), cv, DistrictTable.DISTRICT_ID + "=?", new String[] {String.valueOf(district.getId())});
        }
    }

    public long deleteDistrict(District district) {
        if (district.getId() <= 0) {
            return -1;
        } else {
            db = databaseHelper.getWritableDatabase();
            return db.delete(DistrictTable.TABLE_NAME.getValue(), DistrictTable.DISTRICT_ID + "=?", new String[] {String.valueOf(district.getId())});

        }
    }

}
