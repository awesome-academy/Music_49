package com.framgia.music_49.data.source;

import com.framgia.music_49.data.source.local.SongLocalDataCallBack;
import com.framgia.music_49.data.source.remote.SongRemoteDataCallBack;

public interface SongDataSource {
    interface Remote {
        void getListSongWithGenres(String genre, SongRemoteDataCallBack listener);
    }

    interface Local {
        void getListSongLocal(SongLocalDataCallBack listener);
    }
}
