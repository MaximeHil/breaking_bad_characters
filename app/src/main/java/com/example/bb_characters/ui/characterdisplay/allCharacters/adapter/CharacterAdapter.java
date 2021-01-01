package com.example.bb_characters.ui.characterdisplay.allCharacters.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bb_characters.R;

import java.util.ArrayList;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private List<CharacterViewItem> characterViewItemList;
    private CharacterActionInterface characterActionInterface;
    private boolean isList;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class CharacterViewHolder extends RecyclerView.ViewHolder {
        private ImageButton characterImageButton;
        private TextView nameTextView;
        private View v;
        private CharacterViewItem characterViewItem;
        private CharacterActionInterface characterActionInterface;
        private boolean isList;

        public CharacterViewHolder(View view, final CharacterActionInterface characterActionInterface, boolean isList) {
            super(view);
            // Define click listener for the ViewHolder's View
            this.v = view;
            this.characterActionInterface = characterActionInterface;
            characterImageButton = view.findViewById(R.id.character_imageview);
            this.isList = isList;
            //if(isList){
                this.nameTextView = v.findViewById(R.id.character_text);
            //}
            setupListener();

        }

        private void setupListener() {
            characterImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    characterActionInterface.onCharacterClicked(characterViewItem.getCharacterId());
                }
            });
        }

        public ImageButton getImageButton() {
            return characterImageButton;
        }

        public void bind(CharacterViewItem viewItem){
            this.characterViewItem = viewItem;
            Glide.with(v)
                    .load(viewItem.getImageUrl())
                    .into(characterImageButton);

            if(isList){
                this.nameTextView.setText(characterViewItem.getName());
            }
        }
    }


    public CharacterAdapter(CharacterActionInterface characterActionInterface, boolean isList) {
        characterViewItemList = new ArrayList<>() ;
        this.characterActionInterface = characterActionInterface;
        this.isList = isList;
    }

    // Cette fonction reçoit la liste des personnages, les ajoute dans
    // dans l'adapter et notifie le recyclerview
    public void bindViewModels(List<CharacterViewItem> characterViewItemList){
        this.characterViewItemList.clear();
        this.characterViewItemList.addAll(characterViewItemList);
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view;
        if(isList){
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.character_image_with_name, viewGroup, false);
        }else {
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.character_image, viewGroup, false);
        }

        return new CharacterViewHolder(view, characterActionInterface, isList);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CharacterViewHolder holder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        holder.bind(characterViewItemList.get(position));

        //holder.getImageView().setImageURI(Uri.parse(imagesUrls.get(position)));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return characterViewItemList.size();
    }
}
