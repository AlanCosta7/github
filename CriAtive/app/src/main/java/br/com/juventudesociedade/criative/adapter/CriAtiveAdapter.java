package br.com.juventudesociedade.criative.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.juventudesociedade.criative.Entidades.Category;
import br.com.juventudesociedade.criative.R;
import br.com.juventudesociedade.criative.activity.TelaCheiaActivity;
import br.com.juventudesociedade.criative.activity.WebviewActivity;


/**
 * Created by alanp on 07/02/2018.
 */

public class CriAtiveAdapter extends RecyclerView.Adapter<CriAtiveAdapter.MyViewHolder> {
    private List<Category> categories;
    private Context context;
    private LayoutInflater inflater;
    private StorageReference mStorageRef;

    public CriAtiveAdapter(List <Category> categories, final Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.categories = categories;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView autorCurso;
        public TextView descricaoCurso;
        public TextView tituloCurso;
        public Button textoCurso;
        public ImageView imageCurso;
        public ImageView videoUrl;

        public MyViewHolder(View itemView) {
            super(itemView);

            autorCurso = itemView.findViewById(R.id.id_autor);
            tituloCurso = itemView.findViewById(R.id.id_titulo);
            descricaoCurso = itemView.findViewById(R.id.id_descricao);
            textoCurso = itemView.findViewById ( R.id.id_textoCurso );
            imageCurso = itemView.findViewById ( R.id.id_imageView );
            videoUrl = (ImageView) itemView.findViewById ( R.id.btn_play );

        }
    }
    @Override
    public CriAtiveAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate( R.layout.adapter_lista, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder (final MyViewHolder holder, final int position)  {
        final Category category = categories.get ( position );

        holder.autorCurso.setText ( category.getAutorCurso () );
        holder.tituloCurso.setText ( category.getTituloCurso () );
        holder.descricaoCurso.setText ( category.getDescricaoCurso () );

        holder.textoCurso.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent ( context, WebviewActivity.class );
                intent.putExtra ( "textoCurso", category.getTextoCurso () );
                Toast.makeText ( context, "aguarde...", Toast.LENGTH_SHORT ).show ();
                context.startActivity(intent);
            }

        } );
        Picasso.with(context)
                .load(category.getImageCurso ())
                .into ( holder.imageCurso );

        holder.videoUrl.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent ( context, TelaCheiaActivity.class );
                intent.putExtra ( "videoCurso", category.getVideoUrl () );
                context.startActivity(intent);
            }
        } );
    }


    @Override
    public int getItemCount() {
        return categories.size();
    }


    @Override
    public long getItemId(int position) {

        return position;
    }

}
