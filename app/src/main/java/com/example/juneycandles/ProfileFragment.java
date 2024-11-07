package com.example.juneycandles;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class ProfileFragment extends Fragment {

    private TextView usernameTitle;
    private EditText birthdayInput, addressInput, emailInput;
    private DBHelper dbHelper;
    private String phoneNo = "1234567890"; // Replace with the actual phone number for the logged-in user

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize DBHelper
        dbHelper = new DBHelper(requireContext());

        // Initialize UI components
        usernameTitle = view.findViewById(R.id.username_title);
        birthdayInput = view.findViewById(R.id.birthday_input);
        addressInput = view.findViewById(R.id.address_input);
        emailInput = view.findViewById(R.id.email_input);
        Button logoutButton = view.findViewById(R.id.Logout_button);
        Button editButton = view.findViewById(R.id.edit_profile);

        // Load user information using phone number
        loadUserInfo(phoneNo);

        // Logout button functionality
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                requireActivity().finish(); // Close the profile activity
            }
        });

        // Edit button functionality
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open EditProfileFragment
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.outerFrame, new EditProfileFragment()); // Replace with the container ID where fragments are shown
                transaction.addToBackStack(null); // Add to back stack so user can navigate back
                transaction.commit();
            }
        });

        return view;
    }

    // Load user information from SQLite database
    private void loadUserInfo(String phoneNo) {
        Cursor cursor = dbHelper.getUserByPhone(phoneNo);

        if (cursor != null && cursor.moveToFirst()) {
            String username = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String address = cursor.getString(cursor.getColumnIndexOrThrow("address"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));

            // Set the data to TextViews and EditTexts
            usernameTitle.setText(username);
            addressInput.setText(address);
            emailInput.setText(email);

            cursor.close();  // Close the cursor after use
        } else {
            Toast.makeText(getActivity(), "Failed to load user data.", Toast.LENGTH_SHORT).show();
        }
    }
}
