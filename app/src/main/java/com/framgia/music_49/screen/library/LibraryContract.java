package com.framgia.music_49.screen.library;

import com.framgia.music_49.data.model.Song;
import java.util.List;

public interface LibraryContract {
    interface View {
        void onSuccessGetDataLocal(List<Song> songList);

        void onFailGetDataLocal(Exception e);
    }

    interface Presenter {
        void getListSongLocal();

        void setView(View view);
    }
}
