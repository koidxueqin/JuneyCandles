package com.example.juneycandles;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    DBHelper dbHelper;
    Button btnLogin, forgotPwdBtn;
    EditText numberLogin, passwordLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);  // Sets content to login page layout

        dbHelper = new DBHelper(this);
        numberLogin = findViewById(R.id.numberLogin);
        passwordLogin = findViewById(R.id.passwordLogin);
        btnLogin = findViewById(R.id.btnLogin);
        forgotPwdBtn = findViewById(R.id.forgot_pwd);

        // Handle login button click
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = numberLogin.getText().toString().trim();
                String password = passwordLogin.getText().toString().trim();

                // Input validation
                if (phone.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter both phone number and password.", Toast.LENGTH_SHORT).show();
                } else {
                    // Get the customer ID based on phone and password
                    int loggedInUserId = dbHelper.getCustId(phone, password);
                    if (loggedInUserId != -1) {
                        // Store the customer ID in SharedPreferences
                        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putInt("cust_id", loggedInUserId);  // Store the customer ID
                        editor.apply();  // Save changes

                        // Log the saved customer ID
                        Log.d("LoginActivity", "Customer ID saved: " + loggedInUserId);

                        // Retrieve the saved customer ID to verify
                        int savedCustId = preferences.getInt("cust_id", -1);
                        Log.d("LoginActivity", "Retrieved Customer ID: " + savedCustId);

                        // After successful login, navigate to HomePageActivity
                        Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                        startActivity(intent);
                        finish();  // Close LoginActivity
                    } else {
                        Toast.makeText(LoginActivity.this, "Login Failed. Incorrect phone number or password.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Handle Forgot Password button click
        forgotPwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(intent);
            }
        });

        // Handle Sign Up button click
        Button signUpButton = findViewById(R.id.signUp);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}
