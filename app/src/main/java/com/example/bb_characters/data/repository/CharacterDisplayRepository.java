package com.example.bb_characters.data.repository;

import com.example.bb_characters.data.api.model.Character;
import com.example.bb_characters.data.api.model.CharactersResponse;

import java.util.List;

import io.reactivex.Single;

public interface CharacterDisplayRepository {

    Single<CharactersResponse> getAllCharacters();

    Single<Character> getCharacter(int id);
}
