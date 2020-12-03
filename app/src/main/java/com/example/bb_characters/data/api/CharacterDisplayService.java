package com.example.bb_characters.data.api;

import com.example.bb_characters.data.api.model.Character;
import com.example.bb_characters.data.api.model.CharactersResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CharacterDisplayService {
    @GET("characters")
    Single<CharactersResponse> getAllCharacters();

    @GET("characters/{id}")
    Single<Character> getCharacter(@Path("id") int id);


}
