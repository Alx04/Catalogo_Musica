package com.amora.catalogodemusica;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class MusicActivity extends AppCompatActivity {

    /*Se declara porque es este activity quien recibe la informacion del item seleccionado*/
    public static final String ALBUM_POSITION = "com.amora.catalogodemusica.ALBUM_POSITION";
    public static final int POSITION_NOT_SET = -1;
    private AlbumInfo mAlbum;
    private SongInfo mSong;
    private String url;
    private EditText textSongAuthor;
    private EditText textSongComposer;
    private EditText textSongGender;
    private EditText textSongDate;
    private EditText textAlbumName;
    private EditText textSongUrl;
    private TextView textUrl;
    private Button button_video;

    /*Metodo de inicio de un activitynv / Similar a un constructor*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Indica con cual layout se relaciona*/
        setContentView(R.layout.activity_music);

        /*Carga el encabezado*/
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*Guarda en objeto album los datos enviado por parametros de la lista*/
        readDisplayStateValues();

        /*Cargar datos en un spinner*/
        Spinner spinnerSongs = findViewById(R.id.spinner_songs);
        List<SongInfo> songs = DataManager.getInstance().getSongs(mAlbum);
        ArrayAdapter<SongInfo> adapterSongs = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, songs);
        adapterSongs.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSongs.setAdapter(adapterSongs);

        /*Carga nombre del album*/
        textAlbumName = findViewById(R.id.text_album_name);
        textAlbumName.setText(mAlbum.getName());

        /*Identifica los elementos de la vista*/
        textSongAuthor = findViewById(R.id.text_song_author);
        textSongComposer = findViewById(R.id.text_song_composer);
        textSongGender = findViewById(R.id.text_song_gender);
        textSongDate = findViewById(R.id.text_song_date);
        textSongUrl = findViewById(R.id.text_song_url);
        textUrl = findViewById(R.id.textView_url);

        /*Metodo que se ejecuta cuando se selecciona un item del spinner*/
        spinnerSongs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //String itemSelected = spinnerSongs.getSelectedItem().toString();
                /*Se setean valores dependiendo del item selccionado en el spinner*/
                mSong = DataManager.getInstance().getSong(adapterView.getItemAtPosition(i).toString());
                textSongAuthor.setText(mSong.getAuthor());
                textSongComposer.setText(mSong.getComposer());
                textSongGender.setText(mSong.getGender());
                textSongDate.setText(mSong.getDate());;
                textSongUrl.setText(mSong.getUrl());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        /*Carga los datos en los elementos de la vista*/
        //displaySong(spinnerAlbums,textSongName,textSongAuthor,textSongComposer,textSongGender,textSongDate);

        /*Metodo que se ejecuta cuando se presiona el boton para ver video*/
        button_video = findViewById(R.id.button_view_video);
        button_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                if (mSong.getUrl() != ""){
                    intent.setData(Uri.parse(mSong.getUrl()));
                    startActivity(intent);
                }
            }
        });
    }

    //private void displaySong(Spinner spinnerAlbums, EditText textSongName, EditText textSongAuthor, EditText textSongComposer,EditText textSongGender, EditText textSongDate) {
        //List<AlbumInfo> albums = DataManager.getInstance().getAlbums();
        /*Devuelve la posicion de donde se encuentra el album de la cancion seleccionada en la lista*/
        //int albumIndex = albums.indexOf(mAlbum.getAlbum());
        /*Indica cual album selecciona*/
        //spinnerAlbums.setSelection(albumIndex);

        /*Se setean los datos en los elementos de la vista*/
        /*textSongName.setText(mAlbum.getName());
        textSongAuthor.setText(mAlbum.getAuthor());
        textSongComposer.setText(mAlbum.getComposer());
        textSongGender.setText(mAlbum.getGender());
        textSongDate.setText(mAlbum.getDate());
        url = mAlbum.getUrl();*/
    //}

    private void readDisplayStateValues() {
        Intent intent = getIntent();
        /*Se obtiene el objeto encapsulado por objeto*/
        //mSong = intent.getParcelableExtra(SONG_POSITION);

        /*Se obtiene el objeto encapsulado por posicion*/
        int position = intent.getIntExtra(ALBUM_POSITION, POSITION_NOT_SET);
        mAlbum = DataManager.getInstance().getAlbums().get(position);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_music, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            if(mSong != null){
                /*Se guardan los datos modificados*/
                mSong.setAuthor(textSongAuthor.getText().toString());
                mSong.setComposer(textSongComposer.getText().toString());
                mSong.setGender(textSongGender.getText().toString());
                mSong.setDate(textSongDate.getText().toString());
                mSong.setUrl(textSongUrl.getText().toString());

                textUrl.setText("Reproducir:");
                textSongUrl.setVisibility(View.INVISIBLE);
                button_video.setVisibility(View.VISIBLE);

                textSongAuthor.setEnabled(false);
                textSongComposer.setEnabled(false);
                textSongGender.setEnabled(false);
                textSongDate.setEnabled(false);
            }
            //return true;
        }else if(id == R.id.action_edit){

            textSongAuthor.setEnabled(true);
            textSongComposer.setEnabled(true);
            textSongGender.setEnabled(true);
            textSongDate.setEnabled(true);

            textUrl.setText("Enlace:");
            textSongUrl.setVisibility(View.VISIBLE);
            button_video.setVisibility(View.INVISIBLE);

        }

        return super.onOptionsItemSelected(item);
    }
}