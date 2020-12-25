package com.example.bb_characters.data.repository.local;

import com.example.bb_characters.data.db.CharacterDatabase;
import com.example.bb_characters.data.entity.CharacterEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;


public class CharacterDisplayLocalDataSource {

    private CharacterDatabase characterDatabase;

    public CharacterDisplayLocalDataSource(CharacterDatabase characterDatabase){
        this.characterDatabase = characterDatabase;
    }

    public Flowable<List<CharacterEntity>> getFavoriteCharacters(){
        return characterDatabase.characterDao().getFavorites();
    }

    public Completable addCharacterToFavorites(CharacterEntity entity){
        return characterDatabase.characterDao().addCharacter(entity);
    }

    public Completable removeCharacterFromFavorites(int id){
        return characterDatabase.characterDao().deleteCharacter(id);
    }


}
