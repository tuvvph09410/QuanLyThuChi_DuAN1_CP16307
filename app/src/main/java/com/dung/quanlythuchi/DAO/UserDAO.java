package com.dung.quanlythuchi.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dung.quanlythuchi.DTO.User;
import com.dung.quanlythuchi.database.MyDBHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class UserDAO {
    SQLiteDatabase db;
    MyDBHelper myDBHelper;
    SimpleDateFormat simpleDateFormat;

    public UserDAO(Context context) {
        myDBHelper = new MyDBHelper(context);
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    }

    public void openDB() {
        db = myDBHelper.getWritableDatabase();
    }

    public void closeDB() {
        myDBHelper.close();
    }

    public long insertRow(User objUser) {
        ContentValues values = new ContentValues();
        values.put(User.COL_NAME, objUser.getName());
        values.put(User.COL_USERNAME, objUser.getUserName());
        values.put(User.COL_PASS, objUser.getPassWord());
        values.put(User.COL_EMAIL, objUser.getEmail());
        values.put(User.COL_PHONE, objUser.getSoDT());
        values.put(User.COL_DATE, simpleDateFormat.format(objUser.getDate()));

        long res = db.insert(User.TB_NAME, null, values);
        return res;
    }

    public int updateRow(User objUser) {
        String[] mang_tham_so = new String[]{objUser.getIdUser() + ""};
        ContentValues values = new ContentValues();
        values.put(User.COL_NAME, objUser.getName());
        values.put(User.COL_USERNAME, objUser.getUserName());
        values.put(User.COL_PASS, objUser.getPassWord());
        values.put(User.COL_EMAIL, objUser.getEmail());
        values.put(User.COL_PHONE, objUser.getSoDT());
        values.put(User.COL_DATE, simpleDateFormat.format(objUser.getDate()));
        int res = db.update(User.TB_NAME, values, "id_user=?", mang_tham_so);
        return res;
    }

    public int deleteRow(User objUser) {
        String[] mang_tham_so = new String[]{objUser.getIdUser() + ""};
        int res = db.delete(User.TB_NAME, "id_user=?", mang_tham_so);
        return res;
    }

    public boolean kiemTraLogin(String username, String pass) {
        String ktra = "SELECT * FROM " + User.TB_NAME + " WHERE " + User.COL_USERNAME + " = '" + username
                + "' AND " + User.COL_PASS + " = '" + pass + "'";
        Cursor cursor = db.rawQuery(ktra, null);
        if (cursor.getCount() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public User getEmail(String username) {

        String selectEmail = "SELECT * FROM " + User.TB_NAME + " WHERE " + User.COL_USERNAME + " = '" + username
                + "' ";
        Cursor cursor = db.rawQuery(selectEmail, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
        User user=new User();
            user.setEmail(cursor.getString(cursor.getColumnIndex(User.COL_EMAIL)));
            return user;
        }
        return null;
    }

    public ArrayList<User> selectAll() {
        ArrayList<User> arrUser = new ArrayList<User>();
        String[] ds_cot = new String[]{"*"};
        Cursor cursor = db.query(User.TB_NAME, ds_cot, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                User objUser = new User();
                objUser.setIdUser(cursor.getInt(0));
                objUser.setName(cursor.getString(1));
                objUser.setUserName(cursor.getString(2));
                objUser.setPassWord(cursor.getString(3));
                objUser.setEmail(cursor.getString(4));
                objUser.setSoDT(Integer.parseInt(cursor.getString(5)));

                try {
                    objUser.setDate(simpleDateFormat.parse(cursor.getString(6)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                arrUser.add(objUser);
                cursor.moveToNext();
            }
        }
        return arrUser;
    }
}
