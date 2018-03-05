package br.com.juventudesociedade.criative.activity;


import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.juventudesociedade.criative.DAO.ConfiguracaoFirebase;
import br.com.juventudesociedade.criative.Entidades.Usuarios;
import br.com.juventudesociedade.criative.Helper.Preferencias;
import br.com.juventudesociedade.criative.R;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private EditText email;
    private EditText senha1;
    private EditText senha2;
    private EditText nome;
    private RadioButton rbAdmin;
    private RadioButton rbAtend;
    private Button btnCadastrar;
    private Button btnCancelar;
    private FirebaseAuth autenticacao;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private Usuarios usuarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_cadastro_usuario);

        setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        email = (EditText) findViewById(R.id.edtCadEmail);
        senha1 = (EditText) findViewById(R.id.edtCadSenha1);
        senha2 = (EditText) findViewById(R.id.edtCadSenha2);
        nome = (EditText) findViewById(R.id.edtCadNome);
        //rbAdmin = (RadioButton) findViewById(R.id.rbAdmin);
        //rbAtend = (RadioButton) findViewById(R.id.rbAtend);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);


        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (senha1.getText().toString().equals(senha2.getText().toString())) {
                    usuarios = new Usuarios ();

                    usuarios.setEmail(email.getText().toString());
                    usuarios.setSenha(senha1.getText().toString());
                    usuarios.setNome(nome.getText().toString());

                    if (rbAdmin.isChecked()) {
                        usuarios.setTipoUsuario("Administrador(a)");
                    } else if (rbAtend.isChecked()) {
                        usuarios.setTipoUsuario("Aluno(a)");
                    }

                    //chamada de método para cadastro de usuários
                    cadastrarUsuario();

                } else {
                    Toast.makeText(CadastroUsuarioActivity.this, "As senhas não se correspondem!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void cadastrarUsuario() {

        autenticacao = ConfiguracaoFirebase.getFirebaseAuth();
        autenticacao.createUserWithEmailAndPassword(
                usuarios.getEmail(),
                usuarios.getSenha()
        ).addOnCompleteListener(CadastroUsuarioActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    insereUsuario(usuarios);

                    finish();

                    //deslogar ao adicionar o usuário
                    autenticacao.signOut();

                    //para abrir a nossa tela principal após a re-autenticação
                    abreTelaPrincipal();


                } else {

                    String erroExcecao = "";

                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        erroExcecao = "Digite uma senha mais forte, contendo no mínimo 8 caracteres e que contenha letras e números!";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        erroExcecao = "O e-mail digitado é invalido, digite um novo e-mail";
                    } catch (FirebaseAuthUserCollisionException e) {
                        erroExcecao = "Esse e-mail já está cadastro!";
                    } catch (Exception e) {
                        erroExcecao = "Erro ao efetuar o cadastro!";
                        e.printStackTrace();
                    }

                    Toast.makeText(CadastroUsuarioActivity.this, "Erro: " + erroExcecao, Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private boolean insereUsuario(Usuarios usuario) {

        try {

            reference = ConfiguracaoFirebase.getFirebase().child("usuarios");
            reference.push().setValue(usuario);
            Toast.makeText(CadastroUsuarioActivity.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();
            return true;

        } catch (Exception e) {
            Toast.makeText(CadastroUsuarioActivity.this, "Erro ao gravar o usuário!", Toast.LENGTH_LONG).show();
            e.printStackTrace();
            return false;
        }
    }

    private void abreTelaPrincipal() {

        autenticacao = ConfiguracaoFirebase.getFirebaseAuth();

        Preferencias preferencias = new Preferencias(CadastroUsuarioActivity.this);

        autenticacao.signInWithEmailAndPassword(preferencias.getEmailUsuarioLogado(), preferencias.getSenhaUsuarioLogado()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Intent intent = new Intent(CadastroUsuarioActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {

                    Toast.makeText(CadastroUsuarioActivity.this, "Falha!", Toast.LENGTH_LONG).show();
                    autenticacao.signOut();
                    Intent intent = new Intent(CadastroUsuarioActivity.this, MainActivity.class);
                    finish();
                    startActivity(intent);
                }

            }
        });

    }

}
