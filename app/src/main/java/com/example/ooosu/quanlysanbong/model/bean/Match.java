package com.example.ooosu.quanlysanbong.model.bean;

import java.sql.Timestamp;

/**
 * Created by Noname on 26/4/2016.
 */
public class Match {
    private int id;
    private int fieldId;
    private int hostId;
    private int status;
    private int maxPlayers;
    private int price;
    private Timestamp startTime;
    private Timestamp endTime;
    private boolean verified;
    private String verificationCode;
    private Timestamp createdDate;
    private Timestamp updatedDate;
    private Timestamp deletedDate;

    public Match() {
    }

    public Match(int id, int fieldId, int hostId, int status, int maxPlayers, int price, Timestamp startTime, Timestamp endTime, boolean verified, String verificationCode, Timestamp createdDate, Timestamp updatedDate, Timestamp deletedDate) {
        this.id = id;
        this.fieldId = fieldId;
        this.hostId = hostId;
        this.status = status;
        this.maxPlayers = maxPlayers;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
        this.verified = verified;
        this.verificationCode = verificationCode;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deletedDate = deletedDate;
    }

    public Match(int fieldId, int hostId, int status, int maxPlayers, int price, Timestamp startTime, Timestamp endTime, boolean verified, String verificationCode, Timestamp createdDate, Timestamp updatedDate, Timestamp deletedDate) {
        this.fieldId = fieldId;
        this.hostId = hostId;
        this.status = status;
        this.maxPlayers = maxPlayers;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
        this.verified = verified;
        this.verificationCode = verificationCode;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deletedDate = deletedDate;
    }

    public int getId() {
        return id;
    }

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
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
        return "Match{" +
                "id=" + id +
                ", fieldId=" + fieldId +
                ", hostId=" + hostId +
                ", status=" + status +
                ", maxPlayers=" + maxPlayers +
                ", price=" + price +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", verified=" + verified +
                ", verificationCode='" + verificationCode + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", deletedDate=" + deletedDate +
                '}';
    }
}
