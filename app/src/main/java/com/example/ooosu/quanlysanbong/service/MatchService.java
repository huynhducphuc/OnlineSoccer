package com.example.ooosu.quanlysanbong.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ooosu.quanlysanbong.dbhelper.DatabaseHelper;
import com.example.ooosu.quanlysanbong.dbhelper.MatchTable;
import com.example.ooosu.quanlysanbong.dbhelper.SlotTable;
import com.example.ooosu.quanlysanbong.dbhelper.UserTable;
import com.example.ooosu.quanlysanbong.model.bean.Match;
import com.example.ooosu.quanlysanbong.utils.DateUtils;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Noname on 27/4/2016.
 */
public class MatchService {
    private DatabaseHelper databaseHelper = null;
    private SQLiteDatabase db = null;

    public MatchService(Context context) {
        this.databaseHelper = new DatabaseHelper(context);
    }

    public Match getMatch(int id) {
        Match match = null;
        if (id <= 0) {
            return null;
        } else {
            db = databaseHelper.getWritableDatabase();
            Cursor cursor = db.query(MatchTable.TABLE_NAME.getValue(),
                    new String[]{MatchTable.MATCH_ID.getValue(),
                            MatchTable.FIELD_ID.getValue(),
                            MatchTable.HOST_ID.getValue(),
                            MatchTable.STATUS.getValue(),
                            MatchTable.MAX_PLAYERS.getValue(),
                            MatchTable.PRICE.getValue(),
                            MatchTable.START_TIME.getValue(),
                            MatchTable.END_TIME.getValue(),
                            MatchTable.VERIFIED.getValue(),
                            MatchTable.VERIFICATION_CODE.getValue(),
                            MatchTable.CREATED.getValue(),
                            MatchTable.UPDATED.getValue(),
                            MatchTable.DELETED.getValue()},
                    MatchTable.MATCH_ID + "=?",
                    new String[]{String.valueOf(id)},
                    null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                match = new Match(cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        cursor.getInt(5),
                        DateUtils.convertToTimestamp(cursor.getString(6), DateUtils.FOR_DATABASE),
                        DateUtils.convertToTimestamp(cursor.getString(7), DateUtils.FOR_DATABASE),
                        cursor.getInt(8) > 0,
                        cursor.getString(9),
                        DateUtils.convertToTimestamp(cursor.getString(10), DateUtils.FOR_DATABASE),
                        DateUtils.convertToTimestamp(cursor.getString(11), DateUtils.FOR_DATABASE),
                        DateUtils.convertToTimestamp(cursor.getString(12), DateUtils.FOR_DATABASE));
            }
            return match;
        }

    }

    public List<Match> getAllMatches() {
        List<Match> results = new ArrayList<>();
        Match match = null;
        db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.query(MatchTable.TABLE_NAME.getValue(),
                new String[]{MatchTable.MATCH_ID.getValue(),
                        MatchTable.FIELD_ID.getValue(),
                        MatchTable.HOST_ID.getValue(),
                        MatchTable.STATUS.getValue(),
                        MatchTable.MAX_PLAYERS.getValue(),
                        MatchTable.PRICE.getValue(),
                        MatchTable.START_TIME.getValue(),
                        MatchTable.END_TIME.getValue(),
                        MatchTable.VERIFIED.getValue(),
                        MatchTable.VERIFICATION_CODE.getValue(),
                        MatchTable.CREATED.getValue(),
                        MatchTable.UPDATED.getValue(),
                        MatchTable.DELETED.getValue()},
                null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                match = new Match(cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        cursor.getInt(5),
                        DateUtils.convertToTimestamp(cursor.getString(6), DateUtils.FOR_DATABASE),
                        DateUtils.convertToTimestamp(cursor.getString(7), DateUtils.FOR_DATABASE),
                        cursor.getInt(8) > 0,
                        cursor.getString(9),
                        DateUtils.convertToTimestamp(cursor.getString(10), DateUtils.FOR_DATABASE),
                        DateUtils.convertToTimestamp(cursor.getString(11), DateUtils.FOR_DATABASE),
                        DateUtils.convertToTimestamp(cursor.getString(12), DateUtils.FOR_DATABASE));

                results.add(match);

            } while (cursor.moveToNext());
        }

        return results;
    }

    public long addMatch(Match match) {
        db = databaseHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MatchTable.FIELD_ID.getValue(), match.getFieldId());
        cv.put(MatchTable.HOST_ID.getValue(), match.getHostId());
        cv.put(MatchTable.STATUS.getValue(), match.getStatus());
        cv.put(MatchTable.MAX_PLAYERS.getValue(), match.getMaxPlayers());
        cv.put(MatchTable.PRICE.getValue(), match.getPrice());
        cv.put(MatchTable.START_TIME.getValue(), DateUtils.formatDatetime(match.getStartTime(), DateUtils.FOR_DATABASE));
        cv.put(MatchTable.END_TIME.getValue(), DateUtils.formatDatetime(match.getEndTime(), DateUtils.FOR_DATABASE));
        cv.put(MatchTable.VERIFIED.getValue(), match.isVerified());
        cv.put(MatchTable.VERIFICATION_CODE.getValue(), match.getVerificationCode());
        cv.put(MatchTable.CREATED.getValue(), DateUtils.formatDatetime(match.getCreatedDate(), DateUtils.FOR_DATABASE));
        cv.put(MatchTable.UPDATED.getValue(), DateUtils.formatDatetime(match.getUpdatedDate(), DateUtils.FOR_DATABASE));
        cv.put(MatchTable.DELETED.getValue(), DateUtils.formatDatetime(match.getDeletedDate(), DateUtils.FOR_DATABASE));

        return db.insert(MatchTable.TABLE_NAME.getValue(), null, cv);
    }

    public long updateMatch(Match match) {
        if (match.getId() <= 0) {
            return -1;
        } else {
            db = databaseHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(MatchTable.FIELD_ID.getValue(), match.getFieldId());
            cv.put(MatchTable.HOST_ID.getValue(), match.getHostId());
            cv.put(MatchTable.STATUS.getValue(), match.getStatus());
            cv.put(MatchTable.MAX_PLAYERS.getValue(), match.getMaxPlayers());
            cv.put(MatchTable.PRICE.getValue(), match.getPrice());
            cv.put(MatchTable.START_TIME.getValue(), DateUtils.formatDatetime(match.getStartTime(), DateUtils.FOR_DATABASE));
            cv.put(MatchTable.END_TIME.getValue(), DateUtils.formatDatetime(match.getEndTime(), DateUtils.FOR_DATABASE));
            cv.put(MatchTable.VERIFIED.getValue(), match.isVerified());
            cv.put(MatchTable.VERIFICATION_CODE.getValue(), match.getVerificationCode());
            cv.put(MatchTable.CREATED.getValue(), DateUtils.formatDatetime(match.getCreatedDate(), DateUtils.FOR_DATABASE));
            cv.put(MatchTable.UPDATED.getValue(), DateUtils.formatDatetime(match.getUpdatedDate(), DateUtils.FOR_DATABASE));
            cv.put(MatchTable.DELETED.getValue(), DateUtils.formatDatetime(match.getDeletedDate(), DateUtils.FOR_DATABASE));

            return db.update(MatchTable.TABLE_NAME.getValue(), cv, MatchTable.MATCH_ID.getValue() + "=?", new String[]{String.valueOf(match.getId())});
        }
    }

    public long deleteMatch(Match match) {
        if (match.getId() <= 0) {
            return -1;
        } else {
            db = databaseHelper.getWritableDatabase();

            return db.delete(MatchTable.TABLE_NAME.getValue(), MatchTable.MATCH_ID.getValue() + "=?", new String[]{String.valueOf(match.getId())});
        }
    }

    public List<Match> getAvailableMatches() {
        List<Match> results = new ArrayList<>();
        Match match = null;
        db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.query(MatchTable.TABLE_NAME.getValue(),
                new String[]{MatchTable.MATCH_ID.getValue(),
                        MatchTable.FIELD_ID.getValue(),
                        MatchTable.HOST_ID.getValue(),
                        MatchTable.STATUS.getValue(),
                        MatchTable.MAX_PLAYERS.getValue(),
                        MatchTable.PRICE.getValue(),
                        MatchTable.START_TIME.getValue(),
                        MatchTable.END_TIME.getValue(),
                        MatchTable.VERIFIED.getValue(),
                        MatchTable.VERIFICATION_CODE.getValue(),
                        MatchTable.CREATED.getValue(),
                        MatchTable.UPDATED.getValue(),
                        MatchTable.DELETED.getValue()},
                MatchTable.START_TIME + ">datetime('now', '+7 hour')",
                null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                match = new Match(cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        cursor.getInt(5),
                        DateUtils.convertToTimestamp(cursor.getString(6), DateUtils.FOR_DATABASE),
                        DateUtils.convertToTimestamp(cursor.getString(7), DateUtils.FOR_DATABASE),
                        cursor.getInt(8) > 0,
                        cursor.getString(9),
                        DateUtils.convertToTimestamp(cursor.getString(10), DateUtils.FOR_DATABASE),
                        DateUtils.convertToTimestamp(cursor.getString(11), DateUtils.FOR_DATABASE),
                        DateUtils.convertToTimestamp(cursor.getString(12), DateUtils.FOR_DATABASE));

                results.add(match);

            } while (cursor.moveToNext());
        }

        return results;
    }

    public List<Match> getAllMatches(int hostId) {
        List<Match> results = new ArrayList<>();
        Match match = null;
        db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.query(MatchTable.TABLE_NAME.getValue(),
                new String[]{MatchTable.MATCH_ID.getValue(),
                        MatchTable.FIELD_ID.getValue(),
                        MatchTable.HOST_ID.getValue(),
                        MatchTable.STATUS.getValue(),
                        MatchTable.MAX_PLAYERS.getValue(),
                        MatchTable.PRICE.getValue(),
                        MatchTable.START_TIME.getValue(),
                        MatchTable.END_TIME.getValue(),
                        MatchTable.VERIFIED.getValue(),
                        MatchTable.VERIFICATION_CODE.getValue(),
                        MatchTable.CREATED.getValue(),
                        MatchTable.UPDATED.getValue(),
                        MatchTable.DELETED.getValue()},
                MatchTable.HOST_ID + "=?",
                new String[]{String.valueOf(hostId)},
                null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                match = new Match(cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        cursor.getInt(5),
                        DateUtils.convertToTimestamp(cursor.getString(6), DateUtils.FOR_DATABASE),
                        DateUtils.convertToTimestamp(cursor.getString(7), DateUtils.FOR_DATABASE),
                        cursor.getInt(8) > 0,
                        cursor.getString(9),
                        DateUtils.convertToTimestamp(cursor.getString(10), DateUtils.FOR_DATABASE),
                        DateUtils.convertToTimestamp(cursor.getString(11), DateUtils.FOR_DATABASE),
                        DateUtils.convertToTimestamp(cursor.getString(12), DateUtils.FOR_DATABASE));

                results.add(match);

            } while (cursor.moveToNext());
        }

        return results;
    }

    public List<Match> getOldMatches(int userId) {
        List<Match> results = new ArrayList<>();
        Match match = null;
        db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT m.match_id,m.field_id,m.host_id,m.status,m.maximum_players,m.price,m.start_time,m.end_time,m.is_verified,m.verification_code,m.created,m.updated,m.deleted FROM matches m join slots s on(m.match_id=s.match_id) join users u on(s.user_id=u.user_id) WHERE u.user_id=?;", new String[]{String.valueOf(userId)});

        if (cursor != null && cursor.moveToFirst()) {
            do {
                match = new Match(cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        cursor.getInt(5),
                        DateUtils.convertToTimestamp(cursor.getString(6), DateUtils.FOR_DATABASE),
                        DateUtils.convertToTimestamp(cursor.getString(7), DateUtils.FOR_DATABASE),
                        cursor.getInt(8) > 0,
                        cursor.getString(9),
                        DateUtils.convertToTimestamp(cursor.getString(10), DateUtils.FOR_DATABASE),
                        DateUtils.convertToTimestamp(cursor.getString(11), DateUtils.FOR_DATABASE),
                        DateUtils.convertToTimestamp(cursor.getString(12), DateUtils.FOR_DATABASE));

                results.add(match);

            } while (cursor.moveToNext());
        }

        return results;
    }

}
