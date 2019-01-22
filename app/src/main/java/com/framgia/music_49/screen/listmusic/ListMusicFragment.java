package com.framgia.music_49.screen.listmusic;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.music_49.data.model.Song;
import com.framgia.music_49.data.repository.SongRepository;
import com.framgia.music_49.data.source.local.SongLocal;
import com.framgia.music_49.data.source.remote.SongRemoteDataSource;
import com.framgia.music_49.screen.playsong.PlaySongFragment;
import com.framgia.music_49.utils.ItemClickListener;
import com.framgia.music_49.utils.Navigator;
import com.framgia_music_49.R;
import java.util.ArrayList;
import java.util.List;

public class ListMusicFragment extends Fragment
        implements ItemClickListener, ListMusicContract.View {
    private static String BUNDLE_GENRE = "BUNDLE GENRE";
    private ListMusicAdapter mListMusicAdapter;
    private int mPosition;
    private List<Song> mSongs;
    private Navigator mNavigator;

    public static ListMusicFragment newInstance(String genre) {
        ListMusicFragment listMusicFragment = new ListMusicFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_GENRE, genre);
        listMusicFragment.setArguments(bundle);
        return listMusicFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_music, container, false);
        initView(view);
        initPresenter();
        return view;
    }

    private void initPresenter() {
        SongRemoteDataSource songRemoteDataSource = SongRemoteDataSource.getInstance();
        SongLocal songLocal = SongLocal.getInstance(getActivity().getContentResolver());
        SongRepository songRepository = SongRepository.getInstance(songRemoteDataSource, songLocal);
        assert getArguments() != null;
        String genre = getArguments().getString(BUNDLE_GENRE);
        ListMusicPresenter listMusicPresenter = new ListMusicPresenter(songRepository);
        listMusicPresenter.setView(this);
        listMusicPresenter.getSongsWithGenres(genre);
    }

    private void initView(View view) {
        mSongs = new ArrayList<>();
        mNavigator = new Navigator();
        RecyclerView recyclerViewListMusic = view.findViewById(R.id.recyclerViewListMusic);
        mListMusicAdapter = new ListMusicAdapter(this);
        recyclerViewListMusic.setAdapter(mListMusicAdapter);
        recyclerViewListMusic.setHasFixedSize(true);
    }

    @Override
    public void onItemClicked(int position) {
        mPosition = position;
        moveFragmentPlaySong();
    }

    private void moveFragmentPlaySong() {
        PlaySongFragment playSongFragment =
                PlaySongFragment.newInstance(mPosition, (ArrayList<Song>) mSongs);
        mNavigator.addFragment(getActivity(), playSongFragment, R.id.frameContainer);
    }

    @Override
    public void onGetSongsWithGenresSucces(List<Song> songs) {
        assert songs != null;
        mListMusicAdapter.updateData(songs);
        mSongs.addAll(songs);
    }

    @Override
    public void onFailDataSource(Exception e) {

    }
}
