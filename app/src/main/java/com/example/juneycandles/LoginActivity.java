package com.example.juneycandles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    DBHelper dbHelper;
    Button btnLogin, forgotPwdBtn;
    EditText numberLogin, passwordLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);  // sets content to login page layout
        dbHelper = new DBHelper(this);
        numberLogin = findViewById(R.id.numberLogin);
        passwordLogin = findViewById(R.id.passwordLogin);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = numberLogin.getText().toString().trim();
                String password = passwordLogin.getText().toString().trim();

                // Input validation
                if (phone.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter both phone number and password.", Toast.LENGTH_SHORT).show();
                }else{

                boolean loggedIn = dbHelper.checkUser(numberLogin.getText().toString(), passwordLogin.getText().toString());
                if (loggedIn){
                    Intent intent = new Intent(LoginActivity.this,HomePageActivity.class);
                    startActivity(intent);
                    finish();
                } else
                    Toast.makeText(LoginActivity.this, "Login Failed. Incorrect phone number or password.", Toast.LENGTH_SHORT).show();
            }}
        });

        // Find the "Forgot Password" button
        forgotPwdBtn = findViewById(R.id.forgot_pwd);

        // Set OnClickListener on the "Forgot Password" button
        forgotPwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to ForgotPassword activity
                Intent intent = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(intent);
            }
        });






        // Find the "SIGN UP" Button by its ID
        Button signUpButton = findViewById(R.id.signUp);  // Make sure R.id.signUp matches the ID in your layout XML

        // Set OnClickListener on the TextView
        signUpButton.setOnClickListener(new View.OnClickListener() {  // Corrected method name
            @Override
            public void onClick(View v) {
                // Start the SignUpActivity when the TextView is clicked
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });







    }
}
