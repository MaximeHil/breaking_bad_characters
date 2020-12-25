package com.example.bb_characters.data.repository;

import com.example.bb_characters.data.api.model.Character;
import com.example.bb_characters.data.api.model.CharacterDetails;
import com.example.bb_characters.data.entity.CharacterEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface CharacterDisplayRepository {

    Single<List<CharacterDetails>> getAllCharacters();

    Single<List<CharacterDetails>> getCharacter(int id);

    Flowable<List<CharacterEntity>> getFavoriteCharacters();

    Completable addCharacterToFavorites(int id);

    Completable removeCharacterFromFavorites(int id);
}
