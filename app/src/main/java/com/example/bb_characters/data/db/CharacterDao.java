package com.example.bb_characters.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.bb_characters.data.entity.CharacterEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

// Le DAO contient la liste des fonctions que l'on pourra appeler pour
// questionner la base de données
@Dao
public interface CharacterDao {

    @Query("SELECT * FROM characters")
    Flowable<List<CharacterEntity>> getFavorites();

    @Insert
    Completable addCharacter(CharacterEntity characterEntity);

    @Query("DELETE FROM characters WHERE id = :id")
    Completable deleteCharacter(int id);



    @Query("SELECT id from characters")
    Single<List<Integer>> getFavoriteIdList();
}
