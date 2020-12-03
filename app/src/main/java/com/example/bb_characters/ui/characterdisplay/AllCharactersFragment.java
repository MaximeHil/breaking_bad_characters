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

    private static final String ARG_SECTION_NUMBER = "section_number";
    public static final String TAB_NAME = "Characters";
    private View rootView;
    private RecyclerView recyclerView;
    private CharacterAdapter characterAdapter;
    private ArrayList<String> images = new ArrayList<String>();
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


        registerViewModels();
    }

    private void registerViewModels() {
        Log.i("FRAGMENT", "On est dans le registerViewModels");
        charactersViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(CharactersViewModel.class);
        Log.i("FRAGMENT", "View model créé");

        Log.i("FRAGMENT", "Tous les personnages récupérés");
        charactersViewModel.getCharacters().observe(getViewLifecycleOwner(), new Observer<List<CharacterViewItem>>() {
            @Override
            public void onChanged(List<CharacterViewItem> characterItemViewModelList) {
                Log.i("FRAGMENT", "List size : " + characterItemViewModelList.size());
                characterAdapter.bindViewModels(characterItemViewModelList);
            }
        });
        charactersViewModel.getAllCharacters();
        Log.i("FRAGMENT", "Fin de la méthode");
    }

    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        characterAdapter = new CharacterAdapter();
        recyclerView.setAdapter(characterAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
    }
}