package com.example.bb_characters.ui.characterdisplay;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;

import com.example.bb_characters.R;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    boolean list = false; // true if view is on list mode, false if view is on grid mode.
    private ViewPager viewPager;
    private ImageButton list_button, grid_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViewPagerAndTabs();
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

}