package com.example.bb_characters.data.api.model;

public class Character {

    private int characterId;
    private boolean isFavorite;
    private CharacterDetails details;

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public CharacterDetails getDetails() {
        return details;
    }

    public void setDetails(CharacterDetails details) {
        this.details = details;
    }
}
