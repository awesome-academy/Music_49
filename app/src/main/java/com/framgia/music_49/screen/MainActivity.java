package com.framgia.music_49.screen;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import com.framgia.music_49.screen.fragmentHome.FragmentHome;
import com.framgia.music_49.utils.MoveFragment;
import com.framgia_music_49.R;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {
    private ActionBar mActionBar;
    private MoveFragment mMoveFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mActionBar = getSupportActionBar();
        mMoveFragment = new MoveFragment();
        assert mActionBar != null;
        mActionBar.hide();
        mMoveFragment.addFragment(MainActivity.this, FragmentHome.newInstance(),
                R.id.frameContainer);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationMain);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigationHome:
                mActionBar.setTitle(R.string.home);
                mMoveFragment.addFragment(MainActivity.this, FragmentHome.newInstance(),
                        R.id.frameContainer);
                return true;
            case R.id.navigationAudio:
                mActionBar.setTitle(R.string.audio);
                //add fragment audio
                return true;
            case R.id.navigationSearch:
                mActionBar.setTitle(R.string.search);
                //add fragment search
                return true;
            case R.id.navigationLibrary:
                mActionBar.setTitle(R.string.library);
                //add fragment library
                return true;
        }
        return false;
    }
}
