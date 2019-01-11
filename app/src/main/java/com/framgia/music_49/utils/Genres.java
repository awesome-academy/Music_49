package com.framgia.music_49.utils;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

@StringDef({
        Genres.ALTERNATIVE_ROCK, Genres.MUSIC, Genres.AMBIENT, Genres.CLASSICAL, Genres.COUNTRY,
        Genres.AUDIO
})
@IntDef({
        Genres.GENRE_MUSIC, Genres.GENRE_ALTERNATIVE_ROCK, Genres.GENRE_AMBIENT,
        Genres.GENRE_CLASSICAL, Genres.GENRE_COUNTRY
})
public @interface Genres {
    String AUDIO = "audio";
    String MUSIC = "all music";
    String ALTERNATIVE_ROCK = "alternative rock";
    String AMBIENT = "ambient";
    String CLASSICAL = "classical";
    String COUNTRY = "country";
    int GENRE_MUSIC = 0;
    int GENRE_ALTERNATIVE_ROCK = 1;
    int GENRE_AMBIENT = 2;
    int GENRE_CLASSICAL = 3;
    int GENRE_COUNTRY = 4;
}
