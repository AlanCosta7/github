package br.com.juventudesociedade.criative.Helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by Daniel Lopes on 14/10/2017.
 */

public class Preferencias {

    private Context context;
    private SharedPreferences preferences;
    private String NOME_ARQUIVO = "app.preferencias";
    private int MODE = 0;
    private SharedPreferences.Editor editor;


    private final String EMAIL_USUARIO_LOGADO = "email_usuario_logado";
    private final String SENHA_USUARIO_LOGADO = "senha_usuario_logado";

    public Preferencias(Context contextoParametro) {
        context = contextoParametro;



        preferences = context.getSharedPreferences(NOME_ARQUIVO, MODE);

        //associar o nosso preferencees.edit()
        editor = preferences.edit();
    }


    public void salvarUsuarioPreferencias(String email, String senha) {

        //salvar dentro do nosso arquivo de preferencias o email e senha do usuario

        editor.putString(EMAIL_USUARIO_LOGADO, email);
        editor.putString(SENHA_USUARIO_LOGADO, senha);
        editor.commit();

    }

    public void escreverChave (String key, String value) {
        SharedPreferences.Editor editor = preferences.edit ();
        editor.putString ( key, value );
        editor.commit ();
    }

    public String lerChave (String key){
        return preferences.getString ( key, "" );
    }

    public boolean validarChave (String key){
        return preferences.contains ( key );
    }

    public HashMap<String, String> lerTodasAsChaves (String[] keys){
        HashMap<String, String> map = new HashMap <> (  );
        for (String chave : keys) {
            map.put ( chave, lerChave ( chave ) );

        }
        return map;
    }

    public void escreverTodasAsChaves (HashMap<String, String> map){
        for (Map.Entry<String, String> entry : map.entrySet ()){
            escreverChave ( entry.getKey (), entry.getValue () );
        }

    }

    public boolean validarTodasAsChaves (String[] keys){
        HashSet<Boolean> set = new HashSet <> (  );
        for (String chave : keys){
            if (validarChave ( chave )){
                set.add(true);
            }else {
                set.add ( false );
            }
        }
        return (set.size () == 1 && set.contains ( true ));
    }

    public String getEmailUsuarioLogado() {
        return preferences.getString(EMAIL_USUARIO_LOGADO, null);
    }

    public String getSenhaUsuarioLogado() {

        return preferences.getString(SENHA_USUARIO_LOGADO, null);
    }


}
