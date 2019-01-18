package com.framgia.music_49.data.source.local;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import com.framgia.music_49.data.model.Song;
import java.util.ArrayList;
import java.util.List;

public class LoadAllSongLocal {
    private static final String ALBUM_ART = "content://media/external/audio/albumart";
    private ContentResolver mContentResolver;
    private List<Song> mSongs;

    LoadAllSongLocal(ContentResolver resolver) {
        mContentResolver = resolver;
        mSongs = new ArrayList<>();
    }

    public List<Song> getSongs() {
        Cursor cursor =
                mContentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null,
                        null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int songName = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int songArtist = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int songLink = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            int songImage = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
            int songDuration = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION);

            do {
                String currentName = cursor.getString(songName);
                String currentArtist = cursor.getString(songArtist);
                String currentLink = cursor.getString(songLink);
                String currentImage = String.valueOf(
                        ContentUris.withAppendedId(Uri.parse(ALBUM_ART), cursor.getInt(songImage)));
                String currentDuration = cursor.getString(songDuration);
                Song song = new Song.Builder().setNameSong(currentName)
                        .setNameArtist(currentArtist)
                        .setLink(currentLink)
                        .setImageSong(currentImage)
                        .setDuration(currentDuration)
                        .build();
                mSongs.add(song);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return mSongs;
    }
}
