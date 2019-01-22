package com.framgia.music_49.screen.service;

import android.app.DownloadManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;
import com.framgia.music_49.data.model.Song;
import com.framgia_music_49.R;
import java.io.IOException;
import java.util.List;

public class ServicePlaySong extends Service implements MediaPlayer.OnPreparedListener {
    private IBinder mIBinder = new ServicePlay();
    private MediaPlayer mMediaPlayer = new MediaPlayer();
    private List<Song> mSongs;
    private int mPosition;

    public MediaPlayer getMediaPlayer() {
        return mMediaPlayer;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mIBinder;
    }

    public void setDataMusic() {
        mMediaPlayer.reset();
        try {
            mMediaPlayer.setDataSource(mSongs.get(mPosition).getLink());
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setOnPreparedListener(this);
            mMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playSong(int position, List<Song> songs) {
        mPosition = position;
        mSongs = songs;
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
        }
        setDataMusic();
    }

    public void nextSong() {
        if (mPosition == mSongs.size() - 1) {
            mPosition = 0;
        } else {
            mPosition++;
        }
        setDataMusic();
    }

    public void previousSong() {
        if (mPosition == 0) {
            mPosition = (mSongs.size() - 1);
        } else {
            mPosition--;
        }
        setDataMusic();
    }

    public boolean checkPlaySong() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            return true;
        } else {
            mMediaPlayer.start();
            return false;
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

    public void downLoad() {
        DownloadManager downloadManager =
                (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);
        String linkDownLoad = mSongs.get(mPosition).getLink();
        if (linkDownLoad != null) {
            Uri uri = Uri.parse(linkDownLoad);
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setNotificationVisibility(
                    DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setTitle(mSongs.get(mPosition).getNameSong());
            request.setDescription(R.string.down_load + "");
            assert downloadManager != null;
            downloadManager.enqueue(request);
        } else {
            Toast.makeText(this, R.string.link_error, Toast.LENGTH_SHORT).show();
        }
    }

    public class ServicePlay extends Binder {
        public ServicePlaySong getService() {
            return ServicePlaySong.this;
        }
    }
}
