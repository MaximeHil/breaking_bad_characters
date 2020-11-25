package com.example.bb_characters.ui.characterdisplay;

import android.os.Bundle;

import com.example.bb_characters.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

public class MainActivity extends AppCompatActivity {

    boolean flag = true; // true if view is on list mode, false if view is on grid mode.
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViewPagerAndTabs();
        setFAB();
    }

    private void setViewPagerAndTabs() {
        viewPager = findViewById(R.id.view_pager);

        final AllCharactersFragment allCharactersFragment = AllCharactersFragment.newInstance();
        final AllCharactersFragment allCharactersFragment2 = AllCharactersFragment.newInstance();

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return allCharactersFragment;
                }
                return allCharactersFragment2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0) {
                    return AllCharactersFragment.TAB_NAME;
                }
                return AllCharactersFragment.TAB_NAME;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
        /*SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);*/
    }

    private void setFAB() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Change the view when the FAB is clicked
                if(!flag){
                    fab.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_list_24));
                    flag = true;
                } else {
                    fab.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_grid_on_24));
                    flag = false;
                }
            }
        });
    }
}