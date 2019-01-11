package com.framgia.music_49.data.source.remote;

import com.framgia.music_49.data.model.Song;
import java.util.List;

public interface SongRemoteDataCallBack {
    void getDataRemoteSuccess(List<Song> songs);
    void getDataRemoteFail(Exception e);
}
