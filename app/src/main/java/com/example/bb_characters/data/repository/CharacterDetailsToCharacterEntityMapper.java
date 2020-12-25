package com.example.bb_characters.data.repository;

import com.example.bb_characters.data.api.model.CharacterDetails;
import com.example.bb_characters.data.entity.CharacterEntity;

public class CharacterDetailsToCharacterEntityMapper {

    public CharacterEntity map(CharacterDetails characterDetails){
        CharacterEntity ce = new CharacterEntity();

        ce.setId(characterDetails.getChar_id());
        ce.setImgUrl(characterDetails.getImg());
        ce.setBirthday(characterDetails.getBirthday());
        ce.setName(characterDetails.getName());
        ce.setNickname(characterDetails.getNickname());
        ce.setPortrayed(characterDetails.getPortrayed());

        return ce;
    }
}
