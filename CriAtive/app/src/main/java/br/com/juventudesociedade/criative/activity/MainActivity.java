package br.com.juventudesociedade.criative.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.com.juventudesociedade.criative.Entidades.Usuarios;
import br.com.juventudesociedade.criative.R;
import br.com.juventudesociedade.criative.fragment.AmbienteFragment;
import br.com.juventudesociedade.criative.fragment.CartaFragment;
import br.com.juventudesociedade.criative.fragment.CriAtiveFragment;
import br.com.juventudesociedade.criative.fragment.DashboardFragment;
import br.com.juventudesociedade.criative.fragment.PrincipalFragment;
import br.com.juventudesociedade.criative.fragment.TccFragment;
import br.com.juventudesociedade.criative.fragment.TextoFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth autenticacao;
    private FirebaseDatabase database;
    private DatabaseReference referenciaFirebase;
    private TextView tipoUsuario;
    private Usuarios usuarios;
    private Menu menu1;
    private FrameLayout frameLayout;
    private ImageView imageView;
    private String emailUsuarioLogado;
    private String tipoUsuarioEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        autenticacao = FirebaseAuth.getInstance ();
        referenciaFirebase = FirebaseDatabase.getInstance ().getReference ();
        emailUsuarioLogado = autenticacao.getCurrentUser ().getEmail ();
        frameLayout = findViewById ( R.id.frameContainer );

        Toolbar toolbar = (Toolbar) findViewById ( R.id.toolbar );
        setSupportActionBar ( toolbar );

        DashboardFragment dashboardFragment = new DashboardFragment ();
        FragmentTransaction fragment = getSupportFragmentManager ().beginTransaction ();
        fragment.replace ( R.id.frameContainer, dashboardFragment );
        fragment.commit ();


        DrawerLayout drawer = (DrawerLayout) findViewById ( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle (
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener ( toggle );
        toggle.syncState ();

        NavigationView navigationView = (NavigationView) findViewById ( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener ( this );
        navigationView.setItemIconTintList ( null );
    }

    @Override
    public void onBackPressed() {
            DrawerLayout drawer = (DrawerLayout) findViewById ( R.id.drawer_layout );
            if (drawer.isDrawerOpen ( GravityCompat.START )) {
                drawer.closeDrawer ( GravityCompat.START );
                super.onBackPressed ();
            }
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        menu.clear();

        this.menu1 = menu;

        //recebendo o e-mail do usuário logado no momento
        String email = autenticacao.getCurrentUser ().getEmail ();


        referenciaFirebase.child("usuarios").orderByChild("email").equalTo( email ).addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    tipoUsuarioEmail = postSnapshot.child("tipoUsuario").getValue().toString();

                    menu1.clear();

                    if (tipoUsuarioEmail.equals("Administrador")) {
                        getMenuInflater().inflate(R.menu.menu_admin, menu1);
                    } /*else if (tipoUsuarioEmail.equals("Aluno")) {
                        getMenuInflater().inflate(R.menu.menu_atend, menu1);
                    } */
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return true;
    }


    private void abrirTelaCadastroUsuario() {
        Intent intent = new Intent(MainActivity.this, CadastroUsuarioActivity.class);

        startActivity(intent);
    }

    private void deslogarUsuario() {

        autenticacao.signOut();

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }

    private void uploadFotoPerfil() {
        Intent intent = new Intent(MainActivity.this, UploadFotoActivity.class);
        finish();
        startActivity(intent);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId ();

        if (id == R.id.nav_dashboard) {

            DashboardFragment dashboardFragment = new DashboardFragment ();
            FragmentTransaction fragment = getSupportFragmentManager ().beginTransaction ();
            fragment.replace (R.id.frameContainer, dashboardFragment );
            fragment.commit ();

        } else if (id == R.id.nav_principal) {

            PrincipalFragment principalFragment = new PrincipalFragment ();
            FragmentTransaction fragment = getSupportFragmentManager ().beginTransaction ();
            fragment.replace (R.id.frameContainer, principalFragment );
            fragment.commit ();

        } else if (id == R.id.nav_criative) {

            CriAtiveFragment criAtiveFragment = new CriAtiveFragment ();
            FragmentTransaction fragment = getSupportFragmentManager ().beginTransaction ();
            fragment.replace (R.id.frameContainer, criAtiveFragment );
            fragment.commit ();

        } else if (id == R.id.nav_ambVideos) {
            AmbienteFragment ambienteFragment = new AmbienteFragment ();
            FragmentTransaction fragment = getSupportFragmentManager ().beginTransaction ();
            fragment.replace (R.id.frameContainer, ambienteFragment );
            fragment.commit ();

        } else if (id == R.id.nav_ambTextos) {
            TextoFragment textoFragment = new TextoFragment ();
            FragmentTransaction fragment = getSupportFragmentManager ().beginTransaction ();
            fragment.replace (R.id.frameContainer, textoFragment );
            fragment.commit ();

        } else if (id == R.id.nav_carta) {

            CartaFragment cartaFragment = new CartaFragment ();
            FragmentTransaction fragment = getSupportFragmentManager ().beginTransaction ();
            fragment.replace (R.id.frameContainer, cartaFragment );
            fragment.commit ();

        } else if (id == R.id.nav_tcc) {

            TccFragment tccFragment = new TccFragment ();
            FragmentTransaction fragment = getSupportFragmentManager ().beginTransaction ();
            fragment.replace (R.id.frameContainer, tccFragment );
            fragment.commit ();

        } else if (id == R.id.nav_sair) {
            deslogarUsuario();
      /*  } else if (id == R.id.nav_compartilhar) {

        } else if (id == R.id.nav_depoimento) { */

        }

        DrawerLayout drawer = (DrawerLayout) findViewById ( R.id.drawer_layout );
        drawer.closeDrawer ( GravityCompat.START );
        return true;
    }

}


