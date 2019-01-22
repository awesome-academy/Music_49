package com.framgia.music_49.utils;

import android.support.annotation.IntDef;

@IntDef({
        OptionLoopShuffle.NON_LOOP, OptionLoopShuffle.LOOP_ONE, OptionLoopShuffle.LOOP_ALL,
        OptionLoopShuffle.NON_SHUFFLE, OptionLoopShuffle.SHUFFLE
})
public @interface OptionLoopShuffle {
    int NON_LOOP = 0;
    int LOOP_ONE = 1;
    int LOOP_ALL = 2;
    int NON_SHUFFLE = 3;
    int SHUFFLE = 4;
}
