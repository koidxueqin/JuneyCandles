package com.example.juneycandles;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PassVerification extends AppCompatActivity {

    private EditText code1Input, code2Input, code3Input, code4Input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pw_verification_page);

        // Initialize EditText fields
        code1Input = findViewById(R.id.code1_input);
        code2Input = findViewById(R.id.code2_input);
        code3Input = findViewById(R.id.code3_input);
        code4Input = findViewById(R.id.code4_input);

        // Initialize Confirm button
        Button confirmButton = findViewById(R.id.confirm_button);

        // Set an OnClickListener for the Confirm button to validate and proceed
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndProceed();
            }
        });
    }

    private void validateAndProceed() {
        String code1 = code1Input.getText().toString();
        String code2 = code2Input.getText().toString();
        String code3 = code3Input.getText().toString();
        String code4 = code4Input.getText().toString();

        // Check if any field is empty
        if (TextUtils.isEmpty(code1) || TextUtils.isEmpty(code2) || TextUtils.isEmpty(code3) || TextUtils.isEmpty(code4)) {
            Toast.makeText(PassVerification.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if each field contains exactly one numeric character
        if (!isSingleDigit(code1) || !isSingleDigit(code2) || !isSingleDigit(code3) || !isSingleDigit(code4)) {
            Toast.makeText(PassVerification.this, "Each code field must contain only one number.", Toast.LENGTH_SHORT).show();
            return;
        }

        // All validations passed, show success popup and proceed
        showSuccessDialog();
    }

    // Helper function to validate a single-digit number
    private boolean isSingleDigit(String code) {
        return code.matches("\\d") && code.length() == 1;  // Regex ensures a single numeric digit
    }

    // Show success popup dialog
    private void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Successful")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // Navigate to the login page after dismissing the dialog
                        Intent intent = new Intent(PassVerification.this, LoginActivity.class);
                        startActivity(intent);
                        finish();  // Optional: finish this activity
                    }
                });

        // Create and show the dialog
        AlertDialog alert = builder.create();
        alert.show();
    }
}
