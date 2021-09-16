package com.amora.catalogodemusica;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MusicRecyclerAdapter extends RecyclerView.Adapter<MusicRecyclerAdapter.ViewHolder> {

    private final Context mContext;
    private final List<AlbumInfo> mAlbum;
    private final LayoutInflater mLayoutInflater;

    public MusicRecyclerAdapter(Context context,List<AlbumInfo> album) {
        mContext = context;
        mAlbum = album;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_music_list, parent, false);
        return new ViewHolder(itemView);
    }

    /*Carga los datos de los objetos a los elementos de la vista*/
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        AlbumInfo album = mAlbum.get(position);
        holder.mTextAlbum.setText(album.getName()+" ("+album.getYear()+")");
        holder.mImageAlbum.setImageResource(mContext.getResources().getIdentifier(album.getImage(),"drawable",mContext.getPackageName()));
        holder.mTextDescription.setText("Album musical de "+album.getName());
        holder.mCurrentPosition = position;
    }

    @Override
    public int getItemCount() {
        return mAlbum.size();
    }

    /*Identifica los elementos de la vista*/
    public class ViewHolder extends RecyclerView.ViewHolder{

        public final TextView mTextAlbum;
        public final TextView mTextDescription;
        public final ImageView mImageAlbum;
        public int mCurrentPosition;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextAlbum = (TextView) itemView.findViewById(R.id.text_album_title);
            mImageAlbum = (ImageView) itemView.findViewById(R.id.image_album);
            mTextDescription = (TextView) itemView.findViewById(R.id.text_album_description);

            /*Metodo que se ejecuta al presionar un item de la lista*/
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*Intent que envia hacia musicActivity el cual recibe el parametro*/
                    Intent intent = new Intent(mContext, MusicActivity.class);
                    /*Guarda o encapsula el objeto que ese enviara al otro activity*/
                    intent.putExtra(MusicActivity.ALBUM_POSITION, mCurrentPosition);
                    mContext.startActivity(intent);
                }
            });
        }
    }


}
