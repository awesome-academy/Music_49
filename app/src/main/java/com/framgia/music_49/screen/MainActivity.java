package com.framgia.music_49.screen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.framgia.music_49.screen.audio.AudioFragment;
import com.framgia.music_49.screen.home.HomeFragment;
import com.framgia.music_49.screen.library.LibraryFragment;
import com.framgia.music_49.utils.Navigator;
import com.framgia_music_49.R;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {
    private Navigator mNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationMain);
        mNavigator = new Navigator();
        getSupportActionBar().setTitle(R.string.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        mNavigator.addFragment(MainActivity.this, HomeFragment.newInstance(), R.id.frameContainer);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigationHome:
                getSupportActionBar().setTitle(R.string.home);
                mNavigator.addFragment(MainActivity.this, HomeFragment.newInstance(),
                        R.id.frameContainer);
                return true;
            case R.id.navigationAudio:
                getSupportActionBar().setTitle(R.string.audio);
                mNavigator.addFragment(MainActivity.this, AudioFragment.newInstance(),
                        R.id.frameContainer);
                return true;
            case R.id.navigationSearch:
                getSupportActionBar().setTitle(R.string.search);
                //add fragment search
                return true;
            case R.id.navigationLibrary:
                getSupportActionBar().setTitle(R.string.library);
                mNavigator.addFragment(MainActivity.this, LibraryFragment.newInstance(),
                        R.id.frameContainer);
                return true;
        }
        return false;
    }
}
