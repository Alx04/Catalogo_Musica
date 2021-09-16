package com.amora.catalogodemusica;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static DataManager ourInstance = null;

    private List<AlbumInfo> mAlbums = new ArrayList<>();
    private List<SongInfo> mSongs = new ArrayList<>();

    public static DataManager getInstance() {
        if(ourInstance == null) {
            ourInstance = new DataManager();
            ourInstance.initializeAlbums();
            ourInstance.initializeExampleSongs();
        }
        return ourInstance;
    }

    public List<SongInfo> getSongs() {
        return mSongs;
    }

    public int createNewSong() {
        SongInfo song = new SongInfo(null, null, null, null, null,null,null);
        mSongs.add(song);
        return mSongs.size() - 1;
    }

    public int findSong(SongInfo song) {
        for(int index = 0; index < mSongs.size(); index++) {
            if(song.equals(mSongs.get(index)))
                return index;
        }

        return -1;
    }

    public void removeSong(int index) {
        mSongs.remove(index);
    }

    public List<AlbumInfo> getAlbums() {
        return mAlbums;
    }

    public AlbumInfo getAlbum(String id) {
        for (AlbumInfo album : mAlbums) {
            if (id.equals(album.getAlbumId()))
                return album;
        }
        return null;
    }

    public SongInfo getSong(String name) {
        for (SongInfo song : mSongs) {
            if (name.equals(song.getName()))
                return song;
        }
        return null;
    }

    public List<SongInfo> getSongs(AlbumInfo album) {
        ArrayList<SongInfo> songs = new ArrayList<>();
        for(SongInfo song: mSongs) {
            if(album.equals(song.getAlbum()))
                songs.add(song);
        }
        return songs;
    }

    public int getSongCount(AlbumInfo album) {
        int count = 0;
        for(SongInfo song: mSongs) {
            if(album.equals(song.getAlbum()))
                count++;
        }
        return count;
    }

    private DataManager() {
    }

    //region Initialization code

    private void initializeAlbums() {
        mAlbums.add(initializeAlbum1());
        mAlbums.add(initializeAlbum2());
        mAlbums.add(initializeAlbum3());
        mAlbums.add(initializeAlbum4());
    }

    public void initializeExampleSongs() {
        final DataManager dm = getInstance();

        AlbumInfo album = dm.getAlbum("album_love");
        mSongs.add(new SongInfo("Alone Again Or",
                "Bruce Botnick and Arthur Lee",album,"Bryan MacLean", "Folklore psicodélico","29 de julio de 1972","https://www.youtube.com/watch?v=cPbNpIG8x_s"));
        mSongs.add(new SongInfo("A House Is Not A Motel",
                "Bruce Botnick and Arthur Lee",album,"Arthur Lee","Folk rockrock psicodélico","20 de enero de 1968","https://www.youtube.com/watch?v=H3xzHYz6L5w"));

        album = dm.getAlbum("album_stones");
        mSongs.add(new SongInfo("Rocks Off",
                "Jagger and Richards",album,"Jimmy Miller","Rock and roll","12 de mayo de 1972","https://www.youtube.com/watch?v=_lNP-x94-SE"));
        mSongs.add(new SongInfo("Rip This Joint",
                "Jagger and Richards",album,"Jimmy Miller","Boogie-woogie1","12 de mayo de 1972","https://www.youtube.com/watch?v=NehZl_X3hjQ"));

        album = dm.getAlbum("album_boys");
        mSongs.add(new SongInfo("Wouldn't It Be Nice",
                "Brian Wilson/Tony Asher/Mike Love",album,"Brian Wilson","Pop barroco, sunshine pop","11 de julio de 1966","https://www.youtube.com/watch?v=nZBKFoeDKJo"));
        mSongs.add(new SongInfo("You Still Believe In Me",
                "Brian Wilson y Tony Asher",album,"Brian Wilson","Pop barroco, art pop","11 de agosto de 1966","https://www.youtube.com/watch?v=lSoM2sJ4N1M"));

        album = dm.getAlbum("album_ramones");
        mSongs.add(new SongInfo("Blitzkrieg Bop",
                "Tommy Ramone, Dee Dee Ramone",album,"Craig Leon","Punk rock","23 de abril de 1976","https://www.youtube.com/watch?v=iymtpePP8I8"));
        mSongs.add(new SongInfo("Beat On The Brat",
                "Joey Ramone",album,"Craig Leon","Punk Rock","15 febrero de 1976","https://www.youtube.com/watch?v=yVDP5M0eTcM"));
    }

    private AlbumInfo initializeAlbum1() {
        return new AlbumInfo("album_love", "Love, Forever Changes","love","1967");
    }

    private AlbumInfo initializeAlbum2() {
        return new AlbumInfo("album_stones", "The Rolling Stones, Exile On Main St","stones","1972");
    }

    private AlbumInfo initializeAlbum3() {
        return new AlbumInfo("album_boys", "The Beach Boys, Pet Sounds","boys","1966");
    }

    private AlbumInfo initializeAlbum4() {
        return new AlbumInfo("album_ramones", "Ramones, Ramones","ramones","1976");
    }
    //endregion

}
