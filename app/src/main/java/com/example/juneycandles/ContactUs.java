package com.example.juneycandles;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ContactUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_contactus);

        // Find the back button by ID, if you have one in your layout
        Button backButton = findViewById(R.id.backIcon);

        // Set an OnClickListener for the back button to close the activity
        if (backButton != null) {
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();  // Closes the activity and returns to the previous screen
                }
            });
        }
    }
}

