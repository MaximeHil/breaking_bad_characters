package com.example.bb_characters.ui.characterdisplay.mapper;

import android.util.Log;

import com.example.bb_characters.data.api.model.Character;
import com.example.bb_characters.ui.characterdisplay.adapter.CharacterViewItem;

import java.util.ArrayList;
import java.util.List;

public class CharacterToViewModelMapper {

    public CharacterViewItem map(Character c){
        CharacterViewItem cvi = new CharacterViewItem();
        cvi.setCharacterId(c.getCharacterId());
        cvi.setImageUrl(c.getDetails().getImg());

        return cvi;
    }

    public List<CharacterViewItem> map(List<Character> lc){
        List<CharacterViewItem> lcvi = new ArrayList<>();
        if(lc != null) {
            for (Character c : lc) {
                lcvi.add(map(c));
            }
        }
        return lcvi;
    }
}
