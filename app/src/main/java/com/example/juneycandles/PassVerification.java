package com.example.juneycandles;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PassVerification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pw_verification_page); // Link to your layout

        // Find views
        EditText code1Input = findViewById(R.id.code1_input);
        EditText code2Input = findViewById(R.id.code2_input);
        EditText code3Input = findViewById(R.id.code3_input);
        EditText code4Input = findViewById(R.id.code4_input);
        Button confirmButton = findViewById(R.id.confirm_button);

        // Set OnClickListener for the confirm button
        confirmButton.setOnClickListener(v -> {
            // Get user input from EditTexts
            String code1 = code1Input.getText().toString().trim();
            String code2 = code2Input.getText().toString().trim();
            String code3 = code3Input.getText().toString().trim();
            String code4 = code4Input.getText().toString().trim();

            // Validate inputs (Example validation logic)
            if (code1.isEmpty() || code2.isEmpty() || code3.isEmpty() || code4.isEmpty()) {
                Toast.makeText(PassVerification.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            } else {
                // Proceed to the login page
                Intent intent = new Intent(PassVerification.this, LoginActivity.class); // Update to your actual login activity
                startActivity(intent);
                finish(); // Optional: finish this activity
            }
        });
    }
}