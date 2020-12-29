package com.example.bb_characters.ui.characterdisplay.favoriteCharacters.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bb_characters.R;
import com.example.bb_characters.ui.characterdisplay.allCharacters.adapter.CharacterViewItem;

import java.util.ArrayList;
import java.util.List;


public class CharacterFavoriteAdapter extends RecyclerView.Adapter<CharacterFavoriteAdapter.CharacterFavoriteViewHolder>{

    private List<CharacterFavoriteViewItem> characterFavoriteViewItemList;

    public static class CharacterFavoriteViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView;
        private View v;

        public CharacterFavoriteViewHolder(@NonNull View itemView) {

            super(itemView);
            this.v = itemView;
            nameTextView =v.findViewById(R.id.favorite_name);
        }

        void bind(CharacterFavoriteViewItem characterFavoriteViewItem){
            this.nameTextView.setText(characterFavoriteViewItem.getName());
        }
    }

    public CharacterFavoriteAdapter(){
        this.characterFavoriteViewItemList = new ArrayList<>();
    }

    public void bindViewModels(List<CharacterFavoriteViewItem> characterFavoriteViewItemList) {
        this.characterFavoriteViewItemList.clear();
        Log.i("BINDVIEWMODELS", "List de character de taille : " + characterFavoriteViewItemList.size());
        this.characterFavoriteViewItemList.addAll(characterFavoriteViewItemList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CharacterFavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favorite_character_item, parent, false);
        CharacterFavoriteViewHolder characterFavoriteViewHolder = new CharacterFavoriteViewHolder(v);
        return characterFavoriteViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterFavoriteViewHolder holder, int position) {
        holder.bind(characterFavoriteViewItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return characterFavoriteViewItemList.size();
    }


}
