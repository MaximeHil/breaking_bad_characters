package com.example.bb_characters.ui.characterdisplay.adapter;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bb_characters.R;

import java.util.ArrayList;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private List<CharacterViewItem> characterViewItemList;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class CharacterViewHolder extends RecyclerView.ViewHolder {
        private ImageView characterImageView;
        private View v;

        public CharacterViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            this.v = view;
            characterImageView = view.findViewById(R.id.character_imageview);
        }

        public ImageView getImageView() {
            return characterImageView;
        }

        public void bind(String imageUrl){
            Glide.with(v)
                    .load(imageUrl)
                    .into(characterImageView);
        }
    }


    public CharacterAdapter() {
        characterViewItemList = new ArrayList<>() ;
    }

    // Cette fonction reçoit la liste des personnages, les ajoute dans
    // dans l'adapter et notifie le recyclerview
    public void bindViewModels(List<CharacterViewItem> characterViewItemList){
        Log.i("ADAPTER", "DATA CHANGED");
        this.characterViewItemList.clear();
        Log.i("ADAPTER", "dataset size avant : " + characterViewItemList.size());
        this.characterViewItemList.addAll(characterViewItemList);
        Log.i("ADAPTER", "dataset size après : " + characterViewItemList.size());
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.character_image, viewGroup, false);

        return new CharacterViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CharacterViewHolder holder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Log.i("ADAPTER", "Image Url : " + characterViewItemList.get(position).getImageUrl());
        holder.bind(characterViewItemList.get(position).getImageUrl());

        //holder.getImageView().setImageURI(Uri.parse(imagesUrls.get(position)));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        Log.i("ADAPTER", "dataset size : " + characterViewItemList.size());
        return characterViewItemList.size();
    }
}
