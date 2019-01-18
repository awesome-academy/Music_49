package com.framgia.music_49.data.source.local;

import android.content.ContentResolver;
import com.framgia.music_49.data.source.SongDataSource;

public class SongLocal implements SongDataSource.Local {
    private static SongLocal sInstance;
    private static ContentResolver mResolver;

    private SongLocal(ContentResolver resolver) {
        mResolver = resolver;
    }

    public static SongLocal getInstance(ContentResolver resolver) {
        if (sInstance == null) {
            sInstance = new SongLocal(resolver);
        }
        return sInstance;
    }

    @Override
    public void getListSongLocal(SongLocalDataCallBack listener) {
        LoadAllSongLocal loadAllSongLocal = new LoadAllSongLocal(mResolver);
        listener.onSuccessDataLocal(loadAllSongLocal.getSongs());
    }
}
