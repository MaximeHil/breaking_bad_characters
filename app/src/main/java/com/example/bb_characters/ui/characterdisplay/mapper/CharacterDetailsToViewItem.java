package com.example.bb_characters.ui.characterdisplay.mapper;

import com.example.bb_characters.data.api.model.CharacterDetails;
import com.example.bb_characters.ui.characterdisplay.adapter.CharacterDetailsViewItem;
import com.example.bb_characters.ui.characterdisplay.adapter.CharacterViewItem;

import java.util.ArrayList;
import java.util.List;

public class CharacterDetailsToViewItem {

    public CharacterDetailsViewItem map(CharacterDetails c){
        CharacterDetailsViewItem cdvi = new CharacterDetailsViewItem();
        cdvi.setUrl(c.getImg());
        cdvi.setName(c.getName());
        cdvi.setNickname(c.getNickname());
        cdvi.setBirthday(c.getBirthday());
        cdvi.setPortrayed(c.getPortrayed());

        return cdvi;
    }

    public List<CharacterDetailsViewItem> map(List<CharacterDetails> lc){
        List<CharacterDetailsViewItem> lcvi = new ArrayList<>();
        if(lc != null) {
            for (CharacterDetails c : lc) {
                lcvi.add(map(c));
            }
        }
        return lcvi;
    }
}
