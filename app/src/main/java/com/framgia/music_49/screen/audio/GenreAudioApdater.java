package com.framgia.music_49.screen.audio;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.framgia.music_49.data.model.Genre;
import com.framgia.music_49.utils.ItemClickListener;
import com.framgia_music_49.R;
import java.util.List;

public class GenreAudioApdater extends RecyclerView.Adapter<GenreAudioApdater.ItemHolder> {
    private List<Genre> mGenres;
    private ItemClickListener mItemClickListener;

    public GenreAudioApdater(List<Genre> genres, ItemClickListener itemClickListener) {
        mGenres = genres;
        mItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_view_audio_genre, viewGroup, false);
        return new ItemHolder(itemView, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder viewHolder, int position) {
        viewHolder.binData(mGenres.get(position));
    }

    @Override
    public int getItemCount() {
        return mGenres != null ? mGenres.size() : 0;
    }

    public static class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageViewGenre;
        private ItemClickListener mItemClickListener;

        ItemHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            mItemClickListener = itemClickListener;
            mImageViewGenre = itemView.findViewById(R.id.imageViewAudioGenre);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClicked(getAdapterPosition());
            }
        }

        void binData(Genre genre) {
            Glide.with(itemView.getContext()).load(genre.getImageGenre()).into(mImageViewGenre);
        }
    }
}
