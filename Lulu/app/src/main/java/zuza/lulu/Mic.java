package zuza.lulu;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;

public class Mic extends AppCompatActivity {


    Boolean mStartPlaying = true;
    Boolean mStartRecording = true;
    private static final String LOG_TAG = "AudioRecordTest";
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private static String mFileName = null;

  //  private RecordButton mRecordButton = null;
    private MediaRecorder mRecorder = null;

 //   private PlayButton   mPlayButton = null;
    private MediaPlayer   mPlayer = null;

    // Requesting permission to RECORD_AUDIO
    private boolean permissionToRecordAccepted = false;
    private String [] permissions = {Manifest.permission.RECORD_AUDIO};

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted ) finish();

    }
    private void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    private void onPlay(boolean start) {
        if (start) {
            startPlaying();
        } else {
            stopPlaying();
        }
    }

    private void startPlaying() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    private void stopPlaying() {
        mPlayer.release();
        mPlayer = null;
    }

    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }
    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }

        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }
    private  MyStringRequest myrqst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mic);


        // Record to the external cache directory for visibility
        mFileName = getExternalCacheDir().getAbsolutePath();
        mFileName += "/audiorecordtest.3gp";
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/myrecording.mp3";

        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);


        Button mRecordButton = (Button) findViewById(R.id.buttonRecord);
        Button mPlayButton = (Button )findViewById(R.id.buttonPlay);

        mRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onRecord(mStartRecording);
                if (mStartRecording) {
                    Toast.makeText(Mic.this, "Start recording", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Mic.this, "Stop recording", Toast.LENGTH_SHORT).show();
                }
                mStartRecording = !mStartRecording;
            }
        });
        mPlayButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                onPlay(mStartPlaying);
                if (mStartPlaying) {
                    Toast.makeText(Mic.this, "Start playing", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Mic.this, "Stop playing", Toast.LENGTH_SHORT).show();
                }
                mStartPlaying = !mStartPlaying;
            }
        });

        //myrqst = new MyStringRequest();
        Button button_mic_talk = (Button) findViewById(R.id.button_talk);
        button_mic_talk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO : on click mic talking

            }
        });

        Button button_home = (Button) findViewById(R.id.button_home);
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu.class);
                startActivity(intent);
            }
        });
    }
}



