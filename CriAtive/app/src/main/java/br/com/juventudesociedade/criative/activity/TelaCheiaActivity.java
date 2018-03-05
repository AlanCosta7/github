package br.com.juventudesociedade.criative.activity;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import br.com.juventudesociedade.criative.R;

public class TelaCheiaActivity extends AppCompatActivity {

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_tela_cheia );
        setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        videoView = findViewById ( R.id.videoView );

        try {
            Bundle dados = getIntent ().getExtras ();
            assert dados != null;
            String videoCurso = dados.getString ( "videoCurso" );

            final Uri videoUri = Uri.parse(videoCurso);
            videoView.setVideoURI(videoUri);
            videoView.setMediaController ( new MediaController ( this  ) );
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);
                    videoView.start ();
                }
            });
        } catch (Exception e){
            Toast.makeText(TelaCheiaActivity.this, "Erro inesperado", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }
}
