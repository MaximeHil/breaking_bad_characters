package com.example.bb_characters.ui.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.bb_characters.data.repository.CharacterDisplayRepository;

// S'occupe de générer les view models
public class ViewModelFactory implements ViewModelProvider.Factory {

    private final CharacterDisplayRepository characterDisplayRepository;

    public ViewModelFactory(CharacterDisplayRepository characterDisplayRepository) {
        this.characterDisplayRepository = characterDisplayRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CharactersViewModel.class)) {
            return (T) new CharactersViewModel(characterDisplayRepository);
        } else
        if (modelClass.isAssignableFrom(FavoriteViewModel.class)) {
            return (T) new FavoriteViewModel(characterDisplayRepository);
        }
        //Handle favorite view model case
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
