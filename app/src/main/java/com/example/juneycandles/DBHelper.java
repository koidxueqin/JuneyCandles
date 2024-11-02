package com.example.juneycandles;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {
    public static final String DBName = "JuneyCandlesDB.db";

    public DBHelper(@Nullable Context context) {
        super(context, DBName, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Customer (phone_no TEXT PRIMARY KEY, name TEXT NOT NULL, email TEXT NOT NULL UNIQUE, address TEXT, password TEXT NOT NULL)");
        Log.d("DBHelper", "Database and Customer table created successfully.");
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Drop the existing Customer table if it exists
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Customer");
        onCreate(sqLiteDatabase); // Recreate the table with the new schema
    }



    public boolean insertData(String name, String phone_no, String email, String address, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("phone_no",phone_no);
        contentValues.put("email",email);
        contentValues.put("address",address);
        contentValues.put("password",password);
        long result = myDB.insert("Customer",null, contentValues);
        return result != -1;
    }

    public boolean checkUser(String phone) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        try (Cursor cursor = myDB.rawQuery("select * from Customer where phone_no = ?", new String[]{phone})) {
            return cursor.getCount() > 0;
        }
    }


    public boolean isDatabaseConnected() {
        SQLiteDatabase db = null;
        try {
            db = this.getReadableDatabase(); // Get a readable database
            return db.isOpen(); // Returns true if the database is open
        } catch (Exception e) {
            Log.e("DBConnectionError", "Database connection failed", e); // Log the error message
            return false;
        } finally {
            if (db != null && db.isOpen()) {
                db.close(); // Close the database if it's open
            }
        }
    }

    public boolean checkUser (String phone, String pwd){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from Customer where phone_no = ? and password = ?",new String[]{phone,pwd});
        return cursor.getCount() > 0;
    }



}
