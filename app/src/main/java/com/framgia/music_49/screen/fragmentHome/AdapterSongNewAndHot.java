package com.framgia.music_49.screen.fragmentHome;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.framgia.music_49.data.model.Song;
import com.framgia_music_49.R;
import java.util.List;

public class AdapterSongNewAndHot extends RecyclerView.Adapter<AdapterSongNewAndHot.ViewHolder> {
    private List<Song> mSongs;
    Context mContext;

    public AdapterSongNewAndHot(List<Song> songs, Context context) {
        mSongs = songs;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_view_song_new_and_hot, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.binData(mSongs.get(position));
    }

    @Override
    public int getItemCount() {
        return mSongs != null ? mSongs.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageViewSongNewAndHot;
        private TextView mTextViewNameSongNewAndHot, mTextViewNameArticsNewAndHot;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageViewSongNewAndHot = itemView.findViewById(R.id.imageViewItemNewAndHot);
            mTextViewNameSongNewAndHot = itemView.findViewById(R.id.textViewSongNameNewAndHot);
            mTextViewNameArticsNewAndHot = itemView.findViewById(R.id.textViewArticsNameNewAndHot);
        }

        void binData(Song song) {

            Glide.with(mContext)
                    .load(song.getImageSong())
                    .apply(new RequestOptions().placeholder(R.drawable.classical))
                    .into(mImageViewSongNewAndHot);
            mTextViewNameSongNewAndHot.setText(song.getNameSong());
            mTextViewNameArticsNewAndHot.setText(song.getNameArtist());
        }
    }
}
