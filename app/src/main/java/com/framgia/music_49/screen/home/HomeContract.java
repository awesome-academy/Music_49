package com.framgia.music_49.screen.home;

import com.framgia.music_49.data.model.Song;
import java.util.List;

public interface HomeContract {
    interface View {
        void onGetSongListWithGenreSuccess(List<Song> songs);

        void onFailData(Exception e);
    }

    interface Presenter {
        void getSongListWithGenre(String genre);

        void setView(View view);
    }
}
