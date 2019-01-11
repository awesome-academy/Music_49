package com.framgia.music_49.data.source.remote;

import com.framgia.music_49.data.source.SongDataSource;
import com.framgia.music_49.utils.Constant;

public class SongRemoteDataSource implements SongDataSource.RemoteDataSource {

    @Override
    public void getListSongWithGenres(String genre, SongRemoteDataCallBack listener) {
        GetJsonFromUrl getJsonFromUrl = new GetJsonFromUrl(listener);
        getJsonFromUrl.execute(Constant.GENRES_URL + genre);
    }
}
