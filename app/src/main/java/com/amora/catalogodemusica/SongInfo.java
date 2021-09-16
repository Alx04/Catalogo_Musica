package com.amora.catalogodemusica;

import android.os.Parcel;
import android.os.Parcelable;

public final class SongInfo implements Parcelable {
    private String mName;
    private String mAuthor;
    private AlbumInfo mAlbum;
    private String mComposer;
    private String mGender;
    private String mDate;
    private String mUrl;

    public SongInfo(String name, String author, AlbumInfo album,String composer, String gender, String date, String url) {
        mName = name;
        mAuthor = author;
        mAlbum = album;
        mComposer = composer;
        mGender = gender;
        mDate = date;
        mUrl = url;
    }

    private SongInfo(Parcel source) {
        mName = source.readString();
        mAuthor = source.readString();
        mAlbum = source.readParcelable(AlbumInfo.class.getClassLoader());
        mComposer = source.readString();
        mGender = source.readString();
        mDate = source.readString();
        mUrl = source.readString();
    }

    public AlbumInfo getAlbum() {
        return mAlbum;
    }
    public void setAlbum(AlbumInfo album) {
        mAlbum = album;
    }

    public String getName() {
        return mName;
    }
    public void setName(String name) {
        mName = name;
    }

    public String getAuthor() {
        return mAuthor;
    }
    public void setAuthor(String author) {
        mAuthor = author;
    }

    public String getComposer() {
        return mComposer;
    }
    public void setComposer(String composer) {
        mComposer = composer;
    }

    public String getGender() {
        return mGender;
    }
    public void setGender(String gender) {
        mGender = gender;
    }

    public String getDate() {
        return mDate;
    }
    public void setDate(String date) {
        mDate = date;
    }

    public String getUrl() {
        return mUrl;
    }
    public void setUrl(String url) {
        mUrl = url;
    }

    private String getCompareKey() {
        //return mAlbum.getAlbumId() + "|" + mName + "|" + mAuthor;
        return  mName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SongInfo that = (SongInfo) o;

        return getCompareKey().equals(that.getCompareKey());
    }

    @Override
    public int hashCode() {
        return getCompareKey().hashCode();
    }

    @Override
    public String toString() {
        return getCompareKey();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeParcelable(mAlbum, 0);
        dest.writeString(mName);
        dest.writeString(mAuthor);
        dest.writeString(mComposer);
        dest.writeString(mGender);
        dest.writeString(mDate);
        dest.writeString(mUrl);
    }

    public static final Parcelable.Creator<SongInfo> CREATOR = new Parcelable.Creator<SongInfo>(){
        @Override
        public SongInfo createFromParcel(Parcel source) {
            return new SongInfo(source);
        }

        @Override
        public SongInfo[] newArray(int size) {
            return new SongInfo[size];
        }
    };
}
