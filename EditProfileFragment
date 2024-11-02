package com.example.juneycandles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;

public class EditProfileFragment extends Fragment {

    public EditProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        // Find the back button and set the click listener
        Button backIcon = view.findViewById(R.id.backIcon);
        backIcon.setOnClickListener(v -> {
            // Go back to the ProfileFragment
            getActivity().getSupportFragmentManager().popBackStack();
        });

        // Initialize other views here, if needed
        // Example: Button saveBtn = view.findViewById(R.id.saveBtn);

        return view;
    }
}
