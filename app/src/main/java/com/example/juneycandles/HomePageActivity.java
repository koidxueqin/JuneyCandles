package com.example.juneycandles;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.tabs.TabLayout;

public class HomePageActivity extends AppCompatActivity {
    TabLayout tabLayout;
    FrameLayout frameLayout;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        tabLayout = findViewById(R.id.bottomTab);
        frameLayout = findViewById(R.id.outerFrame);
        btnSearch = findViewById(R.id.btnSearch);

        // Add tabs and set icons
        addTabWithIcon(tabLayout.newTab(), R.drawable.order, "");
        addTabWithIcon(tabLayout.newTab(), R.drawable.home, "");
        addTabWithIcon(tabLayout.newTab(), R.drawable.user, "");

        // Set the default selected tab to the second tab (index 1)
        tabLayout.selectTab(tabLayout.getTabAt(1));

        getSupportFragmentManager().beginTransaction().replace(R.id.outerFrame, new MenuFragment())
                .addToBackStack(null)
                .commit();

        // Set up tab selection listener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new FavFragment();
                        break;
                    case 1:
                        fragment = new MenuFragment();
                        break;
                    case 2:
                        fragment = new ProfileFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.outerFrame, fragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

        // Search button click listener
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchFragment searchFragment = new SearchFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.outerFrame, searchFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void addTabWithIcon(TabLayout.Tab tab, int iconResId, String text) {
        tab.setIcon(iconResId);
        tabLayout.addTab(tab);
    }
}
