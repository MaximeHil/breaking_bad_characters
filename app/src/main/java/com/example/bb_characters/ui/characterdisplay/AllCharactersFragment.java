package com.example.bb_characters.ui.characterdisplay;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.bb_characters.R;
import com.example.bb_characters.ui.viewmodel.PageViewModel;

/**
 * A placeholder fragment containing a simple view.
 */
public class AllCharactersFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    public static final String TAB_NAME = "Characters";
    private View rootView;

    private PageViewModel pageViewModel;

    private AllCharactersFragment(){

    }

    public static AllCharactersFragment newInstance() {
        return new AllCharactersFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }
}