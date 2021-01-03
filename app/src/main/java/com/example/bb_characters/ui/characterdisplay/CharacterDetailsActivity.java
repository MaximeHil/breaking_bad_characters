package com.example.bb_characters.ui.characterdisplay;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.bb_characters.data.di.FakeDependencyInjection;
import com.example.bb_characters.data.repository.CharacterDisplayDataRepository;
import com.example.bb_characters.data.repository.CharacterDisplayRepository;
import com.example.bb_characters.data.repository.local.CharacterDisplayLocalDataSource;
import com.example.bb_characters.ui.characterdisplay.allCharacters.adapter.CharacterDetailsViewItem;
import com.example.bb_characters.ui.viewmodel.CharactersViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bb_characters.R;
import com.example.bb_characters.ui.viewmodel.FavoriteViewModel;

import java.util.List;

/**
 * Cette activité se charge de l'affichage des détails d'un personnage
 */
public class CharacterDetailsActivity extends AppCompatActivity {

    private ImageView photo;
    private TextView name, nickname, birthday, portrayed;
    private Button addToFavoriteButton;
    private int characterId;
    private CharactersViewModel charactersViewModel;
    private FavoriteViewModel favoriteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Character Details");

        // On récupère l'identifiant du personnage à afficher
        Intent i = getIntent();
        this.characterId = i.getIntExtra("CharacterId", 1);

        registerViewModel();

        addToFavoriteButton = findViewById(R.id.add_to_favorite);

        // Gère l'ajout d'un personnage aux favoris quand on clique sur "Add"
        addToFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoriteViewModel.addCharacterToFavorites(characterId);

                Toast.makeText(getApplicationContext(),
                        "Character added to favorites", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    // On récupère l'intstance du view model via le view model factory
    private void registerViewModel() {
        charactersViewModel = new ViewModelProvider(this, FakeDependencyInjection.getViewModelFactory()).get(CharactersViewModel.class);
        favoriteViewModel = new ViewModelProvider(this, FakeDependencyInjection.getViewModelFactory()).get(FavoriteViewModel.class);
        charactersViewModel.getCharacter().observe(this, new Observer<List<CharacterDetailsViewItem>>() {
            @Override
            public void onChanged(List<CharacterDetailsViewItem> characterDetailsViewItemList) {
                setLayout(characterDetailsViewItemList.get(0));
            }
        });
        charactersViewModel.getCharacterById(characterId);

    }

    // Permet d'afficher sur la page les informations du personnage passé en argument
    private void setLayout(CharacterDetailsViewItem characterToDisplay) {

        photo = findViewById(R.id.character_photo);
        name = findViewById(R.id.character_name);
        nickname = findViewById(R.id.character_nickname);
        birthday = findViewById(R.id.character_birthday);
        portrayed = findViewById(R.id.character_portrayed);

        name.setText(characterToDisplay.getName());
        nickname.setText(getString(R.string.nickname, characterToDisplay.getNickname()));
        if(characterToDisplay.getBirthday().equals("Unknown")){
            birthday.setText(characterToDisplay.getBirthday() + " birthday");
        } else {
            birthday.setText(characterToDisplay.getBirthday());
        }
        portrayed.setText(characterToDisplay.getPortrayed());
        Glide.with(this)
                .load(characterToDisplay.getUrl())
                .into(photo);
    }
}