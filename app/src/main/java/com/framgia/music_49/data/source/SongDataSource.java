package com.framgia.music_49.data.source;

import com.framgia.music_49.data.source.remote.SongRemoteDataCallBack;

public interface SongDataSource {
    interface RemoteDataSource extends SongDataSource {
        void getListSongWithGenres(String genre, SongRemoteDataCallBack listener);
    }
}
