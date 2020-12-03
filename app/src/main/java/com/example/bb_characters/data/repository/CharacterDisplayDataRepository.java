package com.example.bb_characters.data.repository;

import com.example.bb_characters.data.api.model.Character;
import com.example.bb_characters.data.api.model.CharacterDetails;
import com.example.bb_characters.data.repository.remote.CharacterDisplayRemoteDataSource;

import java.util.List;

import io.reactivex.Single;

public class CharacterDisplayDataRepository implements CharacterDisplayRepository{

    private CharacterDisplayRemoteDataSource characterDisplayRemoteDataSource;

    public CharacterDisplayDataRepository(CharacterDisplayRemoteDataSource characterDisplayRemoteDataSource){
        this.characterDisplayRemoteDataSource = characterDisplayRemoteDataSource;
    }

    @Override
    public Single<List<CharacterDetails>> getAllCharacters() {
        return this.characterDisplayRemoteDataSource.getAllCharacters();
    }

    @Override
    public Single<Character> getCharacter(int id) {
        return this.characterDisplayRemoteDataSource.getCharacter(id);
    }
}
