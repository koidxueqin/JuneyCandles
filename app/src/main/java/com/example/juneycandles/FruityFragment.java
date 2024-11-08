package com.example.juneycandles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;

public class FruityFragment extends Fragment {

    public FruityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fruity, container, false);

        // Find the back button and set the click listener
        Button backIcon = view.findViewById(R.id.backIcon);
        backIcon.setOnClickListener(v -> {
            // Go back to the ProfileFragment
            getActivity().getSupportFragmentManager().popBackStack();
        });


        return view;
    }
}
