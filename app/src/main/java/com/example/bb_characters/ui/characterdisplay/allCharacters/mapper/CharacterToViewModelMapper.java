package com.example.bb_characters.ui.characterdisplay.allCharacters.mapper;

import com.example.bb_characters.data.api.model.CharacterDetails;
import com.example.bb_characters.ui.characterdisplay.allCharacters.adapter.CharacterViewItem;

import java.util.ArrayList;
import java.util.List;

public class CharacterToViewModelMapper {

    public CharacterViewItem map(CharacterDetails c){
        CharacterViewItem cvi = new CharacterViewItem();
        cvi.setCharacterId(c.getChar_id());
        cvi.setImageUrl(c.getImg());

        return cvi;
    }

    public List<CharacterViewItem> map(List<CharacterDetails> lc){
        List<CharacterViewItem> lcvi = new ArrayList<>();
        if(lc != null) {
            for (CharacterDetails c : lc) {
                lcvi.add(map(c));
            }
        }
        return lcvi;
    }
}
