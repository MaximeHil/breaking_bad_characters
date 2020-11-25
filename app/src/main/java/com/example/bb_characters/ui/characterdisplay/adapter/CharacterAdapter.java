package com.example.bb_characters.ui.characterdisplay.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bb_characters.R;

import java.util.ArrayList;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private ArrayList<String> imagesUrls;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class CharacterViewHolder extends RecyclerView.ViewHolder {
        //private final TextView textView;
        private ImageView characterImageView;
        private View v;

        public CharacterViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            this.v = view;
            characterImageView = (ImageView) view.findViewById(R.id.character_imageview);
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

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet ArrayList<String> containing the data to populate views to be used
     * by RecyclerView.
     */
    public CharacterAdapter(ArrayList<String> dataSet) {
        imagesUrls = dataSet;
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
        holder.bind(imagesUrls.get(position));

        //holder.getImageView().setImageURI(Uri.parse(imagesUrls.get(position)));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return imagesUrls.size();
    }
}
