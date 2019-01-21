package com.framgia.music_49.screen.listmusic;

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
import com.framgia.music_49.utils.ItemClickListener;
import com.framgia_music_49.R;
import java.util.ArrayList;
import java.util.List;

public class ListMusicAdapter
        extends RecyclerView.Adapter<ListMusicAdapter.ItemHolder> {
    private List<Song> mSongs;
    private ItemClickListener mItemClickListener;

    public ListMusicAdapter(ItemClickListener itemClickListener) {
        mSongs = new ArrayList<>();
        mItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_view_list_music, viewGroup, false);
        return new ItemHolder(itemView, mItemClickListener);
    }

    public void updateData(List<Song> list) {
        if (mSongs != null) {
            mSongs.clear();
        }
        assert list != null;
        mSongs = list;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int i) {
        itemHolder.bindData(mSongs.get(i));
    }

    @Override
    public int getItemCount() {
        return mSongs != null ? mSongs.size() : 0;
    }

    public static class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageViewListMusic;
        private TextView mTextViewItemNameSongListMusic, mTextViewNameArticsListMusic;
        private ItemClickListener mItemClickListener;

        public ItemHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            mItemClickListener = itemClickListener;
            mImageViewListMusic = itemView.findViewById(R.id.imageViewItemListMusic);
            mTextViewItemNameSongListMusic = itemView.findViewById(R.id.textViewSongNameItemList);
            mTextViewNameArticsListMusic =
                    itemView.findViewById(R.id.textViewNameArticsItemListMusic);
            itemView.setOnClickListener(this);
        }

        public void bindData(Song song) {
            Glide.with(itemView.getContext())
                    .load(song.getImageSong())
                    .apply(new RequestOptions().placeholder(R.drawable.classical))
                    .into(mImageViewListMusic);
            mTextViewItemNameSongListMusic.setText(song.getNameSong());
            mTextViewNameArticsListMusic.setText(song.getNameArtist());
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClicked(getAdapterPosition());
            }
        }
    }
}
