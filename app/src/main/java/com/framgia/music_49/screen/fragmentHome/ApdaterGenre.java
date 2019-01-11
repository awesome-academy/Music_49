package com.framgia.music_49.screen.fragmentHome;

import android.content.Context;
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

public class ApdaterGenre extends RecyclerView.Adapter<ApdaterGenre.ViewHolder> {
    private Context mContext;
    private List<Genre> mGenres;
    private ItemClickListener mItemClickListener;

    public ApdaterGenre(Context context, List<Genre> genres, ItemClickListener itemClickListener) {
        mContext = context;
        mGenres = genres;
        mItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView;
        itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_view_genre, viewGroup, false);
        return new ViewHolder(itemView, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.binData(mGenres.get(position));
    }

    @Override
    public int getItemCount() {
        return mGenres.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageViewGenre;
        private ItemClickListener mItemClickListener;

        public ViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            mItemClickListener = itemClickListener;
            mImageViewGenre = itemView.findViewById(R.id.imageViewGenre);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClicked(getAdapterPosition());
            }
        }

        void binData(Genre genre) {
            Glide.with(mContext).load(genre.getnImageGenre()).into(mImageViewGenre);
        }
    }
}
