package com.framgia.music_49.screen.fragmentHome;

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
import com.framgia.music_49.utils.ItemClickListener;
import com.framgia_music_49.R;
import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment implements ItemClickListener, HomeContract.View {
    private List<Song> mSongs = new ArrayList<>();
    private AdapterSongNewAndHot mAdapterSongNewAndHot;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        initPresenter();
        return view;
    }

    private void initView(View view) {
        ApdaterGenre homeAdapter = new ApdaterGenre(getContext(), getGenreList(), this);
        RecyclerView recyclerViewGenre = view.findViewById(R.id.recyclerViewGenre);
        recyclerViewGenre.setHasFixedSize(true);
        recyclerViewGenre.setAdapter(homeAdapter);
        mAdapterSongNewAndHot = new AdapterSongNewAndHot(mSongs, getContext());
        RecyclerView recyclerViewNewAndHot = view.findViewById(R.id.recyclerViewNewAndHot);
        recyclerViewNewAndHot.setHasFixedSize(true);
        recyclerViewNewAndHot.setAdapter(mAdapterSongNewAndHot);
    }

    private void initPresenter() {
        SongRemoteDataSource songRemoteDataSource = new SongRemoteDataSource();
        SongRepository songRepository = SongRepository.getmInStance(songRemoteDataSource);
        PresenterFragmentHome presenterFragmentHome = new PresenterFragmentHome(songRepository);
        presenterFragmentHome.getSongListWithGenre(Genres.ALTERNATIVE_ROCK);
        presenterFragmentHome.setView(this);
    }

    public static FragmentHome newInstance() {
        return new FragmentHome();
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
    public void onItemClicked(int position) {
        switch (position) {
            case Genres.GENRE_MUSIC:
                //move fragment listmusic by genre: all-music
                break;
            case Genres.GENRE_ALTERNATIVE_ROCK:
                //move fragment listmusic by genre: ALTERNATIVE_ROCK
                break;
            case Genres.GENRE_AMBIENT:
                //move fragment listmusic by genre: AMBIENT
                break;
            case Genres.GENRE_CLASSICAL:
                //move fragment listmusic by genre: CLASSICAL
                break;
            case Genres.GENRE_COUNTRY:
                //move fragment listmusic by genre: COUNTRY
                break;
        }
    }

    @Override
    public void onSuccessData(List<Song> songs) {
        mSongs.addAll(songs);
        mAdapterSongNewAndHot.notifyDataSetChanged();
    }

    @Override
    public void onFailData(Exception e) {
        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
