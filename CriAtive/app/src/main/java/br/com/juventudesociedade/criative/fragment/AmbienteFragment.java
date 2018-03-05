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

import br.com.juventudesociedade.criative.Entidades.Video;
import br.com.juventudesociedade.criative.R;
import br.com.juventudesociedade.criative.adapter.VideoAdapter;

public class AmbienteFragment extends Fragment {


    public AmbienteFragment() {
    }

    DatabaseReference databaseReference;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<Video> videos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate ( R.layout.fragment_ambiente, container, false );

        databaseReference = FirebaseDatabase.getInstance ().getReference ( "Video" );

        recyclerView = (RecyclerView) view.findViewById ( R.id.recycler_viewamb );
        recyclerView.setHasFixedSize ( true );
        recyclerView.setLayoutManager ( new LinearLayoutManager ( getContext (), LinearLayoutManager.HORIZONTAL, false  ) );
        videos = new ArrayList<> ();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart ();

        databaseReference.addValueEventListener ( new ValueEventListener () {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                videos.clear ();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren ()) {
                    Video video = dataSnapshot1.getValue ( Video.class );

                    videos.add ( video );
                }

                VideoAdapter videoAdapter = new VideoAdapter ( videos, getContext () );
                recyclerView.setAdapter ( videoAdapter );
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        } );
    }

}

