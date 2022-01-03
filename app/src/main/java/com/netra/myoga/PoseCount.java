package com.netra.myoga;

import android.content.pm.ActivityInfo;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


//make youtube base acticity as interface
public class PoseCount extends YouTubeBaseActivity {
    Button finPose;
    TextView poseName;
    TextView countdown;
    String exerciseName;
    String exerciseUrl;
    int duration;
    boolean isPressed = false;

    private YouTubePlayerView posePlayer;
    String api_key = "AIzaSyC6I5inDAdAlH9j2_OOER2cmCGAew79XN4";

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //getSupportActionBar().hide();
        setContentView(R.layout.posingscreen);


        finPose = (Button) findViewById(R.id.doneButton);
        poseName = (TextView) findViewById(R.id.workoutType);
        //yogaGif = (YouTubePlayer) findViewById(R.id.poseGif);
        countdown = (TextView) findViewById(R.id.countDown);
        String startTime = "2";
        countdown.setText(startTime);

        posePlayer = (YouTubePlayerView) findViewById(R.id.posePlayer);

        exerciseName = getIntent().getStringExtra("ExName");
        exerciseUrl = getIntent().getStringExtra("ExUrl");
        duration = (getIntent().getIntExtra("Duration", 1) + 3) * 1000;
        poseName.setText(exerciseName);
        //yogaGif.loadVideo(exerciseUrl, 0);



        //button will be used to skip excercise
        finPose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TEST", "Skipping to next exercise");
                setResult(0);
                kill_activity();
            }
        });
    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Pose Counter", "Started");
        //yogaGif.play();

        posePlayer.initialize(
                api_key,
                new com.google.android.youtube.player.YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(com.google.android.youtube.player.YouTubePlayer.Provider provider, com.google.android.youtube.player.YouTubePlayer youTubePlayer, boolean b) {
                        youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
                        youTubePlayer.loadVideo(exerciseUrl); //kUgA8VCD48E
                        youTubePlayer.play();
                    }

                    @Override
                    public void onInitializationFailure(com.google.android.youtube.player.YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                        Toast.makeText(getApplicationContext(), "Video player Failed", Toast.LENGTH_SHORT).show();

                    }
                }
        );

        new CountDownTimer(duration, 1000) {

            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;
                if(seconds > 59){
                    countdown.setText((seconds/60) + ":" + String.format("%02d",(seconds % 60)));
                }
                else{
                    countdown.setText("" + (millisUntilFinished / 1000));
                }

            }

            public void onFinish() {
                countdown.setText("0");
                Log.i("Pose Counter:", "Ending Current Pose " + exerciseName);
                setResult(1);
                kill_activity();
            }
        }.start();
    }

    void kill_activity()
    {
        //yogaGif.pause();
        finish();
    }

    @Override
    public void onBackPressed() {

        if (isPressed){

            /*finishAffinity();
            System.exit(0);*/
            Log.i("TEST", "Back button clicked");
            Intent intent = new Intent(PoseCount.this, DashboardActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(),"Please click back again to exit",Toast.LENGTH_SHORT).show();
            isPressed = true;
        }

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                isPressed = false;
            }
        };

        new Handler().postDelayed(runnable,2000);
    }
}
