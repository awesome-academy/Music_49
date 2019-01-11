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
import com.framgia.music_49.utils.Genres;
import com.framgia.music_49.utils.ItemClickListener;
import com.framgia_music_49.R;
import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment implements ItemClickListener {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        ApdaterGenre homeAdapter = new ApdaterGenre(getContext(), getGenreList(), this);
        RecyclerView recyclerViewGenre = view.findViewById(R.id.recyclerViewGenre);
        recyclerViewGenre.setHasFixedSize(true);
        recyclerViewGenre.setAdapter(homeAdapter);
//        AdapterNewAndHot adapterNewAndHot = new AdapterNewAndHot(getNewAndHotList(), getContext());
        RecyclerView recyclerViewNewAndHot = view.findViewById(R.id.recyclerViewNewAndHot);
        recyclerViewNewAndHot.setHasFixedSize(true);
//        recyclerViewNewAndHot.setAdapter(adapterNewAndHot);
    }

//    private List<NewAndHot> getNewAndHotList() {
//        List<NewAndHot> newAndHots = new ArrayList<>();
//        newAndHots.add(new NewAndHot(R.drawable.music, "Phải Không Em", "Duy Bằng"));
//        newAndHots.add(new NewAndHot(R.drawable.rock, "Fire way", "Duy Bằng"));
//        newAndHots.add(new NewAndHot(R.drawable.ambient, "Ngọn lửa cao nguyên", "Alient"));
//        newAndHots.add(new NewAndHot(R.drawable.classical, "Line Way", "Luma"));
//        newAndHots.add(new NewAndHot(R.drawable.country, "Về quê", "Cao Hùng"));
//        return newAndHots;
//    }

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
                Toast.makeText(getActivity(), "music", Toast.LENGTH_SHORT).show();
                break;
            case Genres.GENRE_ALTERNATIVE_ROCK:
                Toast.makeText(getActivity(), "GENRE_ALTERNATIVE_ROCK", Toast.LENGTH_SHORT).show();
                break;
            case Genres.GENRE_AMBIENT:
                Toast.makeText(getActivity(), "GENRE_AMBIENT", Toast.LENGTH_SHORT).show();
                break;
            case Genres.GENRE_CLASSICAL:
                Toast.makeText(getActivity(), "GENRE_CLASSICAL", Toast.LENGTH_SHORT).show();
                break;
            case Genres.GENRE_COUNTRY:
                Toast.makeText(getActivity(), "GENRE_COUNTRY", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
