package com.example.bb_characters.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CharactersResponse {

    @SerializedName("items")
    List<Character> charactersList;

    int totalItems;

    public List<Character> getBookList() {
        return charactersList;
    }

    public void setCharactersList(List<Character> charactersList) {
        this.charactersList = charactersList;
    }

    public int getTotalItems() {
        return totalItems;
    }


}