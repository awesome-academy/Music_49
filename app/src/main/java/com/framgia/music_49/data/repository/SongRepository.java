package com.framgia.music_49.data.repository;

import com.framgia.music_49.data.source.SongDataSource;
import com.framgia.music_49.data.source.local.SongLocalDataCallBack;
import com.framgia.music_49.data.source.remote.SongRemoteDataCallBack;

public class SongRepository {
    private static SongRepository sInstance;
    private SongDataSource.Remote mRemote;
    private SongDataSource.Local mLocal;

    public SongRepository(SongDataSource.Remote remote, SongDataSource.Local local) {
        mRemote = remote;
        mLocal = local;
    }

    public static SongRepository getInstance(SongDataSource.Remote remote,SongDataSource.Local local) {
        if (sInstance == null) {
            sInstance = new SongRepository(remote,local);
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
