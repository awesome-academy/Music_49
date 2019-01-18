package com.framgia.music_49.data.source.local;

import com.framgia.music_49.data.model.Song;
import java.util.List;

public interface SongLocalDataCallBack {
    void onSuccessDataLocal(List<Song> songs);

    void onFailDataLocal(Exception e);
}
