package com.dung.quanlythuchi.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {

    public  static  final  String DB_NAME = "qluser";
    public  static  final int DB_VERSION =1;

    public MyDBHelper( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_tb_user = "CREATE TABLE tb_user ( id_user INTEGER NOT NULL, name TEXT NOT NULL, username TEXT NOT NULL, pass TEXT NOT NULL, email TEXT NOT NULL, sodt INTERGER NOT NULL, date DATE NOT NULL, PRIMARY KEY(id_user AUTOINCREMENT) )";
        sqLiteDatabase.execSQL(create_tb_user);
        Log.d("zzzzz", "gọi đến hàm tạo bảng user");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
