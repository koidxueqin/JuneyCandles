package com.example.juneycandles;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class OurStory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_ourstory);

        // Find the back button by ID
        Button backIcon = findViewById(R.id.backIcon);

        // Set an OnClickListener for the back button
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  // Closes the activity and returns to the previous fragment (AboutFragment)
            }
        });
    }
}
