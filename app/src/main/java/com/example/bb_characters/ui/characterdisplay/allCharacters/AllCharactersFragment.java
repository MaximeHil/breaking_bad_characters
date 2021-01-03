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
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bb_characters.R;
import com.example.bb_characters.data.di.FakeDependencyInjection;
import com.example.bb_characters.ui.characterdisplay.CharacterDetailsActivity;
import com.example.bb_characters.ui.characterdisplay.allCharacters.adapter.CharacterActionInterface;
import com.example.bb_characters.ui.characterdisplay.allCharacters.adapter.CharacterAdapter;
import com.example.bb_characters.ui.viewmodel.CharactersViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * Le fragment contenant la liste de tous les personnages
 */
public class AllCharactersFragment extends Fragment implements CharacterActionInterface {

    public static final String TAB_NAME = "Characters";
    private View rootView;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CharacterAdapter characterAdapter_l, characterAdapter_g;
    private CharactersViewModel charactersViewModel;
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
        setupRecyclerView();

        registerViewModels();
    }

    private void registerViewModels() {
        charactersViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(CharactersViewModel.class);
        charactersViewModel.getAllCharacters();
        charactersViewModel.getCharacters().observe(getViewLifecycleOwner(), characterItemViewModelList -> characterAdapter_g.bindViewModels(characterItemViewModelList));
        charactersViewModel.getCharacters().observe(getViewLifecycleOwner(), characterItemViewModelList -> characterAdapter_l.bindViewModels(characterItemViewModelList));
    }

    // Cette méthode permet d'initialiser le recyclerView et le onClickListener sur le FAB
    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view);

        final RecyclerView.LayoutManager layoutManager_lign = new LinearLayoutManager(getContext());
        final RecyclerView.LayoutManager layoutManager_grid = new GridLayoutManager(getContext(),3);

        this.characterAdapter_l = new CharacterAdapter(this, true);
        this.characterAdapter_g = new CharacterAdapter(this, false);

        layoutManager = layoutManager_grid;
        recyclerView.setLayoutManager(layoutManager);

        FloatingActionButton fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            if(!asList){
                fab.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_baseline_grid_on_24));
                layoutManager = layoutManager_lign;
                asList = true;
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(characterAdapter_l);
            } else {
                fab.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_baseline_list_24));
                layoutManager = layoutManager_grid;
                asList = false;
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(characterAdapter_g);
            }

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