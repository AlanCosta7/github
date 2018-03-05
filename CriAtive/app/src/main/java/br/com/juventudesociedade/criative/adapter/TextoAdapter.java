package br.com.juventudesociedade.criative.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.storage.StorageReference;

import java.util.List;

import br.com.juventudesociedade.criative.Entidades.Texto;
import br.com.juventudesociedade.criative.R;
import br.com.juventudesociedade.criative.activity.WebviewActivity;

/**
 * Created by alanp on 22/02/2018.
 */

public class TextoAdapter extends RecyclerView.Adapter <TextoAdapter.MyViewHolder> {

    private List<Texto> textos;
    private Context context;
    private LayoutInflater inflater;
    private StorageReference mStorageRef;

    public TextoAdapter(List <Texto> textos, final Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.textos = textos;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView autortxt;
        public TextView descricaotxt;
        public TextView titulotxt;
        public Button textotxt;

        public MyViewHolder(View itemView) {
            super(itemView);

            autortxt = itemView.findViewById(R.id.id_autorText);
            titulotxt = itemView.findViewById(R.id.id_tituloText);
            descricaotxt = itemView.findViewById(R.id.id_descricaoText);
            textotxt = itemView.findViewById ( R.id.id_textopdfText);

        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate( R.layout.texto_layout, parent, false);

        return new TextoAdapter.MyViewHolder (itemLista);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final Texto texto = textos.get ( position );

        holder.autortxt.setText ( texto.getAutortxt () );
        holder.titulotxt.setText ( texto.getTitulotxt () );
        holder.descricaotxt.setText ( texto.getDescricaotxt () );
        holder.textotxt.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent ( context, WebviewActivity.class );
                intent.putExtra ( "textoCurso", texto.getTextotxt () );
                Toast.makeText ( context, "aguarde...", Toast.LENGTH_SHORT ).show ();
                context.startActivity(intent);

            }

        } );

    }



    @Override
    public int getItemCount() {
        return textos.size();
    }


    @Override
    public long getItemId(int position) {

        return position;
    }

}
