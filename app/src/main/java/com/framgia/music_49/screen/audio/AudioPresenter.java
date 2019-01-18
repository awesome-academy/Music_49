package com.framgia.music_49.screen.audio;

import com.framgia.music_49.data.model.Song;
import com.framgia.music_49.data.repository.SongRepository;
import com.framgia.music_49.data.source.remote.SongRemoteDataCallBack;
import java.util.List;

public class AudioPresenter implements AudioContract.Presenter {
    private AudioContract.View mView;
    private SongRepository mSongRepository;

    public AudioPresenter(SongRepository songRepository) {
        mSongRepository = songRepository;
    }

    @Override
    public void getSongsWithGenre(String genre) {
        mSongRepository.getListSongWithGenres(genre, new SongRemoteDataCallBack() {
            @Override
            public void getDataRemoteSuccess(List<Song> songs) {
                mView.onGetSongsSuccess(songs);
            }

            @Override
            public void getDataRemoteFail(Exception e) {
                mView.onFailData(e);
            }
        });
    }

    @Override
    public void setView(AudioContract.View view) {
        mView = view;
    }
}
