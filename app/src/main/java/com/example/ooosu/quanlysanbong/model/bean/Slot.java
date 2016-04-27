package com.example.ooosu.quanlysanbong.model.bean;

import java.sql.Timestamp;

/**
 * Created by Noname on 26/4/2016.
 */
public class Slot {
    private int matchId;
    private int userId;
    private int quantity;
    private boolean verified;
    private String verificationCode;
    private Timestamp createdDate;
    private Timestamp updatedDate;
    private Timestamp deletedDate;

    public Slot() {
    }

    public Slot(int matchId, int userId, int quantity, boolean verified, String verificationCode, Timestamp createdDate, Timestamp updatedDate, Timestamp deletedDate) {
        this.matchId = matchId;
        this.userId = userId;
        this.quantity = quantity;
        this.verified = verified;
        this.verificationCode = verificationCode;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deletedDate = deletedDate;
    }

    public Slot(int userId, int quantity, boolean verified, String verificationCode, Timestamp createdDate, Timestamp updatedDate, Timestamp deletedDate) {
        this.userId = userId;
        this.quantity = quantity;
        this.verified = verified;
        this.verificationCode = verificationCode;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deletedDate = deletedDate;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Timestamp getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Timestamp deletedDate) {
        this.deletedDate = deletedDate;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "matchId=" + matchId +
                ", userId=" + userId +
                ", quantity=" + quantity +
                ", verified=" + verified +
                ", verificationCode='" + verificationCode + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", deletedDate=" + deletedDate +
                '}';
    }
}
