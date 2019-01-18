package com.framgia.music_49.screen.audio;

import com.framgia.music_49.data.model.Song;
import java.util.List;

public interface AudioContract {
    interface View {
        void onGetSongsSuccess(List<Song> songs);

        void onFailData(Exception e);
    }

    interface Presenter {
        void getSongsWithGenre(String genre);

        void setView(View view);
    }
}
