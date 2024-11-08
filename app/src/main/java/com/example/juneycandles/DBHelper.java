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
    private static final int DB_VERSION = 3;

    public DBHelper(@Nullable Context context) {
        super(context, DBName, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create Customer table
        sqLiteDatabase.execSQL("CREATE TABLE Customer (phone_no TEXT PRIMARY KEY, name TEXT NOT NULL, email TEXT NOT NULL UNIQUE, address TEXT, password TEXT NOT NULL)");

        // Create Products table
        sqLiteDatabase.execSQL("CREATE TABLE Products (product_id INTEGER PRIMARY KEY AUTOINCREMENT, product_name TEXT NOT NULL, product_price REAL NOT NULL, product_img TEXT NOT NULL)");

        Log.d("DBHelper", "Database and tables created successfully.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Drop existing tables if they exist
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Customer");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Products");

        // Recreate tables
        onCreate(sqLiteDatabase);
    }

    // Insert a new customer into the Customer table
    public boolean insertData(String name, String phone_no, String email, String address, String password) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone_no", phone_no);
        contentValues.put("email", email);
        contentValues.put("address", address);
        contentValues.put("password", password);
        long result = myDB.insert("Customer", null, contentValues);
        return result != -1;
    }

    // Insert a new product into the Products table
    public boolean insertProduct(String name, double price, String imgName) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("product_name", name);
        contentValues.put("product_price", price);
        contentValues.put("product_img", imgName);
        long result = myDB.insert("Products", null, contentValues);
        return result != -1;
    }

    // Retrieve all products ordered by product_id (ascending)
    public Cursor getAllProducts() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query("Products", null, null, null, null, null, "product_id ASC");
    }

    // Check if a user exists by phone number
    public boolean checkUser(String phone) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        try (Cursor cursor = myDB.rawQuery("SELECT * FROM Customer WHERE phone_no = ?", new String[]{phone})) {
            return cursor.getCount() > 0;
        }
    }

    // Check if a user exists by phone number and password
    public boolean checkUser(String phone, String pwd) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM Customer WHERE phone_no = ? AND password = ?", new String[]{phone, pwd});
        return cursor.getCount() > 0;
    }

    // Get user details by phone number
    public Cursor getUserByPhone(String phone_no) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM Customer WHERE phone_no = ?", new String[]{phone_no});
    }

    // Check if the database connection is open
    public boolean isDatabaseConnected() {
        SQLiteDatabase db = null;
        try {
            db = this.getReadableDatabase();
            return db.isOpen();
        } catch (Exception e) {
            Log.e("DBConnectionError", "Database connection failed", e);
            return false;
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }
}
