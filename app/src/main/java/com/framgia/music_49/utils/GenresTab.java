package com.framgia.music_49.utils;

import android.support.annotation.IntDef;

@IntDef({
        GenresTab.GENRE_MUSIC, GenresTab.GENRE_ALTERNATIVE_ROCK, GenresTab.GENRE_AMBIENT,
        GenresTab.GENRE_CLASSICAL, GenresTab.GENRE_COUNTRY
})
public @interface GenresTab {
    int GENRE_MUSIC = 0;
    int GENRE_ALTERNATIVE_ROCK = 1;
    int GENRE_AMBIENT = 2;
    int GENRE_CLASSICAL = 3;
    int GENRE_COUNTRY = 4;
}
