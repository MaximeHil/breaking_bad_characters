package com.example.bb_characters.ui.characterdisplay;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.bb_characters.data.api.model.CharacterDetails;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bb_characters.R;

public class CharacterDetailsActivity extends AppCompatActivity {

    private CharacterDetails details_example;
    private ImageView photo;
    private TextView name, nickname, birthday, portrayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //setLayoutExample();
    }

    /*private void setLayoutExample() {
        details_example = new CharacterDetails();
        details_example.setImg("https://images.amcnetworks.com/amc.com/wp-content/uploads/2015/04/cast_bb_700x1000_walter-white-lg.jpg");
        details_example.setName("Walter White");
        details_example.setBirthday("09-07-1958");
        details_example.setNickname("Heisenberg");
        details_example.setPortrayed("Bryan Cranston");

        photo = findViewById(R.id.character_photo);
        name = findViewById(R.id.character_name);
        nickname = findViewById(R.id.character_nickname);
        birthday = findViewById(R.id.character_birthday);
        portrayed = findViewById(R.id.character_portrayed);

        name.append(" " + details_example.getName());
        nickname.append(" " + details_example.getNickname());
        birthday.append(" " + details_example.getBirthday());
        portrayed.append(" " + details_example.getPortrayed());
        Glide.with(this)
                .load(details_example.getImg())
                .into(photo);
    }*/
}