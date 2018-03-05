package br.com.juventudesociedade.criative.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.juventudesociedade.criative.R;

public class TccFragment extends Fragment {

    public TccFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate ( R.layout.fragment_tcc, container, false );

        FloatingActionButton fab = (FloatingActionButton) view.findViewById ( R.id.fab_tcc );

        fab.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                enviarEmail();
            }
        } );


        return view;
    }

    public void enviarEmail (){

        Intent email = new Intent (Intent.ACTION_SEND);
        email.putExtra ( Intent.EXTRA_EMAIL, new String[]{"juventudesesociedade@gmail.com", "amnomfiel@gmail.com"} );
        email.putExtra ( Intent.EXTRA_SUBJECT, "TCC - CriAtive" );
        email.putExtra ( Intent.EXTRA_TEXT, "");

        //Configurar app de email

        email.setType ( "message/rfc822" );
        startActivity ( Intent.createChooser ( email, "Escolha o aplicativo de e-mail:" ) );

    }
}
