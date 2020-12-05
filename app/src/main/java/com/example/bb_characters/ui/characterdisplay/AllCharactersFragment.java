package com.example.bb_characters.ui.characterdisplay;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bb_characters.R;
import com.example.bb_characters.data.di.FakeDependencyInjection;
import com.example.bb_characters.ui.characterdisplay.adapter.CharacterAdapter;
import com.example.bb_characters.ui.characterdisplay.adapter.CharacterViewItem;
import com.example.bb_characters.ui.viewmodel.CharactersViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class AllCharactersFragment extends Fragment {

    public static final String TAB_NAME = "Characters";
    private View rootView;
    private RecyclerView recyclerView;
    private CharacterAdapter characterAdapter;
    private CharactersViewModel charactersViewModel;

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
        rootView = inflater.inflate(R.layout.fragment_all_characters, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();


        registerViewModels();
    }

    private void registerViewModels() {
        charactersViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(CharactersViewModel.class);
        charactersViewModel.getAllCharacters();
        charactersViewModel.getCharacters().observe(getViewLifecycleOwner(), new Observer<List<CharacterViewItem>>() {
            @Override
            public void onChanged(List<CharacterViewItem> characterItemViewModelList) {
                characterAdapter.bindViewModels(characterItemViewModelList);
            }
        });

    }

    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        characterAdapter = new CharacterAdapter();
        recyclerView.setAdapter(characterAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }
}