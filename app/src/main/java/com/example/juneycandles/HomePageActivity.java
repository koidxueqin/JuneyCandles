package com.example.juneycandles;

import android.os.Bundle;
import android.view.Menu;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.tabs.TabLayout;


public class HomePageActivity extends AppCompatActivity {
    TabLayout tabLayout;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        tabLayout = findViewById(R.id.bottomTab);
        frameLayout = findViewById(R.id.outerFrame);

        // Add tabs and set icons
        addTabWithIcon(tabLayout.newTab(), R.drawable.heart_border, ""); // Use empty text
        addTabWithIcon(tabLayout.newTab(), R.drawable.home, ""); // Use empty text
        addTabWithIcon(tabLayout.newTab(), R.drawable.user, ""); // Use empty text

        // Set the default selected tab to the second tab (index 1)
        tabLayout.selectTab(tabLayout.getTabAt(1));

        frameLayout = (FrameLayout) findViewById(R.id.outerFrame);
        tabLayout = (TabLayout) findViewById(R.id.bottomTab);

        getSupportFragmentManager().beginTransaction().replace(R.id.outerFrame, new MenuFragment())
                .addToBackStack(null)
                .commit();

        // Optionally, add a listener to handle tab selection
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
               Fragment fragment = null;
                switch(tab.getPosition()){
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
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void addTabWithIcon(TabLayout.Tab tab, int iconResId, String text) {
        tab.setIcon(iconResId);
        tabLayout.addTab(tab);
    }
}
