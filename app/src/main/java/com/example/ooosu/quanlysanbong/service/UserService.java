package com.example.ooosu.quanlysanbong.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ooosu.quanlysanbong.dbhelper.DatabaseHelper;
import com.example.ooosu.quanlysanbong.dbhelper.UserTable;
import com.example.ooosu.quanlysanbong.model.bean.User;
import com.example.ooosu.quanlysanbong.utils.DateUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Noname on 27/4/2016.
 */
public class UserService {
    private DatabaseHelper databaseHelper = null;
    private SQLiteDatabase db = null;

    public UserService(Context context) {
        this.databaseHelper = new DatabaseHelper(context);
    }

    public User getUser(int id) {
        if (id <= 0) {
            return null;
        } else {
            User user = null;
            db = databaseHelper.getWritableDatabase();
            Cursor cursor = db.query(UserTable.TABLE_NAME.getValue(),
                    new String[]{UserTable.USER_ID.getValue(),
                            UserTable.USERNAME.getValue(),
                            UserTable.PASSWORD.getValue(),
                            UserTable.EMAIL.getValue(),
                            UserTable.PHONE_NUMBER.getValue(),
                            UserTable.STATUS.getValue(),
                            UserTable.DISTRICT_ID.getValue(),
                            UserTable.USER_TYPE.getValue(),
                            UserTable.LAST_LOGIN.getValue(),
                            UserTable.VERIFIED.getValue(),
                            UserTable.VERIFICATION_CODE.getValue(),
                            UserTable.CREATED.getValue(),
                            UserTable.UPDATED.getValue(),
                            UserTable.DELETED.getValue()},
                    UserTable.USER_ID + "=?",
                    new String[]{String.valueOf(id)},
                    null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                user = new User(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5),
                        cursor.getInt(6),
                        cursor.getInt(7),
                        DateUtils.convertToTimestamp(cursor.getString(8)),
                        cursor.getInt(9) > 0,
                        cursor.getString(10),
                        DateUtils.convertToTimestamp(cursor.getString(11)),
                        DateUtils.convertToTimestamp(cursor.getString(12)),
                        DateUtils.convertToTimestamp(cursor.getString(13)));
            }
            return user;
        }
    }

    public List<User> getAllUsers() {
        List<User> results = new ArrayList<>();
        User user = null;
        db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.query(UserTable.TABLE_NAME.getValue(),
                new String[]{UserTable.USER_ID.getValue(),
                        UserTable.USERNAME.getValue(),
                        UserTable.PASSWORD.getValue(),
                        UserTable.EMAIL.getValue(),
                        UserTable.PHONE_NUMBER.getValue(),
                        UserTable.STATUS.getValue(),
                        UserTable.DISTRICT_ID.getValue(),
                        UserTable.USER_TYPE.getValue(),
                        UserTable.LAST_LOGIN.getValue(),
                        UserTable.VERIFIED.getValue(),
                        UserTable.VERIFICATION_CODE.getValue(),
                        UserTable.CREATED.getValue(),
                        UserTable.UPDATED.getValue(),
                        UserTable.DELETED.getValue()},
                null, null, null, null, null, null);

        if (cursor != null &&  cursor.moveToFirst()) {
            do {
                user = new User(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5),
                        cursor.getInt(6),
                        cursor.getInt(7),
                        DateUtils.convertToTimestamp(cursor.getString(8)),
                        cursor.getInt(9) > 0,
                        cursor.getString(10),
                        DateUtils.convertToTimestamp(cursor.getString(11)),
                        DateUtils.convertToTimestamp(cursor.getString(12)),
                        DateUtils.convertToTimestamp(cursor.getString(13)));

                results.add(user);

            } while (cursor.moveToNext());
        }

        return results;
    }

    public long addUser(User user) {
        db = databaseHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(UserTable.USERNAME.getValue(), user.getUsername());
        cv.put(UserTable.PASSWORD.getValue(), user.getPassword());
        cv.put(UserTable.EMAIL.getValue(), user.getEmail());
        cv.put(UserTable.PHONE_NUMBER.getValue(), user.getPhoneNumber());
        cv.put(UserTable.STATUS.getValue(), user.getStatus());
        cv.put(UserTable.DISTRICT_ID.getValue(), user.getDistrictId());
        cv.put(UserTable.USER_TYPE.getValue(), user.getUserType());
        cv.put(UserTable.LAST_LOGIN.getValue(), user.getLastLogin().toString());
        cv.put(UserTable.VERIFIED.getValue(), user.isVerified());
        cv.put(UserTable.VERIFICATION_CODE.getValue(), user.getVerificationCode());
        cv.put(UserTable.CREATED.getValue(), DateUtils.formatDatetime(user.getCreatedDate()));
        cv.put(UserTable.UPDATED.getValue(), DateUtils.formatDatetime(user.getUpdatedDate()));
        cv.put(UserTable.DELETED.getValue(), DateUtils.formatDatetime(user.getDeletedDate()));

        return db.insert(UserTable.TABLE_NAME.getValue(), null, cv);
    }

    public long updateUser(User user) {
        if (user.getId() <= 0) {
            return -1;
        } else {
            db = databaseHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(UserTable.USERNAME.getValue(), user.getUsername());
            cv.put(UserTable.PASSWORD.getValue(), user.getPassword());
            cv.put(UserTable.EMAIL.getValue(), user.getEmail());
            cv.put(UserTable.PHONE_NUMBER.getValue(), user.getPhoneNumber());
            cv.put(UserTable.STATUS.getValue(), user.getStatus());
            cv.put(UserTable.DISTRICT_ID.getValue(), user.getDistrictId());
            cv.put(UserTable.USER_TYPE.getValue(), user.getUserType());
            cv.put(UserTable.LAST_LOGIN.getValue(), user.getLastLogin().toString());
            cv.put(UserTable.VERIFIED.getValue(), user.isVerified());
            cv.put(UserTable.VERIFICATION_CODE.getValue(), user.getVerificationCode());
            cv.put(UserTable.CREATED.getValue(), user.getCreatedDate().toString());
            cv.put(UserTable.UPDATED.getValue(), user.getUpdatedDate().toString());
            cv.put(UserTable.DELETED.getValue(), user.getDeletedDate().toString());

            return db.update(UserTable.TABLE_NAME.getValue(), cv, UserTable.USER_ID + "=?", new String[]{String.valueOf(user.getId())});
        }
    }

    public long deleteUser(User user) {
        if (user.getId() <= 0) {
            return -1;
        } else {
            db = databaseHelper.getWritableDatabase();
            return db.delete(UserTable.TABLE_NAME.getValue(), UserTable.USER_ID + "=?", new String[]{String.valueOf(user.getId())});
        }
    }

    public User getUser(String username, String password) {
        if (username == null || username.length() == 0 || password == null || password.length() == 0) {
            return null;
        } else {
            User user = null;
            db = databaseHelper.getWritableDatabase();
            Cursor cursor = db.query(UserTable.TABLE_NAME.getValue(),
                    new String[]{UserTable.USER_ID.getValue(),
                            UserTable.USERNAME.getValue(),
                            UserTable.PASSWORD.getValue(),
                            UserTable.EMAIL.getValue(),
                            UserTable.PHONE_NUMBER.getValue(),
                            UserTable.STATUS.getValue(),
                            UserTable.DISTRICT_ID.getValue(),
                            UserTable.USER_TYPE.getValue(),
                            UserTable.LAST_LOGIN.getValue(),
                            UserTable.VERIFIED.getValue(),
                            UserTable.VERIFICATION_CODE.getValue(),
                            UserTable.CREATED.getValue(),
                            UserTable.UPDATED.getValue(),
                            UserTable.DELETED.getValue()},
                    UserTable.USERNAME + "=? and " + UserTable.PASSWORD + "=?",
                    new String[]{username, password},
                    null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                user = new User(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5),
                        cursor.getInt(6),
                        cursor.getInt(7),
                        DateUtils.convertToTimestamp(cursor.getString(8)),
                        cursor.getInt(9) > 0,
                        cursor.getString(10),
                        DateUtils.convertToTimestamp(cursor.getString(11)),
                        DateUtils.convertToTimestamp(cursor.getString(12)),
                        DateUtils.convertToTimestamp(cursor.getString(13)));
            }
            return user;
        }
    }


}
