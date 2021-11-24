package com.dung.quanlythuchi.DTO;

public class User {
    int idUser;
    String name, userName, passWord, email, soDT, date;

    public static final String TB_NAME = "tb_user";
    public static final String COL_ID = "id_user";
    public static final String COL_NAME = "name";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASS = "pass";
    public static final String COL_EMAIL = "email";
    public static final String COL_PHONE = "sodt";
    public static final String COL_DATE = "date";


    public User(int idUser, String name, String userName, String passWord, String email, String soDT, String date) {
        this.idUser = idUser;
        this.name = name;
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.soDT = soDT;
        this.date = date;
    }

    public User() {
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
