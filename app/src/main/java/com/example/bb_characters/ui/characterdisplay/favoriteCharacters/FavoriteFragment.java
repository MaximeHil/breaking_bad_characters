package com.example.bb_characters.ui.characterdisplay.favoriteCharacters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bb_characters.R;
import com.example.bb_characters.data.di.FakeDependencyInjection;
import com.example.bb_characters.ui.characterdisplay.allCharacters.adapter.CharacterActionInterface;
import com.example.bb_characters.ui.characterdisplay.allCharacters.adapter.CharacterAdapter;
import com.example.bb_characters.ui.characterdisplay.favoriteCharacters.adapter.CharacterFavoriteActionInterface;
import com.example.bb_characters.ui.characterdisplay.favoriteCharacters.adapter.CharacterFavoriteAdapter;
import com.example.bb_characters.ui.viewmodel.CharactersViewModel;
import com.example.bb_characters.ui.viewmodel.Event;
import com.example.bb_characters.ui.viewmodel.FavoriteViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteFragment extends Fragment implements CharacterFavoriteActionInterface {

    public static final String TAB_NAME = "Favorites";
    private View rootView;
    private RecyclerView recyclerView;
    private CharacterFavoriteAdapter characterFavoriteAdapter;
    private FavoriteViewModel favoriteViewModel;

    private FavoriteFragment() {
    }

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_favorite, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();
        registerViewModels();
    }


    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        characterFavoriteAdapter = new CharacterFavoriteAdapter(this);
        recyclerView.setAdapter(characterFavoriteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void registerViewModels() {
        favoriteViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(FavoriteViewModel.class);
        favoriteViewModel.getFavoriteCharacters();
        favoriteViewModel.getCharacters().observe(getViewLifecycleOwner(), characterItemViewModelList -> characterFavoriteAdapter.bindViewModels(characterItemViewModelList));

        favoriteViewModel.getCharacterAddedEvent().observe(getViewLifecycleOwner(), stringEvent -> {
            //Do nothing
        });
        favoriteViewModel.getCharacterDeletedEvent().observe(getViewLifecycleOwner(), stringEvent -> {
            //Do nothing
        });
    }

    @Override
    public void onRemoveFavorite(int charId) {
        favoriteViewModel.deleteCharacterFromFavorites(charId);
    }
}