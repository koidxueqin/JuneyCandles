package com.example.juneycandles;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setting layout
        setContentView(R.layout.welcome_page);

        TextView userNameText = findViewById(R.id.userNameText);

        // Get the user's name from the Intent
        String userName = getIntent().getStringExtra("userName");

        if (userName != null) {
            userNameText.setText(userName); // Set the user's name
        }

        // Set a click listener on the main layout to transition to the login page (if needed)
        findViewById(R.id.main).setOnClickListener(v -> {
            Intent intent = new Intent(WelcomeActivity.this, HomePageActivity.class);
            startActivity(intent);
        });

    }

    private String getUserName() {
        String name = null;
        // Query to fetch the user's name based on the last inserted entry or specific criteria
        Cursor cursor = database.rawQuery("SELECT name FROM Customer ORDER BY phone_no DESC LIMIT 1", null);

            if (cursor.moveToFirst()) {
                int nameIndex = cursor.getColumnIndex("name"); // Get the index of the column
                if (nameIndex != -1) { // Ensure the index is valid
                    name = cursor.getString(nameIndex);
                }
            }
            cursor.close(); // Close the cursor

        return name; // Return the fetched name
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (database != null && database.isOpen()) {
            database.close(); // Close the database to free up resources
        }
    }


}
