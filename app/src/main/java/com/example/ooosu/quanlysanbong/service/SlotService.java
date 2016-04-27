package com.example.ooosu.quanlysanbong.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ooosu.quanlysanbong.dbhelper.DatabaseHelper;
import com.example.ooosu.quanlysanbong.dbhelper.SlotTable;
import com.example.ooosu.quanlysanbong.model.bean.Slot;
import com.example.ooosu.quanlysanbong.utils.DateUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Noname on 27/4/2016.
 */
public class SlotService {
    private DatabaseHelper databaseHelper = null;
    private SQLiteDatabase db = null;

    public SlotService(Context context) {
        this.databaseHelper = new DatabaseHelper(context);
    }

    public Slot getSlot(int matchId, int userId) {
        Slot slot = null;
        if (matchId <= 0 || userId <= 0) {
            return null;
        } else {
            db = databaseHelper.getWritableDatabase();
            Cursor cursor = db.query(SlotTable.TABLE_NAME.getValue(),
                    new String[] {SlotTable.MATCH_ID.getValue(),
                            SlotTable.USER_ID.getValue(),
                            SlotTable.QUANTITY.getValue(),
                            SlotTable.VERIFIED.getValue(),
                            SlotTable.VERIFICATION_CODE.getValue(),
                            SlotTable.CREATED.getValue(),
                            SlotTable.UPDATED.getValue(),
                            SlotTable.DELETED.getValue()},
                    SlotTable.MATCH_ID.getValue() + "=? and " + SlotTable.USER_ID.getValue() + "=?",
                    new String[]{String.valueOf(matchId), String.valueOf(userId)},
                    null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                slot = new Slot(cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getInt(3) > 0,
                        cursor.getString(4),
                        DateUtils.convertToTimestamp(cursor.getString(5), DateUtils.FOR_DATABASE),
                        DateUtils.convertToTimestamp(cursor.getString(6), DateUtils.FOR_DATABASE),
                        DateUtils.convertToTimestamp(cursor.getString(7), DateUtils.FOR_DATABASE));
            }
            return slot;
        }
    }

    public List<Slot> getAllSlots() {
        List<Slot> results = new ArrayList<>();
        Slot slot = null;
        db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.query(SlotTable.TABLE_NAME.getValue(),
                new String[] {SlotTable.MATCH_ID.getValue(),
                        SlotTable.USER_ID.getValue(),
                        SlotTable.QUANTITY.getValue(),
                        SlotTable.VERIFIED.getValue(),
                        SlotTable.VERIFICATION_CODE.getValue(),
                        SlotTable.CREATED.getValue(),
                        SlotTable.UPDATED.getValue(),
                        SlotTable.DELETED.getValue()},
                null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                slot = new Slot(cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getInt(3) > 0,
                        cursor.getString(4),
                        DateUtils.convertToTimestamp(cursor.getString(5), DateUtils.FOR_DATABASE),
                        DateUtils.convertToTimestamp(cursor.getString(6), DateUtils.FOR_DATABASE),
                        DateUtils.convertToTimestamp(cursor.getString(7), DateUtils.FOR_DATABASE));

                results.add(slot);

            } while (cursor.moveToNext());
        }
        return results;
    }

    public long addSlot(Slot slot) {
        db = databaseHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SlotTable.MATCH_ID.getValue(), slot.getMatchId());
        cv.put(SlotTable.USER_ID.getValue(), slot.getUserId());
        cv.put(SlotTable.QUANTITY.getValue(), slot.getQuantity());
        cv.put(SlotTable.VERIFIED.getValue(), slot.isVerified());
        cv.put(SlotTable.VERIFICATION_CODE.getValue(), slot.getVerificationCode());
        cv.put(SlotTable.CREATED.getValue(), DateUtils.formatDatetime(slot.getCreatedDate(), DateUtils.FOR_DATABASE));

        return db.insert(SlotTable.TABLE_NAME.getValue(), null, cv);
    }

    public long updateSlot(Slot slot) {
        if (slot.getMatchId() <= 0 || slot.getUserId() <= 0) {
            return -1;
        } else {
            db = databaseHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(SlotTable.MATCH_ID.getValue(), slot.getMatchId());
            cv.put(SlotTable.USER_ID.getValue(), slot.getUserId());
            cv.put(SlotTable.QUANTITY.getValue(), slot.getQuantity());
            cv.put(SlotTable.VERIFIED.getValue(), slot.isVerified());
            cv.put(SlotTable.UPDATED.getValue(), DateUtils.formatDatetime(slot.getUpdatedDate(), DateUtils.FOR_DATABASE));

            return db.update(SlotTable.TABLE_NAME.getValue(),
                    cv,
                    SlotTable.MATCH_ID.getValue() + "=? and " + SlotTable.USER_ID.getValue() + "=?",
                    new String[]{String.valueOf(slot.getMatchId()), String.valueOf(slot.getUserId())});
        }
    }

    public long deleteSlot(int matchId, int userId) {
        if (matchId <= 0 || userId <= 0) {
            return -1;
        } else {
            db = databaseHelper.getWritableDatabase();
            return db.delete(SlotTable.TABLE_NAME.getValue(),
                    SlotTable.MATCH_ID.getValue() + "=? and " + SlotTable.USER_ID.getValue() + "=?",
                    new String[]{String.valueOf(matchId), String.valueOf(userId)});
        }
    }

    public long countSlots(int matchId) {
        if (matchId <= 0) {
            return 0;
        } else {
            db = databaseHelper.getWritableDatabase();
            Cursor cursor = db.query(SlotTable.TABLE_NAME.getValue(),
                    null,
                    SlotTable.MATCH_ID + "=?",
                    new String[] {String.valueOf(matchId)},
                    null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                return cursor.getLong(0);
            } else {
                return 0;
            }
        }
    }

}
