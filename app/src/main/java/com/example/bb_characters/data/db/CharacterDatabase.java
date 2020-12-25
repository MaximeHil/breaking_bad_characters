package com.example.bb_characters.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.bb_characters.data.entity.CharacterEntity;

// Notre base données stocke des objets qui correspondent à la classe CharacterEntity
@Database(entities = {CharacterEntity.class}, version = 1)
public abstract class CharacterDatabase extends RoomDatabase {
    public abstract CharacterDao characterDao();
}
