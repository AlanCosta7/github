package br.com.juventudesociedade.criative.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapCircleThumbnail;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import br.com.juventudesociedade.criative.DAO.ConfiguracaoFirebase;
import br.com.juventudesociedade.criative.Entidades.Usuarios;
import br.com.juventudesociedade.criative.R;
import br.com.juventudesociedade.criative.activity.UploadFotoActivity;


public class DashboardFragment extends Fragment {

    private FirebaseAuth autenticacao;
    private FirebaseDatabase database;
    private StorageReference storageReference;
    private TextView tipoUsuario;
    private TextView nome;
    private Usuarios usuarios;
    private BootstrapCircleThumbnail imageView;
    private String emailUsuarioLogado;
    private DatabaseReference referenciaFirebase;
    private String tipoUsuarioEmail;
    private String nomeEmail;


    public DashboardFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate ( R.layout.fragment_dashboard, container, false );

        autenticacao = FirebaseAuth.getInstance ();
        storageReference = ConfiguracaoFirebase.getFirebaseStorageReference ();
        emailUsuarioLogado = autenticacao.getCurrentUser ().getEmail ();
        referenciaFirebase = FirebaseDatabase.getInstance ().getReference ();

        tipoUsuario = (TextView) view.findViewById ( R.id.txtTipoUsuario );
        nome = (TextView) view.findViewById ( R.id.id_nomeAluno );
        imageView = (BootstrapCircleThumbnail) view.findViewById ( R.id.id_fotoPerfil );


        imageView.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                uploadFotoPerfil();
            }
        } );

        carregaImagemPadrao ();
        dadosUsuario();
        return view;
    }

    private void carregaImagemPadrao() {

        FirebaseStorage storage = FirebaseStorage.getInstance ();
        final StorageReference storageReference = storage.getReferenceFromUrl ( "gs://criative-2018-js.appspot.com/fotoPerfilUsuario/" + emailUsuarioLogado + ".jpg" );

        final int heigth = 300;
        final int width = 300;

        storageReference.getDownloadUrl ().addOnSuccessListener ( new OnSuccessListener <Uri> () {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with ( getContext () ).load ( uri.toString () ).resize ( width, heigth ).centerCrop ().into ( imageView );
            }
        } ).addOnFailureListener ( new OnFailureListener () {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        } );

    }

    private void uploadFotoPerfil() {
        Intent intent = new Intent(getContext (), UploadFotoActivity.class);
        startActivity(intent);
    }

    public void dadosUsuario() {

        String email = autenticacao.getCurrentUser ().getEmail ().toString ();


        referenciaFirebase.child ( "usuarios" ).orderByChild ( "email" ).equalTo ( email.toString () ).addValueEventListener ( new ValueEventListener () {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren ()) {
                    tipoUsuarioEmail = postSnapshot.child ( "tipoUsuario" ).getValue ().toString ();
                    nomeEmail = postSnapshot.child ( "nome" ).getValue ().toString ();

                    tipoUsuario.setText ( tipoUsuarioEmail );
                    nome.setText ( nomeEmail );

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        } );

    }
}

