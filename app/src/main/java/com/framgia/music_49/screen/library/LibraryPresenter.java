package com.framgia.music_49.screen.library;

import com.framgia.music_49.data.model.Song;
import com.framgia.music_49.data.repository.SongRepository;
import com.framgia.music_49.data.source.local.SongLocalDataCallBack;
import java.util.List;

public class LibraryPresenter implements LibraryContract.Presenter {
    private LibraryContract.View mView;
    private SongRepository mSongRepository;

    public LibraryPresenter(SongRepository songRepository) {
        mSongRepository = songRepository;
    }

    @Override
    public void getListSongLocal() {
        mSongRepository.getListSongLocal(new SongLocalDataCallBack() {
            @Override
            public void onSuccessDataLocal(List<Song> songs) {
                mView.onSuccessGetDataLocal(songs);
            }

            @Override
            public void onFailDataLocal(Exception e) {
                mView.onFailGetDataLocal(e);
            }
        });
    }

    @Override
    public void setView(LibraryContract.View view) {
        mView = view;
    }
}
