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
import com.framgia_music_49.R;
import java.util.List;

public class AdapterNewAndHot extends RecyclerView.Adapter<AdapterNewAndHot.ViewHolder> {
    private List<NewAndHot> mNewAndHots;
    Context mContext;

    public AdapterNewAndHot(List<NewAndHot> newAndHots, Context context) {
        mNewAndHots = newAndHots;
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
        viewHolder.binData(mNewAndHots.get(position));
    }

    @Override
    public int getItemCount() {
        return mNewAndHots.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageViewNewAndHot;
        private TextView mTextViewNameSong, mTextViewNameArtics;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageViewNewAndHot = itemView.findViewById(R.id.imageViewItemNewAndHot);
            mTextViewNameSong = itemView.findViewById(R.id.textViewSongNameNewAndHot);
            mTextViewNameArtics = itemView.findViewById(R.id.textViewArticsNameNewAndHot);
        }

        void binData(NewAndHot newAndHot) {
            Glide.with(mContext).load(newAndHot.getImageNewAndHot()).into(mImageViewNewAndHot);
            mTextViewNameSong.setText(newAndHot.getNameSong());
            mTextViewNameArtics.setText(newAndHot.getNameArtics());
        }
    }
}
