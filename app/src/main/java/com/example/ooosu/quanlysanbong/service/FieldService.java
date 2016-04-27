package com.example.ooosu.quanlysanbong.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ooosu.quanlysanbong.dbhelper.DatabaseHelper;
import com.example.ooosu.quanlysanbong.dbhelper.FieldTable;
import com.example.ooosu.quanlysanbong.model.bean.Field;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Noname on 26/4/2016.
 */
public class FieldService {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    public FieldService(Context context) {
        this.databaseHelper = new DatabaseHelper(context);
    }

    public Field getField(int id) {
        Field field = null;

        db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.query(FieldTable.TABLE_NAME.getValue(),
                new String[]{FieldTable.FIELD_ID.getValue(),
                        FieldTable.FIELD_NAME.getValue(),
                        FieldTable.DISTRICT_ID.getValue(),
                        FieldTable.ADDRESS.getValue(),
                        FieldTable.LATITUDE.getValue(),
                        FieldTable.LONGITUDE.getValue(),
                        FieldTable.PHONE_NUMBER.getValue(),
                        FieldTable.CREATED.getValue(),
                        FieldTable.UPDATED.getValue(),
                        FieldTable.DELETED.getValue()},
                FieldTable.FIELD_ID.getValue() + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            field = new Field(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getFloat(4), cursor.getFloat(5), cursor.getString(6), Timestamp.valueOf(cursor.getString(7)), Timestamp.valueOf(cursor.getString(8)), Timestamp.valueOf(cursor.getString(9)));
        }
        return field;
    }

    public List<Field> getAllFields() {
        String query = "SELECT * FROM " + FieldTable.TABLE_NAME.getValue();
        List<Field> results = new ArrayList<>();
        Field field = null;

        db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                field = new Field(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getFloat(4), cursor.getFloat(5), cursor.getString(6), Timestamp.valueOf(cursor.getString(7)), Timestamp.valueOf(cursor.getString(8)), Timestamp.valueOf(cursor.getString(9)));
                results.add(field);
            } while (cursor.moveToNext());
        }

        return results;
    }

    public long addField(Field field) {
        if (field.getDistrictId() == 0) {
            return -1;
        } else {
            db = databaseHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(FieldTable.FIELD_NAME.getValue(), field.getName());
            cv.put(FieldTable.DISTRICT_ID.getValue(), field.getDistrictId());
            cv.put(FieldTable.ADDRESS.getValue(), field.getAddress());
            cv.put(FieldTable.LATITUDE.getValue(), field.getLatitude());
            cv.put(FieldTable.LONGITUDE.getValue(), field.getLongitude());
            cv.put(FieldTable.PHONE_NUMBER.getValue(), field.getPhoneNumber());

            return db.insert(FieldTable.TABLE_NAME.getValue(), null, cv);
        }
    }

    public long updateField(Field field) {
        if (field.getDistrictId() == 0 || field.getId() == 0) {
            return -1;
        } else {
            db = databaseHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(FieldTable.FIELD_NAME.getValue(), field.getName());
            cv.put(FieldTable.DISTRICT_ID.getValue(), field.getDistrictId());
            cv.put(FieldTable.ADDRESS.getValue(), field.getAddress());
            cv.put(FieldTable.LATITUDE.getValue(), field.getLatitude());
            cv.put(FieldTable.LONGITUDE.getValue(), field.getLongitude());
            cv.put(FieldTable.PHONE_NUMBER.getValue(), field.getPhoneNumber());

            return db.update(FieldTable.TABLE_NAME.getValue(), cv, FieldTable.FIELD_ID + "=?", new String[]{String.valueOf(field.getId())});
        }
    }

    public long deleteField(Field field) {
        if (field.getId() == 0) {
            return -1;
        } else {
            db = databaseHelper.getWritableDatabase();
            return db.delete(FieldTable.TABLE_NAME.getValue(), FieldTable.FIELD_ID + "=?", new String[]{String.valueOf(field.getId())});
        }
    }
}
