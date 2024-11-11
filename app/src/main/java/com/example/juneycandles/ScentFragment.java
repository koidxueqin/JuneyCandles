package com.example.juneycandles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.fragment.app.Fragment;

public class ScentFragment extends Fragment {

    public ScentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scent, container, false);

        // find the image button
        ImageButton fruityImage = view.findViewById(R.id.fruity);

        // Set an OnClickListener on the button
        fruityImage.setOnClickListener(v -> {
            if (getActivity() != null) {

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.outerFrame, new FruityFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

}
