package com.example.juneycandles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);  // sets content to login page layout

        // Find the "SIGN UP" TextView by its ID
        TextView signUpTextView = findViewById(R.id.signUp);  // Make sure R.id.signUp matches the ID in your layout XML

        // Set OnClickListener on the TextView
        signUpTextView.setOnClickListener(new View.OnClickListener() {  // Corrected method name
            @Override
            public void onClick(View v) {
                // Start the SignUpActivity when the TextView is clicked
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}
