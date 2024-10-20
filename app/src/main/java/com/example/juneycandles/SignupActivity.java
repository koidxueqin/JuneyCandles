package com.example.juneycandles;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    private EditText phoneInput, nameInput, emailInput, addressInput, passwordInput, confirmInput;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);

        // Initialize views
        phoneInput = findViewById(R.id.phoneInput);
        nameInput = findViewById(R.id.nameInput);
        emailInput = findViewById(R.id.emailInput);
        addressInput = findViewById(R.id.addressInput);
        passwordInput = findViewById(R.id.passwordInput);
        confirmInput = findViewById(R.id.confirmInput);
        Button signupButton = findViewById(R.id.signupButton);
        Button backButton = findViewById(R.id.backButton);

        // Initialize database
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        // Set onClick listener for the signup button
        signupButton.setOnClickListener(v -> signupUser());

        // Set onClick listener for the back button
        backButton.setOnClickListener(v -> {
            // Navigate back to the login page
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Finish current activity to remove it from the back stack
        });
    }

    private void signupUser() {
        // Get input values
        String phone = phoneInput.getText().toString().trim();
        String name = nameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String address = addressInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        String confirmPassword = confirmInput.getText().toString().trim();

        // Validate input fields
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(name) ||
                TextUtils.isEmpty(email) || TextUtils.isEmpty(address) ||
                TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "Please input all fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Confirm Password does not match.", Toast.LENGTH_SHORT).show(); // Updated error message
            return;
        }

        // Save user information in the database
        ContentValues values = new ContentValues();
        values.put("phone_no", phone);
        values.put("name", name);
        values.put("email", email);
        values.put("password", password);
        values.put("address", address);

        long newRowId = database.insert("Customer", null, values);
        if (newRowId != -1) {
            Toast.makeText(this, "Sign up successful!", Toast.LENGTH_SHORT).show();
            // Navigate to welcome page
            Intent intent = new Intent(SignupActivity.this, WelcomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Sign up failed. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }

}
