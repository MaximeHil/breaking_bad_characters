package com.example.bb_characters.ui.characterdisplay;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bb_characters.R;
import com.example.bb_characters.ui.characterdisplay.adapter.CharacterAdapter;
import com.example.bb_characters.ui.viewmodel.CharactersViewModel;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class AllCharactersFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    public static final String TAB_NAME = "Characters";
    private View rootView;
    private RecyclerView recyclerView;
    private CharacterAdapter characterAdapter;
    private ArrayList<String> images = new ArrayList<String>();

    private CharactersViewModel pageViewModel;

    private AllCharactersFragment(){

    }

    public static AllCharactersFragment newInstance() {
        return new AllCharactersFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        this.images.add("https://images.amcnetworks.com/amc.com/wp-content/uploads/2015/04/cast_bb_700x1000_walter-white-lg.jpg");
        this.images.add("https://vignette.wikia.nocookie.net/breakingbad/images/9/95/JesseS5.jpg/revision/latest?cb=20120620012441");
        this.images.add("https://vignette.wikia.nocookie.net/breakingbad/images/9/95/JesseS5.jpg/revision/latest?cb=20120620012441");
        rootView = inflater.inflate(R.layout.fragment_all_characters, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        characterAdapter = new CharacterAdapter(images);
        recyclerView.setAdapter(characterAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
    }
}