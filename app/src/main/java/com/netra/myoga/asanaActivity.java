package com.netra.myoga;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class asanaActivity extends YouTubeBaseActivity {

    private YouTubePlayerView ytPlayer;
    String api_key = "AIzaSyC6I5inDAdAlH9j2_OOER2cmCGAew79XN4";

    protected void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_asana);
        Log.i("Asana Activity:", "Activity Started");

        /** GIF VIEW : this is working but replacing  with youtube
         * video for Testing User Progress
         */

        // GifView asana = (GifView) findViewById(R.id.asana_steps);

      //  ProgressBar asana_completion = (ProgressBar) findViewById(R.id.asanaCompletion);

//        ProgressBar user_completion = (ProgressBar) findViewById(R.id.circular_progress);

     /*   asana_completion.setMax(100);
        asana_completion.setProgress(25);
        asana.setVisibility(View.VISIBLE);
        asana.setGifResource(R.drawable.asana);


        asana_completion.setProgress(50);
        Log.i("TIME:", String.valueOf(asan a.getDrawingTime()));*/



        ytPlayer = (YouTubePlayerView) findViewById(R.id.ytPlayer);
        ytPlayer.initialize(
                api_key,
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                        youTubePlayer.loadVideo("RJ44oIxWiYI");
                        youTubePlayer.play();
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                        Toast.makeText(getApplicationContext(), "Video player Failed", Toast.LENGTH_SHORT).show();

                    }
                }
        );





    }
}
