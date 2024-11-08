package com.example.juneycandles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class ThemeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_theme, container, false);

        // Get the zodiacSign ImageButton and set an OnClickListener
        ImageButton zodiacSignButton = view.findViewById(R.id.zodiacSign);
        zodiacSignButton.setOnClickListener(v -> openZodiacFragment());

        return view;
    }

    private void openZodiacFragment() {
        // Create a new instance of ZodiacFragment
        ZodiacFragment zodiacFragment = new ZodiacFragment();

        // Replace the outerFrame in home_page.xml with ZodiacFragment
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.outerFrame, zodiacFragment);
        transaction.addToBackStack(null); // Optional: adds transaction to back stack for "back" functionality
        transaction.commit();
    }
}
