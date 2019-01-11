package com.framgia.music_49;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.framgia.music_49.Data.Source.Remote.FetchDataRemote;
import com.framgia_music_49.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FetchDataRemote fetchDataRemote = new FetchDataRemote();
        fetchDataRemote.execute(Constant.GENRES_URL);
    }
}
