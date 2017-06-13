package zuza.lulu;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class Camera extends AppCompatActivity {


    private String src = "http://172.16.73.161:8081/";

    VideoView streamView;
    MediaController mediaController;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

       // streamView = (VideoView)findViewById(R.id.streamView);



        webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl(src);
        Button button_home = (Button) findViewById(R.id.button_home);


        button_home.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO : on click home

                Intent i = new Intent(getApplicationContext(), Menu.class);
                startActivity(i);
            }
        });
       // playStream(src);

    }

    private void playStream(String src) {
        Uri UriSrc = Uri.parse(src);
        if (UriSrc == null) {
            Toast.makeText(Camera.this,
                    "UriSrc == null", Toast.LENGTH_LONG).show();
        } else {
            streamView.setVideoURI(UriSrc);
            mediaController = new MediaController(this);
            streamView.setMediaController(mediaController);
            streamView.start();

            Toast.makeText(Camera.this,
                    "Connect: " + src,
                    Toast.LENGTH_LONG).show();
        }

    }

        protected void onDestroy() {
        super.onDestroy();
      //  streamView.stopPlayback();
        }

}
