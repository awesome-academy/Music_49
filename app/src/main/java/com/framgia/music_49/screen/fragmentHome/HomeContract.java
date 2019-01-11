package com.framgia.music_49.screen.fragmentHome;

import com.framgia.music_49.data.model.Song;
import java.net.URL;
import java.util.List;

public interface HomeContract {
    interface View {
        void onSuccessData(List<Song> songs);

        void onFailData(Exception e);
    }

    interface Presenter {
        void getSongListWithGenre(String genre);

        void setView(View view);
    }
}
