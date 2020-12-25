package com.example.bb_characters.data.repository;

import com.example.bb_characters.data.api.model.Character;
import com.example.bb_characters.data.api.model.CharacterDetails;
import com.example.bb_characters.data.entity.CharacterEntity;
import com.example.bb_characters.data.repository.local.CharacterDisplayLocalDataSource;
import com.example.bb_characters.data.repository.remote.CharacterDisplayRemoteDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class CharacterDisplayDataRepository implements CharacterDisplayRepository{

    private CharacterDisplayRemoteDataSource characterDisplayRemoteDataSource;
    private CharacterDisplayLocalDataSource characterDisplayLocalDataSource;
    private  CharacterDetailsToCharacterEntityMapper characterDetailsToCharacterEntityMapper;

    public CharacterDisplayDataRepository(CharacterDisplayRemoteDataSource characterDisplayRemoteDataSource,
                                          CharacterDisplayLocalDataSource characterDisplayLocalDataSource){
        this.characterDisplayRemoteDataSource = characterDisplayRemoteDataSource;
        this.characterDisplayLocalDataSource = characterDisplayLocalDataSource;
        this.characterDetailsToCharacterEntityMapper = new CharacterDetailsToCharacterEntityMapper();
    }

    @Override
    public Single<List<CharacterDetails>> getAllCharacters() {
        return this.characterDisplayRemoteDataSource.getAllCharacters();
    }

    @Override
    public Single<List<CharacterDetails>> getCharacter(int id) {
        return this.characterDisplayRemoteDataSource.getCharacter(id);
    }

    @Override
    public Flowable<List<CharacterEntity>> getFavoriteCharacters() {
        return this.characterDisplayLocalDataSource.getFavoriteCharacters();
    }

    @Override
    public Completable addCharacterToFavorites(int id) {
        return characterDisplayRemoteDataSource.getCharacter(id)
                .map(new Function<List<CharacterDetails>, CharacterEntity>() {
                    @Override
                    public CharacterEntity apply(@NonNull List<CharacterDetails> characterDetails) throws Exception {
                        return characterDetailsToCharacterEntityMapper.map(characterDetails.get(0));
                    }
                }).flatMapCompletable(new Function<CharacterEntity, CompletableSource>() {
                    @Override
                    public CompletableSource apply(@NonNull CharacterEntity characterEntity) throws Exception {
                        return characterDisplayLocalDataSource.addCharacterToFavorites(characterEntity);
                    }
                });
    }

    @Override
    public Completable removeCharacterFromFavorites(int id) {
        return characterDisplayLocalDataSource.removeCharacterFromFavorites(id);
    }


}
