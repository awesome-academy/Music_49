package com.framgia.music_49.screen.home;

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
import com.framgia.music_49.data.source.local.SongLocal;
import com.framgia.music_49.data.source.remote.SongRemoteDataSource;
import com.framgia.music_49.screen.listmusic.ListMusicFragment;
import com.framgia.music_49.utils.Genres;
import com.framgia.music_49.utils.GenresTab;
import com.framgia.music_49.utils.ItemClickListener;
import com.framgia.music_49.utils.Navigator;
import com.framgia_music_49.R;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements ItemClickListener, HomeContract.View {
    private SongNewAndHotAdapter mSongNewAndHotAdapter;
    private List<Song> mSongs;
    private Navigator mNavigator;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

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
        mNavigator = new Navigator();
        mSongs = new ArrayList<>();
        RecyclerView recyclerViewGenre = view.findViewById(R.id.recyclerViewGenre);
        RecyclerView recyclerViewNewAndHot = view.findViewById(R.id.recyclerViewNewAndHot);
        mSongNewAndHotAdapter = new SongNewAndHotAdapter(mSongs);
        GenreApdater homeAdapter = new GenreApdater(getGenreList(), this);
        recyclerViewGenre.setAdapter(homeAdapter);
        recyclerViewNewAndHot.setAdapter(mSongNewAndHotAdapter);
        recyclerViewNewAndHot.setHasFixedSize(true);
        recyclerViewGenre.setHasFixedSize(true);
    }

    private void initPresenter() {
        SongRemoteDataSource songRemoteDataSource = SongRemoteDataSource.getInstance();
        SongLocal songLocal = SongLocal.getInstance(getActivity().getContentResolver());
        SongRepository songRepository = SongRepository.getInstance(songRemoteDataSource, songLocal);
        HomePresenter homePresenter = new HomePresenter(songRepository);
        homePresenter.getSongListWithGenre(Genres.ALTERNATIVE_ROCK);
        homePresenter.setView(this);
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

    private void updateData(List<Song> list) {
        if (mSongs != null) {
            mSongs.clear();
        }
        assert list != null;
        mSongs.addAll(list);
        mSongNewAndHotAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClicked(int position) {
        switch (position) {
            case GenresTab.GENRE_MUSIC:
                mNavigator.addFragment(getActivity(), ListMusicFragment.newInstance(Genres.MUSIC),
                        R.id.frameContainer);
                break;
            case GenresTab.GENRE_ALTERNATIVE_ROCK:
                mNavigator.addFragment(getActivity(),
                        ListMusicFragment.newInstance(Genres.ALTERNATIVE_ROCK),
                        R.id.frameContainer);
                break;
            case GenresTab.GENRE_AMBIENT:
                mNavigator.addFragment(getActivity(), ListMusicFragment.newInstance(Genres.AMBIENT),
                        R.id.frameContainer);
                break;
            case GenresTab.GENRE_CLASSICAL:
                mNavigator.addFragment(getActivity(),
                        ListMusicFragment.newInstance(Genres.CLASSICAL), R.id.frameContainer);
                break;
            case GenresTab.GENRE_COUNTRY:
                mNavigator.addFragment(getActivity(), ListMusicFragment.newInstance(Genres.COUNTRY),
                        R.id.frameContainer);
                break;
        }
    }

    @Override
    public void onGetSongListWithGenreSuccess(List<Song> songs) {
        updateData(songs);
    }

    @Override
    public void onFailData(Exception e) {
        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
