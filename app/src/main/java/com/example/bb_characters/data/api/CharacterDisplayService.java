package com.example.bb_characters.data.api;

import com.example.bb_characters.data.api.model.Character;
import com.example.bb_characters.data.api.model.CharacterDetails;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CharacterDisplayService {
    @GET("characters")
    Single<List<CharacterDetails>> getAllCharacters();

    @GET("characters/{id}")
    Single<List<CharacterDetails>> getCharacter(@Path("id") int id);


}
