package com.amora.catalogodemusica;

import android.os.Parcel;
import android.os.Parcelable;

public final class AlbumInfo implements Parcelable {
    private final String mAlbumId;
    private final String mName;
    private final String mImage;
    private final String mYear;

    public AlbumInfo(String albumId, String name, String image, String year) {
        mAlbumId = albumId;
        mName = name;
        mImage = image;
        mYear = year;
    }

    private AlbumInfo(Parcel source) {
        mAlbumId = source.readString();
        mName = source.readString();
        mImage = source.readString();
        mYear = source.readString();
    }

    public String getAlbumId() {
        return mAlbumId;
    }

    public String getName() {
        return mName;
    }

    public String getImage() {
        return mImage;
    }

    public String getYear() {
        return mYear;
    }

    @Override
    public String toString() {
        return mName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlbumInfo that = (AlbumInfo) o;

        return mAlbumId.equals(that.mAlbumId);

    }

    @Override
    public int hashCode() {
        return mAlbumId.hashCode();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mAlbumId);
        dest.writeString(mName);
        dest.writeString(mImage);
        dest.writeString(mYear);
    }

    public static final Creator<AlbumInfo> CREATOR =
            new Creator<AlbumInfo>() {

                @Override
                public AlbumInfo createFromParcel(Parcel source) {
                    return new AlbumInfo(source);
                }

                @Override
                public AlbumInfo[] newArray(int size) {
                    return new AlbumInfo[size];
                }
            };
}
