package com.framgia.music_49.screen.audio;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.framgia.music_49.data.model.Genre;
import com.framgia.music_49.data.model.Song;
import com.framgia.music_49.data.repository.SongRepository;
import com.framgia.music_49.data.source.remote.SongRemoteDataSource;
import com.framgia.music_49.utils.Genres;
import com.framgia.music_49.utils.GenresTab;
import com.framgia.music_49.utils.ItemClickListener;
import com.framgia_music_49.R;
import java.util.ArrayList;
import java.util.List;

public class AudioFragment extends Fragment implements ItemClickListener, AudioContract.View {
    private ListAudioAdapter mListAudioAdapter;

    public static AudioFragment newInstance() {
        return new AudioFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_audio, container, false);
        initView(view);
        initPresenter();
        return view;
    }

    private void initView(View view) {
        RecyclerView recyclerViewGenre = view.findViewById(R.id.recyclerViewAudioGenre);
        RecyclerView recyclerViewListAudio = view.findViewById(R.id.recyclerViewAudioListMusic);
        GenreAudioApdater genreAudioApdater = new GenreAudioApdater(getGenreList(), this);
        mListAudioAdapter = new ListAudioAdapter(this);
        mListAudioAdapter.setItemClickListener(this);
        recyclerViewListAudio.setHasFixedSize(true);
        recyclerViewGenre.setHasFixedSize(true);
        recyclerViewListAudio.setAdapter(mListAudioAdapter);
        recyclerViewGenre.setAdapter(genreAudioApdater);
    }

    private void initPresenter() {
        SongRemoteDataSource songRemoteDataSource = SongRemoteDataSource.getInstance();
        SongRepository songRepository = SongRepository.getInstance(songRemoteDataSource);
        AudioPresenter audioPresenter = new AudioPresenter(songRepository);
        audioPresenter.getSongsWithGenre(Genres.AUDIO);
        audioPresenter.setView(this);
    }

    private List<Genre> getGenreList() {
        List<Genre> genreList = new ArrayList<>();
        genreList.add(new Genre((R.drawable.music)));
        genreList.add(new Genre((R.drawable.rock)));
        genreList.add(new Genre((R.drawable.ambient)));
        genreList.add(new Genre((R.drawable.classical)));
        genreList.add(new Genre((R.drawable.country)));
        return genreList;
    }

    @Override
    public void onGetSongsSuccess(List<Song> songs) {
        assert songs != null;
        mListAudioAdapter.updateData(songs);
    }

    @Override
    public void onFailData(Exception e) {
        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClicked(int position) {
        switch (position) {
            case GenresTab.GENRE_MUSIC:
                //move fragment listmusic by genre: all-music
                break;
            case GenresTab.GENRE_ALTERNATIVE_ROCK:
                //move fragment listmusic by genre: ALTERNATIVE_ROCK
                break;
            case GenresTab.GENRE_AMBIENT:
                //move fragment listmusic by genre: AMBIENT
                break;
            case GenresTab.GENRE_CLASSICAL:
                //move fragment listmusic by genre: CLASSICAL
                break;
            case GenresTab.GENRE_COUNTRY:
                //move fragment listmusic by genre: COUNTRY
                break;
        }
    }
}
