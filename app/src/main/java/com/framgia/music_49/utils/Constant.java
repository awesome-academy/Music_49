package com.framgia.music_49.utils;

import com.framgia_music_49.BuildConfig;

public class Constant {
    public static final String KEY = BuildConfig.API_KEY;
    public static final String CLIENT_ID = "?client_id=" + KEY;
    public static final String HTTP = "https://api.soundcloud.com/tracks";
    public static final String LINKED = "&linked_partitioning=1&limit=10";
    public static final String GENRES = "&genres=";
    public static final String GENRES_URL = HTTP + CLIENT_ID + LINKED + GENRES;
}
