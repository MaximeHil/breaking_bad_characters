package com.example.bb_characters.data.repository.remote;

import com.example.bb_characters.data.api.CharacterDisplayService;
import com.example.bb_characters.data.api.model.Character;
import com.example.bb_characters.data.api.model.CharacterDetails;

import java.util.List;

import io.reactivex.Single;

public class CharacterDisplayRemoteDataSource {

    private CharacterDisplayService characterDisplayService;

    public CharacterDisplayRemoteDataSource(CharacterDisplayService characterDisplayService){
        this.characterDisplayService = characterDisplayService;
    }

    public Single<List<CharacterDetails>> getAllCharacters(){
        return this.characterDisplayService.getAllCharacters();
    }

    public Single<List<CharacterDetails>> getCharacter(int id){
        return this.characterDisplayService.getCharacter(id);
    }

}
