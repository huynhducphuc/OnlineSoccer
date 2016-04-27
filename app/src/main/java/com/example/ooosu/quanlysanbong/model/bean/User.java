package com.example.ooosu.quanlysanbong.model.bean;

import java.sql.Timestamp;

/**
 * Created by Noname on 26/4/2016.
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private int status;
    private int districtId;
    private int userType;
    private Timestamp lastLogin;
    private boolean verified;
    private String verificationCode;
    private Timestamp createdDate;
    private Timestamp updatedDate;
    private Timestamp deletedDate;

    public User() {
    }

    public User(int id, String username, String password, String email, String phoneNumber, int status, int districtId, int userType, Timestamp lastLogin, boolean verified, String verificationCode, Timestamp createdDate, Timestamp updatedDate, Timestamp deletedDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.districtId = districtId;
        this.userType = userType;
        this.lastLogin = lastLogin;
        this.verified = verified;
        this.verificationCode = verificationCode;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deletedDate = deletedDate;
    }

    public User(String username, String password, String email, String phoneNumber, int status, int districtId, int userType, Timestamp lastLogin, boolean verified, String verificationCode, Timestamp createdDate, Timestamp updatedDate, Timestamp deletedDate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.districtId = districtId;
        this.userType = userType;
        this.lastLogin = lastLogin;
        this.verified = verified;
        this.verificationCode = verificationCode;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deletedDate = deletedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
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
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", status=" + status +
                ", districtId=" + districtId +
                ", userType=" + userType +
                ", lastLogin=" + lastLogin +
                ", verified=" + verified +
                ", verificationCode='" + verificationCode + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", deletedDate=" + deletedDate +
                '}';
    }
}
