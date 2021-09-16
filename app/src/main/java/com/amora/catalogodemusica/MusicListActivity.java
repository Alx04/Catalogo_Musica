package com.amora.catalogodemusica;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MusicListActivity extends AppCompatActivity {

    private MusicRecyclerAdapter mAlbumRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_music_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*Carga datos a la lista*/
        initializeDisplayContent();
    }

    private void initializeDisplayContent() {
        final RecyclerView recyclerSongs = (RecyclerView) findViewById(R.id.list_albums);
        final LinearLayoutManager songsLayoutManager = new LinearLayoutManager(this);
        recyclerSongs.setLayoutManager(songsLayoutManager);

        List<AlbumInfo> albums = DataManager.getInstance().getAlbums();
        mAlbumRecyclerAdapter = new MusicRecyclerAdapter(this, albums);
        recyclerSongs.setAdapter(mAlbumRecyclerAdapter);
    }
}