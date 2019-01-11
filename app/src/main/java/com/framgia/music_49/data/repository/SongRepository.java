package com.framgia.music_49.data.repository;

import com.framgia.music_49.data.source.SongDataSource;
import com.framgia.music_49.data.source.remote.SongRemoteDataCallBack;

public class SongRepository {
    private static SongRepository mInStance;
    private SongDataSource.RemoteDataSource mRemoteDataSource;

    public SongRepository(SongDataSource.RemoteDataSource songDataSource) {
        mRemoteDataSource = songDataSource;
    }

    public static SongRepository getmInStance(SongDataSource.RemoteDataSource remoteDataSource) {
        if (mInStance == null) {
            mInStance = new SongRepository(remoteDataSource);
        }
        return mInStance;
    }

    public void getListSongWithGenres(String genre, SongRemoteDataCallBack callBack) {
        mRemoteDataSource.getListSongWithGenres(genre, callBack);
    }
}
