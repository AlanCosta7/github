package br.com.juventudesociedade.criative.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.juventudesociedade.criative.Entidades.Texto;
import br.com.juventudesociedade.criative.R;
import br.com.juventudesociedade.criative.adapter.TextoAdapter;


public class TextoFragment extends Fragment {

    public TextoFragment() {
    }

    DatabaseReference databaseReference;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<Texto> textos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate ( R.layout.fragment_texto, container, false );

        databaseReference = FirebaseDatabase.getInstance ().getReference ( "Texto" );

        recyclerView = (RecyclerView) view.findViewById ( R.id.recycler_viewtxt );
        recyclerView.setHasFixedSize ( true );
        recyclerView.setLayoutManager ( new LinearLayoutManager ( getContext (), LinearLayoutManager.VERTICAL, false  ) );
        textos = new ArrayList<> ();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart ();

        databaseReference.addValueEventListener ( new ValueEventListener () {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                textos.clear ();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren ()) {
                    Texto texto = dataSnapshot1.getValue ( Texto.class );

                    textos.add ( texto );
                }

                TextoAdapter textoAdapter = new TextoAdapter ( textos, getContext () );
                recyclerView.setAdapter ( textoAdapter );
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        } );
    }

}