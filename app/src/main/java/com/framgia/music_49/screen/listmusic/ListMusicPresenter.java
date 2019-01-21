package com.framgia.music_49.screen.listmusic;

import com.framgia.music_49.data.model.Song;
import com.framgia.music_49.data.repository.SongRepository;
import com.framgia.music_49.data.source.remote.SongRemoteDataCallBack;
import java.util.List;

public class ListMusicPresenter implements ListMusicContract.Presenter {
    private ListMusicContract.View mView;
    private SongRepository mSongRepository;

    public ListMusicPresenter(SongRepository songRepository) {
        mSongRepository = songRepository;
    }

    @Override
    public void getSongsWithGenres(String genre) {
        mSongRepository.getListSongWithGenres(genre, new SongRemoteDataCallBack() {
            @Override
            public void getDataRemoteSuccess(List<Song> songs) {
                mView.onGetSongsWithGenresSucces(songs);
            }

            @Override
            public void getDataRemoteFail(Exception e) {
                mView.onFailDataSource(e);
            }
        });
    }

    @Override
    public void setView(ListMusicContract.View view) {
        mView = view;
    }
}
