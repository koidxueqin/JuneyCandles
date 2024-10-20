package com.example.juneycandles;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "JuneyCandlesDB"; // Keep your database name
    private static final int DATABASE_VERSION = 1; // Update this if you change the schema
    private static String DB_PATH; // Path to the database
    private final Context context;
    private SQLiteDatabase database;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        DB_PATH = context.getDatabasePath(DATABASE_NAME).getPath(); // Get the path to the database
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // This method is intentionally left empty because we are using a pre-existing database.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades here if needed
    }

    // Method to check if the database exists
    public boolean checkDatabase() {
        File dbFile = new File(DB_PATH);
        return dbFile.exists();
    }

    // Method to copy the database from assets to internal storage
    public void copyDatabase() throws IOException {
        InputStream input = context.getAssets().open("databases/" + DATABASE_NAME);
        OutputStream output = new FileOutputStream(DB_PATH);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = input.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }

        output.flush();
        output.close();
        input.close();
    }

    // Method to open the database
    public void openDatabase() {
        database = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    // Method to close the database
    public void close() {
        if (database != null) {
            database.close();
        }
    }

    // Method to create and open the database
    public void createDatabase() throws IOException {
        if (!checkDatabase()) {
            this.getWritableDatabase(); // Create the database
            try {
                copyDatabase(); // Copy the database from assets
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
        openDatabase(); // Open the database
    }
}
