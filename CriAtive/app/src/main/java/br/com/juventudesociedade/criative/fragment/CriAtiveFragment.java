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

import br.com.juventudesociedade.criative.Entidades.Category;
import br.com.juventudesociedade.criative.Entidades.Usuarios;
import br.com.juventudesociedade.criative.Helper.Preferencias;
import br.com.juventudesociedade.criative.R;
import br.com.juventudesociedade.criative.adapter.CriAtiveAdapter;

public class CriAtiveFragment extends Fragment {

    public CriAtiveFragment() {
    }

    DatabaseReference databaseReference;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private Preferencias preferencias;
    private Usuarios usuarios;
    private List <Category> categories;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate ( R.layout.fragment_criative, container, false );

        databaseReference = FirebaseDatabase.getInstance ().getReference ( "Category" );

        recyclerView = (RecyclerView) view.findViewById ( R.id.recycler_view );
        recyclerView.setHasFixedSize ( true );
        recyclerView.setLayoutManager ( new LinearLayoutManager ( getContext (), LinearLayoutManager.HORIZONTAL, false  ) );
        categories = new ArrayList <> ();



        return view;
    }

    @Override
    public void onStart() {
        super.onStart ();

        databaseReference.addValueEventListener ( new ValueEventListener () {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                categories.clear ();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren ()) {
                    Category category = dataSnapshot1.getValue ( Category.class );

                    categories.add ( category );
                }

                CriAtiveAdapter criAtiveAdapter = new CriAtiveAdapter ( categories, getContext () );
                recyclerView.setAdapter ( criAtiveAdapter );
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        } );

    }

}