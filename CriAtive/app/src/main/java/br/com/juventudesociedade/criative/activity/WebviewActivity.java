package br.com.juventudesociedade.criative.activity;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import br.com.juventudesociedade.criative.R;

public class WebviewActivity extends AppCompatActivity {

   public WebView webView;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        //setContentView ( R.layout.activity_webview );

        Bundle dados = getIntent ().getExtras ();
        assert dados != null;
        String arquivo_pdf = dados.getString ( "textoCurso" );

        try
        {
            Intent intentUrl = new Intent(Intent.ACTION_VIEW);
            intentUrl.setDataAndType( Uri.parse ( arquivo_pdf ), "application/pdf");
            intentUrl.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intentUrl);
        }
        catch (ActivityNotFoundException e)
        {
            Toast.makeText(this, "Instale um visualizador de PDF", Toast.LENGTH_LONG).show();
        }

    }
}
