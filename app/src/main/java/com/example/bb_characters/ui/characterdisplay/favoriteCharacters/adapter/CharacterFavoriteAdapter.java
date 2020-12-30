package com.example.bb_characters.ui.characterdisplay.favoriteCharacters.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.bb_characters.R;
import com.example.bb_characters.ui.characterdisplay.allCharacters.adapter.CharacterViewItem;

import java.util.ArrayList;
import java.util.List;


public class CharacterFavoriteAdapter extends RecyclerView.Adapter<CharacterFavoriteAdapter.CharacterFavoriteViewHolder>{

    private final List<CharacterFavoriteViewItem> characterFavoriteViewItemList;
    private CharacterFavoriteActionInterface characterFavoriteActionInterface;

    public static class CharacterFavoriteViewHolder extends RecyclerView.ViewHolder {

        private final TextView nameTextView;
        private final TextView nicknameTextView;
        private final ImageView character_image;
        private final ImageButton delete_button;
        private final View v;
        private final CharacterFavoriteActionInterface characterFavoriteActionInterface;
        private CharacterFavoriteViewItem characterFavoriteViewItem;

        public CharacterFavoriteViewHolder(@NonNull View itemView, final CharacterFavoriteActionInterface characterFavoriteActionInterface) {

            super(itemView);
            this.v = itemView;
            nameTextView =v.findViewById(R.id.favorite_name);
            character_image = v.findViewById(R.id.character_imageview);
            nicknameTextView = v.findViewById(R.id.favorite_nickname);
            delete_button = v.findViewById(R.id.delete_icon);
            this.characterFavoriteActionInterface = characterFavoriteActionInterface;
            setupListeners();
        }

        private void setupListeners(){
            delete_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    characterFavoriteActionInterface.onRemoveFavorite(characterFavoriteViewItem.getId());
                }
            });
        }

        void bind(CharacterFavoriteViewItem characterFavoriteViewItem){
            this.characterFavoriteViewItem = characterFavoriteViewItem;
            this.nameTextView.setText(characterFavoriteViewItem.getName());
            this.nicknameTextView.setText("\"" + characterFavoriteViewItem.getNickname() + "\"");
            Glide.with(v)
                    .load(characterFavoriteViewItem.getImgUrl())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(character_image);
        }
    }

    public CharacterFavoriteAdapter(CharacterFavoriteActionInterface characterFavoriteActionInterface){
        this.characterFavoriteViewItemList = new ArrayList<>();
        this.characterFavoriteActionInterface = characterFavoriteActionInterface;
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
        return new CharacterFavoriteViewHolder(v, characterFavoriteActionInterface);
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
