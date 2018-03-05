package br.com.juventudesociedade.criative.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

import br.com.juventudesociedade.criative.R;

public class IntroducaoActivity extends IntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        //setContentView ( R.layout.activity_introducao );
        //setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setButtonBackVisible ( false );
        setButtonNextVisible ( false );

        addSlide ( new FragmentSlide.Builder ()
                .background ( android.R.color.holo_blue_dark )
                .fragment ( R.layout.intro_0 )
                .build () );
        addSlide ( new FragmentSlide.Builder ()
                .background ( android.R.color.holo_blue_bright )
                .fragment ( R.layout.intro_1 )
                .build () );
        addSlide ( new FragmentSlide.Builder ()
                .background ( android.R.color.holo_orange_dark )
                .fragment ( R.layout.intro_2 )
                .build () );
        addSlide ( new FragmentSlide.Builder ()
                .background ( android.R.color.holo_green_light )
                .fragment ( R.layout.intro_3 )
                .build () );
        addSlide ( new FragmentSlide.Builder ()
                .background ( android.R.color.white )
                .fragment ( R.layout.intro_cadastro )
                .build () );

    }

    public void abrirTelapagamento(View view) {
        Intent intent = new Intent(IntroducaoActivity.this, PagamentoActivity.class);

        startActivity(intent);
    }

    public void abrirTelaLogin(View view) {
        Intent intent = new Intent(IntroducaoActivity.this, LoginActivity.class);

        startActivity(intent);
    }

}
