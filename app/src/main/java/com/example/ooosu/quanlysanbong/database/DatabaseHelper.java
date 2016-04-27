package com.example.ooosu.quanlysanbong.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.util.Log;

import com.example.ooosu.quanlysanbong.model.bean.User;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by VietHoang on 07/04/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "SQLite";
    // Phien ban
    private static final int DATABASE_VERSION = 3;

    // Ten co so du lieu.
    private static final String DATABASE_NAME = "Android_db";

    // Tên bảng: Note.
    private static final String TABLE_USER = "tbl_User";
    private static final String COLUMN_USER_ID ="user_id";
    private static final String COLUMN_USER_USERNAME = "username";
    private static final String COLUMN_USER_PASSWORD = "password";
    private static final String COLUMN_USER_EMAIL="email";
    private static final String COLUMN_USER_PHONENUMBER = "phonenumber";
    private static final String COLUMN_USER_ADDRESS = "address";

    //private SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Tạo các bảng.
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");
        // Script tạo bảng.
        String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + " ("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_USERNAME + " TEXT (50) NOT NULL,"
                + COLUMN_USER_PASSWORD + " TEXT (50) NOT NULL," + COLUMN_USER_EMAIL + " TEXT (50) NOT NULL,"
                + COLUMN_USER_PHONENUMBER + " TEXT (50) NOT NULL," + COLUMN_USER_ADDRESS + " TEXT (50) NOT NULL);";
        //String CREATE_TABLE_USER = "CREATE TABLE tbl_User (user_id INTEGER PRIMARY KEY AUTOINCREMENT,username TEXT (50) NOT NULL,password TEXT (50) NOT NULL,email TEXT (50) NOT NULL,phonenumber TEXT (50) NOT NULL,address TEXT (50) NOT NULL);";
        // Chạy lệnh tạo bảng.
        db.execSQL(CREATE_TABLE_USER);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "MyDatabaseHelper.onUpgrade ... ");
        // Hủy (drop) bảng cũ nếu nó đã tồn tại.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        // Và tạo lại.
        onCreate(db);
    }

//    //open database
//    public void open() {
//        try {
//            db = getWritableDatabase();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    // close database
//    public void close() {
//        if (db != null && db.isOpen()) {
//            try {
//                db.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
    public List<User> getAllUser() {
        Log.i(TAG, "MyDatabaseHelper.getAllUser ... " );

        List<User> userList = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                User user = new User(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),
                        cursor.getString(3),cursor.getString(4),cursor.getString(5));
                // Thêm vào danh sách.
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return user list
        return userList;
    }

    public User getUser(int id) {
        Log.i(TAG, "MyDatabaseHelper.getNote ... " + id);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, new String[]{COLUMN_USER_ID, COLUMN_USER_USERNAME, COLUMN_USER_PASSWORD,
                        COLUMN_USER_EMAIL, COLUMN_USER_PHONENUMBER, COLUMN_USER_ADDRESS }, COLUMN_USER_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        User user = new User(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),
                cursor.getString(3),cursor.getString(4),cursor.getString(5));
        // return user
        db.close();
        cursor.close();
        return user;
    }

    public void addUser(User user) {
        Log.i(TAG, "MyDatabaseHelper.addUser ... " + user.getUsername());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_USERNAME, user.getUsername());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PHONENUMBER, user.getPhone());
        values.put(COLUMN_USER_ADDRESS, user.getAddress());

        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_USER, null, values);
        // Đóng kết nối database.
        db.close();
    }
    public int updateUser(User user) {
        Log.i(TAG, "MyDatabaseHelper.updateUser ... "  + user.getUsername());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_USERNAME, user.getUsername());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PHONENUMBER, user.getPhone());
        values.put(COLUMN_USER_ADDRESS, user.getAddress());

        // updating row
        return db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getUserid())});
    }

    public void deleteUser(User user) {
        Log.i(TAG, "MyDatabaseHelper.deleteUser ... " + user.getUsername() );

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getUserid())});
        db.close();
    }
    public int getUserCount() {
        Log.i(TAG, "MyDatabaseHelper.getUserCount ... " );

        String countQuery = "SELECT  * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        db.close();
        cursor.close();

        // return count
        return count;
    }
    public String getSinlgeEntry(String userName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.query(TABLE_USER, null, " username=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return null;
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("password"));
        cursor.close();
        return password;
    }
}
