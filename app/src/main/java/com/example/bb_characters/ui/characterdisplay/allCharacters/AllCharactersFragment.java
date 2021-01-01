package com.example.bb_characters.ui.characterdisplay.allCharacters;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bb_characters.R;
import com.example.bb_characters.data.api.model.Character;
import com.example.bb_characters.data.di.FakeDependencyInjection;
import com.example.bb_characters.ui.characterdisplay.CharacterDetailsActivity;
import com.example.bb_characters.ui.characterdisplay.allCharacters.adapter.CharacterActionInterface;
import com.example.bb_characters.ui.characterdisplay.allCharacters.adapter.CharacterAdapter;
import com.example.bb_characters.ui.viewmodel.CharactersViewModel;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class AllCharactersFragment extends Fragment implements CharacterActionInterface {

    public static final String TAB_NAME = "Characters";
    private View rootView;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CharacterAdapter characterAdapter_l, characterAdapter_g;
    private CharactersViewModel charactersViewModel;
    private ImageButton list_button, grid_button;
    private boolean asList;

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
        setButtons();
        setupRecyclerView();

        registerViewModels();
    }

    private void setButtons() {
        list_button = rootView.getRootView().findViewById(R.id.list_button);
        grid_button = rootView.getRootView().findViewById(R.id.grid_button);
    }

    private void registerViewModels() {
        charactersViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(CharactersViewModel.class);
        charactersViewModel.getAllCharacters();
        charactersViewModel.getCharacters().observe(getViewLifecycleOwner(), characterItemViewModelList -> characterAdapter_g.bindViewModels(characterItemViewModelList));
        charactersViewModel.getCharacters().observe(getViewLifecycleOwner(), characterItemViewModelList -> characterAdapter_l.bindViewModels(characterItemViewModelList));
    }

    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view);

        final RecyclerView.LayoutManager layoutManager_lign = new LinearLayoutManager(getContext());
        final RecyclerView.LayoutManager layoutManager_grid = new GridLayoutManager(getContext(),3);

        this.characterAdapter_l = new CharacterAdapter(this, true);
        this.characterAdapter_g = new CharacterAdapter(this, false);

        layoutManager = layoutManager_grid;
        recyclerView.setLayoutManager(layoutManager);

        list_button.setOnClickListener(v -> {
            list_button.setVisibility(View.INVISIBLE);
            grid_button.setVisibility(View.VISIBLE);
            layoutManager = layoutManager_lign;
            asList = true;
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(characterAdapter_l);
        });

        grid_button.setOnClickListener(v -> {
            grid_button.setVisibility(View.INVISIBLE);
            list_button.setVisibility(View.VISIBLE);
            layoutManager = layoutManager_grid;
            asList = false;
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(characterAdapter_g);
        });

        recyclerView.setAdapter(characterAdapter_g);

    }

    @Override
    public void onCharacterClicked(int characterId) {
        Intent i = new Intent(getActivity(), CharacterDetailsActivity.class);
        i.putExtra("CharacterId", characterId);
        charactersViewModel.getCharacterById(characterId);
        startActivity(i);
    }
}