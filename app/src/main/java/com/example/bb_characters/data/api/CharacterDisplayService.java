package com.example.bb_characters.data.api;

import com.example.bb_characters.data.api.model.CharacterDetails;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Cette interface contient la liste des endpoints de l'API qui pourront
 * être appelés par l'application
 */
public interface CharacterDisplayService {
    @GET("characters")
    Single<List<CharacterDetails>> getAllCharacters();

    @GET("characters/{id}")
    Single<List<CharacterDetails>> getCharacter(@Path("id") int id);


}
