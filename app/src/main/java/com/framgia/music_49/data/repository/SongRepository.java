package com.framgia.music_49.data.repository;

import com.framgia.music_49.data.source.SongDataSource;
import com.framgia.music_49.data.source.local.SongLocalDataCallBack;
import com.framgia.music_49.data.source.remote.SongRemoteDataCallBack;

public class SongRepository {
    private static SongRepository sInstance;
    private SongDataSource.Remote mRemote;
    private SongDataSource.Local mLocal;
    private SongRepository(SongDataSource.Remote songDataSource) {
        mRemote = songDataSource;
    }

    public static SongRepository getInstance(SongDataSource.Remote remote) {
        if (sInstance == null) {
            sInstance = new SongRepository(remote);
        }
        return sInstance;
    }

    public void getListSongWithGenres(String genre, SongRemoteDataCallBack callBack) {
        mRemote.getListSongWithGenres(genre, callBack);
    }
    public void getListSongLocal(SongLocalDataCallBack callBack) {
        mLocal.getListSongLocal(callBack);
    }
}
