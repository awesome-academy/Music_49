package com.framgia.music_49.screen.fragmentHome;

import com.framgia.music_49.data.model.Song;
import com.framgia.music_49.data.repository.SongRepository;
import com.framgia.music_49.data.source.remote.SongRemoteDataCallBack;
import java.util.List;

public class PresenterFragmentHome implements HomeContract.Presenter {
    private HomeContract.View mView;
    private SongRepository mSongRepository;

    public PresenterFragmentHome(SongRepository songRepository) {
        mSongRepository = songRepository;
    }

    @Override
    public void getSongListWithGenre(String genre) {
        mSongRepository.getListSongWithGenres(genre, new SongRemoteDataCallBack() {
            @Override
            public void getDataRemoteSuccess(List<Song> songs) {
                mView.onSuccessData(songs);
            }

            @Override
            public void getDataRemoteFail(Exception e) {
                mView.onFailData(e);
            }
        });
    }

    @Override
    public void setView(HomeContract.View view) {
        mView = view;
    }
}
