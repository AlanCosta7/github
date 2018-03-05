package br.com.juventudesociedade.criative.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.com.juventudesociedade.criative.DAO.ConfiguracaoFirebase;
import br.com.juventudesociedade.criative.Entidades.Usuarios;
import br.com.juventudesociedade.criative.Helper.Preferencias;
import br.com.juventudesociedade.criative.R;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao;
    private EditText edtEmailLogin;
    private EditText edtSenhaLogin;
    private Button btnLogin;
    private Usuarios usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        edtEmailLogin = (EditText) findViewById(R.id.edtEmail);
        edtSenhaLogin = (EditText) findViewById(R.id.edtSenha);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        permissoes();

        if (usuarioLogado()) {

            Intent intentMinhaConta = new Intent(LoginActivity.this, MainActivity.class);
            abrirNovaActivity(intentMinhaConta);
            finish();

        } else {

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (!edtEmailLogin.getText().toString().equals("") && !edtSenhaLogin.getText().toString().equals("")) {

                        usuarios = new Usuarios();

                        usuarios.setEmail(edtEmailLogin.getText().toString());
                        usuarios.setSenha(edtSenhaLogin.getText().toString());

                        validarLogin();


                    } else {
                        Toast.makeText(LoginActivity.this, "Preencha os campos de E-mail e senha", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void validarLogin() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAuth();
        autenticacao.signInWithEmailAndPassword( usuarios.getEmail (), usuarios.getSenha () ).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    abrirTelaPrincipal();

                    Preferencias preferencias = new Preferencias(LoginActivity.this);
                    preferencias.salvarUsuarioPreferencias(usuarios.getEmail(), usuarios.getSenha());
                    Toast.makeText(LoginActivity.this, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(LoginActivity.this, "Usuário ou senha inválidos! Tente novamente!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void abrirTelaPrincipal() {

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    public Boolean usuarioLogado() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    public void abrirNovaActivity(Intent intent) {
        startActivity(intent);
    }

    public void permissoes() {
        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = new String[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            PERMISSIONS = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.INTERNET};
        }
        ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
    }

}