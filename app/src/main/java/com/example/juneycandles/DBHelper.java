package com.example.juneycandles;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBName = "JuneyCandlesDB.db";

    public DBHelper(@Nullable Context context) {
        super(context, DBName, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Customer (" +
                "cust_id INTEGER PRIMARY KEY AUTOINCREMENT, " + // Added cust_id with autoincrement
                "phone TEXT UNIQUE, " +
                "name TEXT NOT NULL, " +
                "email TEXT NOT NULL UNIQUE, " +
                "address TEXT, " +
                "password TEXT NOT NULL)");
        Log.d("DBHelper", "Database and Customer table created successfully.");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Drop the existing Customer table if it exists
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Customer");
        onCreate(sqLiteDatabase); // Recreate the table with the new schema
    }



    public boolean insertData(String name, String phone, String email, String address, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("phone",phone);
        contentValues.put("email",email);
        contentValues.put("address",address);
        contentValues.put("password",password);
        long result = myDB.insert("Customer",null, contentValues);
        return result != -1;
    }

    public boolean checkUser(String phone) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        try (Cursor cursor = myDB.rawQuery("select * from Customer where phone = ?", new String[]{phone})) {
            return cursor.getCount() > 0;
        }
    }

    public int getCustId(String phone, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT cust_id FROM Customer WHERE phone = ? AND password = ?";
        Cursor cursor = db.rawQuery(query, new String[]{phone, password});

        Log.d("DBHelper", "Phone: " + phone + ", Password: " + password);


        if (cursor != null && cursor.moveToFirst()) {
            int custId = cursor.getInt(cursor.getColumnIndex("cust_id"));
            cursor.close();
            return custId;
        } else {
            cursor.close();
            return -1;  // Return -1 if user not found
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



    public boolean insertToCart(int custId, int productId, String size, String color) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("cust_id", custId);
        values.put("product_id", productId);
        values.put("size", size);
        values.put("color", color);

        long result = db.insert("Cart", null, values);  // "Cart" is your cart table name
        db.close();

        Log.d("DBHelper", "Inserting to Cart: cust_id=" + custId + ", product_id=" + productId + ", size=" + size + ", color=" + color);


        return result != -1;  // Returns true if insertion is successful
    }





}
