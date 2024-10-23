package com.example.juneycandles;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import androidx.annotation.Nullable;

public class SignupActivity extends AppCompatActivity {

    private EditText phoneInput, nameInput, emailInput, addressInput, passwordInput, confirmInput;
    private DBHelper dbHelper;

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
        Button backButton = findViewById(R.id.backButton); // Initialize backButton
        dbHelper = new DBHelper(this);


        // Check database connection
        boolean isConnected = dbHelper.isDatabaseConnected();
        if (isConnected) {
            Toast.makeText(this, "Database connection is successful!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Database connection failed!", Toast.LENGTH_SHORT).show();
        }


        // Set up the backButton click listener
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class); // Replace LoginActivity with your actual login activity name
            startActivity(intent);
            finish(); // Optional: Call finish() if you want to remove the SignupActivity from the back stack
        });

        signupButton.setOnClickListener(v -> {
            String name, phone, email, address, pwd, cfpwd;
            name = nameInput.getText().toString();
            phone = phoneInput.getText().toString();
            email = emailInput.getText().toString();
            address = addressInput.getText().toString();
            pwd = passwordInput.getText().toString();
            cfpwd = confirmInput.getText().toString();

            if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || address.isEmpty() || pwd.isEmpty() || cfpwd.isEmpty()) {
                Toast.makeText(SignupActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(SignupActivity.this, "Please enter a valid email address.", Toast.LENGTH_SHORT).show();
            } else {
                if (pwd.equals(cfpwd)) {
                    if (dbHelper.checkUser(phone)) {
                        Toast.makeText(SignupActivity.this, "This phone is already registered.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    // Proceed with registration
                    boolean signupSuccess = dbHelper.insertData(name, phone, email, address, pwd);
                    if (signupSuccess) {
                        // Navigate to WelcomeActivity and pass the user's name
                        Intent welcomeIntent = new Intent(SignupActivity.this, WelcomeActivity.class);
                        welcomeIntent.putExtra("userName", name); // Pass the user's name
                        startActivity(welcomeIntent);
                        finish(); // Finish SignupActivity
                        Toast.makeText(SignupActivity.this, "Signed Up Successfully!", Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(SignupActivity.this, "Sign Up Failed. Please try again.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignupActivity.this, "Confirm password does not match Password.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
