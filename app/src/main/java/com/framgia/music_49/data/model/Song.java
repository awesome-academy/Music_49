package com.framgia.music_49.data.model;

public class Song {
    private String mNameSong;
    private String mNameArtist;
    private String mImageSong;
    private String mLink;
    private String mDuration;
    private String mDownloadLink;

    public Song(Builder builder) {
        mNameSong = builder.mNameSong;
        mNameArtist = builder.mNameArtist;
        mImageSong = builder.mImageSong;
        mLink = builder.mLink;
        mDuration = builder.mDuration;
        mDownloadLink = builder.mDownloadLink;
    }

    public String getNameSong() {
        return mNameSong;
    }

    public void setNameSong(String nameSong) {
        mNameSong = nameSong;
    }

    public String getNameArtist() {
        return mNameArtist;
    }

    public void setNameArtist(String nameArtist) {
        mNameArtist = nameArtist;
    }

    public String getImageSong() {
        return mImageSong;
    }

    public void setImageSong(String imageSong) {
        mImageSong = imageSong;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        mLink = link;
    }

    public String getDuration() {
        return mDuration;
    }

    public void setDuration(String duration) {
        mDuration = duration;
    }

    public String getDownloadLink() {
        return mDownloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        mDownloadLink = downloadLink;
    }

    public static class Builder {

        public Builder() {
        }

        public Song build() {
            return new Song(this);
        }

        private String mNameSong;
        private String mNameArtist;
        private String mImageSong;
        private String mLink;
        private String mDuration;
        private String mDownloadLink;

        public Builder setNameSong(String nameSong) {
            mNameSong = nameSong;
            return this;
        }

        public Builder setNameArtist(String nameArtist) {
            mNameArtist = nameArtist;
            return this;
        }

        public Builder setImageSong(String imageSong) {
            mImageSong = imageSong;
            return this;
        }

        public Builder setLink(String link) {
            mLink = link;
            return this;
        }

        public Builder setDuration(String duration) {
            mDuration = duration;
            return this;
        }

        public Builder setDownloadLink(String downloadLink) {
            mDownloadLink = downloadLink;
            return this;
        }
    }

    public final class JsonEntity {
        public static final String NAME_SONG = "title";
        public static final String NAME_ARTIST = "label_name";
        public static final String URL_IMAGE = "artwork_url";
        public static final String URL_STREAM = "stream_url";
        public static final String DURATION = "duration";
        public static final String URL_DOWNLOAD = "download_url";
    }
}
