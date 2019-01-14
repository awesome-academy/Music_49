package com.framgia.music_49.data.source.remote;

import com.framgia.music_49.data.source.SongDataSource;
import com.framgia.music_49.utils.Constant;

public class SongRemoteDataSource implements SongDataSource.Remote {
    private static SongRemoteDataSource sInstance;

    private SongRemoteDataSource() {
    }

    public static SongRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new SongRemoteDataSource();
        }
        return sInstance;
    }

    @Override
    public void getListSongWithGenres(String genre, SongRemoteDataCallBack listener) {
        GetJsonFromUrl getJsonFromUrl = new GetJsonFromUrl(listener);
        getJsonFromUrl.execute(Constant.GENRES_URL + genre);
    }
}
