package com.example.juneycandles;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpw_page);// Link to your layout



        // Find views
        EditText newPasswordInput = findViewById(R.id.new_password_input);
        EditText confirmPasswordInput = findViewById(R.id.pw2_input);
        Button confirmButton = findViewById(R.id.confirm_button);
        Button backToLoginButton = findViewById(R.id.backIcon);

        // Set OnClickListener for the confirm button
        confirmButton.setOnClickListener(v -> {
            // Validate password fields and handle logic here
            String newPassword = newPasswordInput.getText().toString();
            String confirmPassword = confirmPasswordInput.getText().toString();

            // Simple validation (you can expand this)
            if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
                // Show error (e.g., using Toast)
                Toast.makeText(ForgotPassword.this, "Please fill in both fields.", Toast.LENGTH_SHORT).show();
            } else if (!newPassword.equals(confirmPassword)) {
                // Show error for mismatched passwords
                Toast.makeText(ForgotPassword.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
            } else {
                // Proceed to the verification page
                Intent intent = new Intent(ForgotPassword.this, PassVerification.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for the back to login button
        backToLoginButton.setOnClickListener(v -> {
            // Navigate back to the login activity
            Intent intent = new Intent(ForgotPassword.this, LoginActivity.class); // Update to your login activity
            startActivity(intent);
            finish(); // Optional: finish this activity
        });
    }
}