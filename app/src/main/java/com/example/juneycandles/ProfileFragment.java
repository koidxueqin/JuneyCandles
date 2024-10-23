package com.example.juneycandles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Find the edit profile button
        Button editProfileButton = view.findViewById(R.id.edit_profile);

        // Set an OnClickListener on the button
        editProfileButton.setOnClickListener(v -> {
            // Check if the activity is not null before proceeding
            if (getActivity() != null) {
                // Replace the ProfileFragment with EditProfileFragment
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.outerFrame, new EditProfileFragment())
                        .addToBackStack(null) // Allow going back to ProfileFragment
                        .commit();
            }
        });

        return view;
    }

}
