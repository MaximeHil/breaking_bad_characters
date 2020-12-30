package com.example.bb_characters.ui.characterdisplay.favoriteCharacters.mapper;

import com.example.bb_characters.data.entity.CharacterEntity;
import com.example.bb_characters.ui.characterdisplay.allCharacters.adapter.CharacterDetailsViewItem;
import com.example.bb_characters.ui.characterdisplay.favoriteCharacters.adapter.CharacterFavoriteViewItem;

import java.util.ArrayList;
import java.util.List;

public class EntityToFavoriteViewMapper {

    public CharacterFavoriteViewItem map(CharacterEntity entity){
        CharacterFavoriteViewItem cfvi = new CharacterFavoriteViewItem();
        cfvi.setId(entity.getId());
        cfvi.setName(entity.getName());
        cfvi.setImgUrl(entity.getImgUrl());
        cfvi.setNickname(entity.getNickname());
        return cfvi;
    }

    public List<CharacterFavoriteViewItem> map(List<CharacterEntity> entityList){
        List<CharacterFavoriteViewItem> list = new ArrayList<>();

        if(entityList != null){
            for (CharacterEntity c : entityList){
                list.add(map(c));
            }
        }
        return list;
    }
}
