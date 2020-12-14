package com.example.bb_characters.ui.characterdisplay;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.bb_characters.data.api.model.CharacterDetails;
import com.example.bb_characters.data.di.FakeDependencyInjection;
import com.example.bb_characters.ui.characterdisplay.adapter.CharacterDetailsViewItem;
import com.example.bb_characters.ui.characterdisplay.adapter.CharacterViewItem;
import com.example.bb_characters.ui.viewmodel.CharactersViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bb_characters.R;

import java.util.List;

public class CharacterDetailsActivity extends AppCompatActivity {

    private ImageView photo;
    private TextView name, nickname, birthday, portrayed;
    private int characterId;
    private CharactersViewModel charactersViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // On récupère l'identifiant du personnage à afficher
        Intent i = getIntent();
        this.characterId = i.getIntExtra("CharacterId", 1);

        registerViewModel();
    }

    // On récupère l'intstance du view model via le view model factory
    private void registerViewModel() {
        charactersViewModel = new ViewModelProvider(this, FakeDependencyInjection.getViewModelFactory()).get(CharactersViewModel.class);

        charactersViewModel.getCharacter().observe(this, new Observer<List<CharacterDetailsViewItem>>() {
            @Override
            public void onChanged(List<CharacterDetailsViewItem> characterDetailsViewItemList) {
                setLayout(characterDetailsViewItemList.get(0));
            }
        });
        charactersViewModel.getCharacterById(characterId);

    }

    // Permet d'afficher sur la page les informations passées en argument
    private void setLayout(CharacterDetailsViewItem characterToDisplay) {

        photo = findViewById(R.id.character_photo);
        name = findViewById(R.id.character_name);
        nickname = findViewById(R.id.character_nickname);
        birthday = findViewById(R.id.character_birthday);
        portrayed = findViewById(R.id.character_portrayed);

        name.append(" " + characterToDisplay.getName());
        nickname.append(" " + characterToDisplay.getNickname());
        birthday.append(" " + characterToDisplay.getBirthday());
        portrayed.append(" " + characterToDisplay.getPortrayed());
        Glide.with(this)
                .load(characterToDisplay.getUrl())
                .into(photo);
    }
}