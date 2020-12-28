package com.example.bb_characters.ui.characterdisplay.favoriteCharacters.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class CharacterFavoriteAdapter extends RecyclerView.Adapter<CharacterFavoriteAdapter.CharacterFavoriteViewHolder>{

    public static class CharacterFavoriteViewHolder extends RecyclerView.ViewHolder {

        public CharacterFavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public CharacterFavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterFavoriteViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
