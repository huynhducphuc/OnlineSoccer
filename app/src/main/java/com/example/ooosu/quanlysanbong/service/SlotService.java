package com.example.ooosu.quanlysanbong.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.noname.onlinesoccer.bean.Slot;
import com.example.noname.onlinesoccer.dbhelper.DatabaseHelper;
import com.example.noname.onlinesoccer.dbhelper.SlotTable;

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
                        Timestamp.valueOf(cursor.getString(5)),
                        Timestamp.valueOf(cursor.getString(6)),
                        Timestamp.valueOf(cursor.getString(7)));
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
                        Timestamp.valueOf(cursor.getString(5)),
                        Timestamp.valueOf(cursor.getString(6)),
                        Timestamp.valueOf(cursor.getString(7)));

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
        cv.put(SlotTable.CREATED.getValue(), slot.getCreatedDate().toString());
        cv.put(SlotTable.UPDATED.getValue(), slot.getUpdatedDate().toString());
        cv.put(SlotTable.DELETED.getValue(), slot.getDeletedDate().toString());

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
            cv.put(SlotTable.VERIFICATION_CODE.getValue(), slot.getVerificationCode());
            cv.put(SlotTable.CREATED.getValue(), slot.getCreatedDate().toString());
            cv.put(SlotTable.UPDATED.getValue(), slot.getUpdatedDate().toString());
            cv.put(SlotTable.DELETED.getValue(), slot.getDeletedDate().toString());

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

}
