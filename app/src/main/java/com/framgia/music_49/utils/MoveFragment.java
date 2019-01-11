package com.framgia.music_49.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.framgia.music_49.screen.MainActivity;
import com.framgia.music_49.screen.fragmentHome.FragmentHome;
import com.framgia_music_49.R;

public class MoveFragment {
    public void addFragment(FragmentActivity fragmentActivity, Fragment fragment, int idLayout) {
        switch (idLayout) {
            case R.id.frameContainer:
                FragmentTransaction fragmentTransaction =
                        fragmentActivity.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(idLayout, fragment);
                fragmentTransaction.commit();
                break;
        }
    }
}
