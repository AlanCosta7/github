package br.com.juventudesociedade.criative.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.juventudesociedade.criative.Entidades.Video;
import br.com.juventudesociedade.criative.R;
import br.com.juventudesociedade.criative.activity.TelaCheiaActivity;

/**
 * Created by alanp on 16/02/2018.
 */

public class VideoAdapter extends RecyclerView.Adapter <VideoAdapter.MyViewHolder> {

    private List<Video> videos;
    private Context context;
    private LayoutInflater inflater;
    private StorageReference mStorageRef;

    public VideoAdapter(List <Video> videos, final Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.videos = videos;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView autorVid;
        public TextView descricaoVid;
        public TextView tituloVid;
        public ImageView imageVideo;
        public ImageView btn_play;


        public MyViewHolder(View itemView) {
            super(itemView);

            autorVid = itemView.findViewById(R.id.id_autoramb);
            tituloVid = itemView.findViewById(R.id.id_tituloamb);
            descricaoVid = itemView.findViewById(R.id.id_descricaoamb);
            imageVideo = (ImageView) itemView.findViewById ( R.id.id_imageVideo );
            btn_play = (ImageView) itemView.findViewById ( R.id.btn_play );
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate( R.layout.ambiente_layout, parent, false);

        return new VideoAdapter.MyViewHolder (itemLista);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Video video = videos.get ( position );

        holder.autorVid.setText ( video.getAmb_autor () );
        holder.tituloVid.setText ( video.getAmb_titulo () );
        holder.descricaoVid.setText ( video.getAmb_descricao () );
        Picasso.with(context)
                .load(video.getImageVideo ())
                .into ( holder.imageVideo );

        holder.btn_play.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent ( context, TelaCheiaActivity.class );
                intent.putExtra ( "videoCurso", video.getAmb_videoUrl () );
                context.startActivity(intent);
            }
        } );

    }



    @Override
    public int getItemCount() {
        return videos.size();
    }


    @Override
    public long getItemId(int position) {

        return position;
    }

}
