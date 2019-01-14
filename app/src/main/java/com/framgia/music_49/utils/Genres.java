package com.framgia.music_49.utils;

import android.support.annotation.StringDef;

@StringDef({
        Genres.ALTERNATIVE_ROCK, Genres.MUSIC, Genres.AMBIENT, Genres.CLASSICAL, Genres.COUNTRY,
        Genres.AUDIO
})
public @interface Genres {
    String AUDIO = "audio";
    String MUSIC = "all music";
    String ALTERNATIVE_ROCK = "alternative rock";
    String AMBIENT = "ambient";
    String CLASSICAL = "classical";
    String COUNTRY = "country";
}
