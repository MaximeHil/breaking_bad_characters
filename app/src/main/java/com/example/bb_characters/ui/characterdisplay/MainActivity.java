package com.example.bb_characters.ui.characterdisplay;

import android.os.Bundle;

import com.example.bb_characters.R;
import com.example.bb_characters.ui.characterdisplay.allCharacters.AllCharactersFragment;
import com.example.bb_characters.ui.characterdisplay.favoriteCharacters.FavoriteFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageButton;

/**
 * Activité principale qui contient 2 fragments dans un viewPager
 */
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
        final FavoriteFragment favoriteFragment = FavoriteFragment.newInstance();

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return allCharactersFragment;
                }
                return favoriteFragment;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0) {
                    return AllCharactersFragment.TAB_NAME;
                }
                return FavoriteFragment.TAB_NAME;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
    }

}