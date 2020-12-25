package com.example.bb_characters.data.db;

import android.content.Context;

import androidx.room.Room;

public class CharacterDatabaseCreator {
    private static CharacterDatabase myCharacterDatabase;

    public static CharacterDatabase database(Context context){
        // On vérifie que la base de données n'est instanciée qu'une seule fois
        if(myCharacterDatabase == null){
            myCharacterDatabase = Room.databaseBuilder(context, CharacterDatabase.class, "CharacterDB").build();
        }

        return myCharacterDatabase;
    }
}
