package com.framgia.music_49.screen.library;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.music_49.data.model.Song;
import com.framgia.music_49.data.repository.SongRepository;
import com.framgia.music_49.data.source.local.SongLocal;
import com.framgia.music_49.data.source.remote.SongRemoteDataSource;
import com.framgia.music_49.utils.ItemClickListener;
import com.framgia_music_49.R;
import java.util.List;

public class LibraryFragment extends Fragment implements ItemClickListener, LibraryContract.View {
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    public static final String EXTERNAL_STORAGE = "EXTERNAL STORAGE";
    public static final String PERMISSION_NECESSARY = "PERMISSION NECESSARY";
    public static final String PERMISSION_IS_NECESSARY = "PERMISSION IS NECESSARY";

    private LibraryAdapter mLibraryAdapter;

    public static LibraryFragment newInstance() {
        return new LibraryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);
        initView(view);
        if (checkPermissionREAD_EXTERNAL_STORAGE(getContext())) {
            initPresenter();
        }
        return view;
    }

    private void initView(View view) {
        RecyclerView recyclerViewLibrary = view.findViewById(R.id.recyclerViewLibrary);
        mLibraryAdapter = new LibraryAdapter(this);
        mLibraryAdapter.setItemClickListener(this);
        recyclerViewLibrary.setHasFixedSize(true);
        recyclerViewLibrary.setAdapter(mLibraryAdapter);
    }

    private void initPresenter() {
        SongLocal songLocal = SongLocal.getInstance(getActivity().getContentResolver());
        SongRemoteDataSource songRemoteDataSource = SongRemoteDataSource.getInstance();
        SongRepository songRepository = SongRepository.getInstance(songRemoteDataSource, songLocal);
        LibraryPresenter libraryPresenter = new LibraryPresenter(songRepository);
        libraryPresenter.setView(this);
        libraryPresenter.getListSongLocal();
    }

    public boolean checkPermissionREAD_EXTERNAL_STORAGE(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    showDialog(EXTERNAL_STORAGE, context,
                            Manifest.permission.READ_EXTERNAL_STORAGE);
                } else {
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[] { Manifest.permission.READ_EXTERNAL_STORAGE },
                            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public void showDialog(final String msg, final Context context, final String permission) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle(PERMISSION_NECESSARY);
        alertBuilder.setMessage(msg + PERMISSION_IS_NECESSARY);
        alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                ActivityCompat.requestPermissions((Activity) context, new String[] { permission },
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
            }
        });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }

    @Override
    public void onItemClicked(int position) {
    }

    @Override
    public void onSuccessGetDataLocal(List<Song> songList) {
        assert songList != null;
        mLibraryAdapter.updateData(songList);
    }

    @Override
    public void onFailGetDataLocal(Exception e) {
    }
}
