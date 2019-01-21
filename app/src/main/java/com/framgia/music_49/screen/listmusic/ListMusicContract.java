package com.framgia.music_49.screen.listmusic;

import android.view.View;
import com.framgia.music_49.data.model.Song;
import com.framgia.music_49.screen.home.HomeContract;
import java.util.List;

public interface ListMusicContract {
    interface View {
        void onGetSongsWithGenresSucces(List<Song> songs);

        void onFailDataSource(Exception e);
    }

    interface Presenter {
        void getSongsWithGenres(String genre);

        void setView(View view);
    }
}
