package com.framgia.music_49.Data.Repository;

import com.framgia.music_49.Data.Source.Remote.DataRemote;

public interface SongRepository {

    interface SongRemote {
        void getSongRemote(DataRemote dataRemote);
    }
}
