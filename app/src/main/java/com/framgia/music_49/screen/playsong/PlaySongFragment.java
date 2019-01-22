package com.framgia.music_49.screen.playsong;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.framgia.music_49.data.model.Song;
import com.framgia.music_49.screen.service.ServicePlaySong;
import com.framgia_music_49.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static android.content.Context.BIND_AUTO_CREATE;
import static com.framgia.music_49.utils.Constant.ONE;
import static com.framgia.music_49.utils.OptionLoopShuffle.NON_SHUFFLE;
import static com.framgia.music_49.utils.OptionLoopShuffle.SHUFFLE;

public class PlaySongFragment extends Fragment
        implements View.OnClickListener, MediaPlayer.OnCompletionListener {
    private static final String BUNDLE_POSITION = "BUNDLE_POSITION";
    private static final String BUNDLE_LIST = "BUNDLE_LIST";
    private static ServicePlaySong sServicePlaySong;
    private int mPosition;
    private ImageView mImageViewPlaySong;
    private ImageButton mImageButtonPause;
    private ImageButton mImageButtonRandom;
    private TextView mTextViewNameSongPlaySong;
    private TextView mTextViewNameArticsPlaySong;
    private TextView mTextViewTimeProgress;
    private TextView mTextViewTimeTotal;
    private SeekBar mSeekBar;

    private List<Song> mSongs;
    private int mIsShuffle = NON_SHUFFLE;
    private Random mRandom = new Random();
    private MediaPlayer mMediaPlayer;
    private boolean mIsBoundService;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ServicePlaySong.ServicePlay binder = (ServicePlaySong.ServicePlay) service;
            sServicePlaySong = binder.getService();
            mIsBoundService = true;
            setServiceConnection();
            setTimeProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIsBoundService = false;
        }
    };

    public static PlaySongFragment newInstance(int position, ArrayList<Song> songs) {
        PlaySongFragment playSongFragment = new PlaySongFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_POSITION, position);
        bundle.putParcelableArrayList(BUNDLE_LIST, songs);
        playSongFragment.setArguments(bundle);
        return playSongFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play_song, container, false);
        initData();
        intView(view);
        startBoundService();
        setSeekBarPlaySong();
        return view;
    }

    private void initData() {
        mSongs = new ArrayList<>();
        Bundle bundle = getArguments();
        if (getArguments() != null) {
            mPosition = bundle.getInt(BUNDLE_POSITION);
            mSongs = bundle.getParcelableArrayList(BUNDLE_LIST);
        }
    }

    private void intView(View view) {
        mTextViewNameSongPlaySong = view.findViewById(R.id.textViewNameSongPlayMusic);
        mTextViewNameArticsPlaySong = view.findViewById(R.id.textViewNameArticsPlayMusic);
        mTextViewTimeProgress = view.findViewById(R.id.textViewTimeProgressPlayMusic);
        mTextViewTimeTotal = view.findViewById(R.id.textViewTimeTotalPlayMusic);
        mImageButtonPause = view.findViewById(R.id.imageButtonPausePlayMusic);
        mImageButtonRandom = view.findViewById(R.id.imageButtonRandomPlayMusic);
        mSeekBar = view.findViewById(R.id.seekbarPlaySong);

        view.findViewById(R.id.imageButtonDownloadPlayMusic).setOnClickListener(this);
        view.findViewById(R.id.imageButtonPreviousPlayMusic).setOnClickListener(this);
        view.findViewById(R.id.imageButtonPausePlayMusic).setOnClickListener(this);
        view.findViewById(R.id.imageButtonNextPlayMusic).setOnClickListener(this);
        view.findViewById(R.id.imageButtonRepeatPlayMusic).setOnClickListener(this);
        view.findViewById(R.id.imageButtonRandomPlayMusic).setOnClickListener(this);
        mImageViewPlaySong = view.findViewById(R.id.imageViewSongPlayMusic);
        mImageButtonPause.setOnClickListener(this);
    }

    private void startBoundService() {
        Intent intent = new Intent(this.getActivity(), ServicePlaySong.class);
        this.getActivity().bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
    }

    private void setServiceConnection() {
        if (mIsBoundService) {
            sServicePlaySong.playSong(mPosition, mSongs);
            setUIPlaySong();
            mImageButtonPause.setImageResource(R.drawable.icon_pause);
        }
        mIsBoundService = true;
    }

    private void setSeekBarPlaySong() {
        mSeekBar.setMax(Integer.parseInt(mSongs.get(mPosition).getDuration()));
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean input) {
                if (input) {
                    mMediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void setUIPlaySong() {
        mMediaPlayer = sServicePlaySong.getMediaPlayer();
        if (getActivity() != null) {
            mTextViewNameSongPlaySong.setText(mSongs.get(mPosition).getNameSong());
            mTextViewNameArticsPlaySong.setText(mSongs.get(mPosition).getNameArtist());
            Glide.with(getActivity())
                    .load(mSongs.get(mPosition).getImageSong())
                    .apply(new RequestOptions().placeholder(R.drawable.ambient))
                    .into(mImageViewPlaySong);
        }
    }

    private String parseDurationToStringTime(long duration) {
        return String.format(Locale.getDefault(), "%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(duration),
                TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(duration)));
    }

    private void setTimeProgress() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mTextViewTimeProgress.setText(
                        parseDurationToStringTime(mMediaPlayer.getCurrentPosition()));
                mTextViewTimeTotal.setText(parseDurationToStringTime(mMediaPlayer.getDuration()));
                handler.postDelayed(this, 500);
                mSeekBar.setProgress(mMediaPlayer.getCurrentPosition());
            }
        }, 100);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButtonDownloadPlayMusic:
                sServicePlaySong.downLoad();
                break;
            case R.id.imageButtonPreviousPlayMusic:
                handleButtonPreviousMusic();
                break;
            case R.id.imageButtonPausePlayMusic:
                handleButtonPauseMusic();
                break;
            case R.id.imageButtonRandomPlayMusic:
                setButtonRandom();
                break;
            case R.id.imageButtonRepeatPlayMusic:
                break;
            case R.id.imageButtonNextPlayMusic:
                handleButtonNextMusic();
                break;
        }
    }

    private void setButtonRandom() {
        if (mIsShuffle == NON_SHUFFLE) {
            mIsShuffle = SHUFFLE;
            mImageButtonRandom.setImageResource(R.drawable.icon_random_clicked);
        } else {
            mIsShuffle = NON_SHUFFLE;
            mImageButtonRandom.setImageResource(R.drawable.icon_random);
        }
    }

    private void handlerRandomleMusic() {
        if (mIsShuffle == SHUFFLE) {
            mPosition = mRandom.nextInt(mSongs.size());
        }
    }

    private void handleButtonNextMusic() {
        mImageButtonPause.setImageResource(R.drawable.icon_pause);
        sServicePlaySong.nextSong();
        handleNextMusic();
        setUIPlaySong();
    }

    private void handleNextMusic() {
        if (mPosition == mSongs.size() - ONE) {
            mPosition = 0;
        } else {
            mPosition++;
        }
        handlerRandomleMusic();
    }

    private void handleButtonPreviousMusic() {
        mImageButtonPause.setImageResource(R.drawable.icon_pause);
        sServicePlaySong.previousSong();
        handlePreviousMusic();
        setUIPlaySong();
    }

    private void handlePreviousMusic() {
        if (mPosition == 0) {
            mPosition = mSongs.size() - ONE;
        } else {
            mPosition--;
        }
        handlerRandomleMusic();
    }

    private void handleButtonPauseMusic() {
        if (sServicePlaySong.checkPlaySong()) {
            mImageButtonPause.setImageResource(R.drawable.icon_play);
        } else {
            mImageButtonPause.setImageResource(R.drawable.icon_pause);
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        switch (mIsShuffle) {
            case SHUFFLE:
                mPosition = mRandom.nextInt(mSongs.size());
                break;
        }
    }
}
