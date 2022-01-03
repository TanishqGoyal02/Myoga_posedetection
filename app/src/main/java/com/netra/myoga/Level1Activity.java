package com.netra.myoga;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.TimerTask;

public class Level1Activity extends AppCompatActivity {

    TextView userName, email, percent, numWorkouts, hoursSpent, weekNumber, date_textView;
    ProgressBar progress;
    ImageView userImage;
    int week;
    Button level1_button;
    Button day1B, day2B, day3B, day4B, day5B, day6B, day7B;
    ImageView previewPose;
    DBConnection connection = new DBConnection(this);
    String userEmail;

    double[] currentProgress = new double[10];

    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions options;
    private GoogleSignInAccount account;



    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.level1_homepage);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

       /* GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            String personEmail = account.getEmail();
            userEmail = personEmail;
        }*/

        Cursor res = connection.fetchAllData();
        if(res.getCount() == 0)
        {

        }
        while (res.moveToNext())
        {
            String email = res.getString(3);
            userEmail = email;
        }

        //userName = (TextView) findViewById(R.id.userName);
        //email = (TextView) findViewById(R.id.Email);
        //userImage = (ImageView) findViewById(R.id.userImage);
        percent = (TextView) findViewById(R.id.percentComplete);
        numWorkouts = (TextView) findViewById(R.id.workoutsCompleted);
        hoursSpent = (TextView) findViewById(R.id.hoursSpent);
        day1B = (Button) findViewById(R.id.day1);
        day2B = (Button) findViewById(R.id.day2);
        day3B = (Button) findViewById(R.id.day3);
        day4B = (Button) findViewById(R.id.day4);
        day5B = (Button) findViewById(R.id.day5);
        day6B = (Button) findViewById(R.id.day6);
        day7B = (Button) findViewById(R.id.day7);
        progress = (ProgressBar) findViewById(R.id.progressBar);
        weekNumber = (TextView) findViewById(R.id.weekNumber);
        previewPose = (ImageView) findViewById(R.id.previewImage);
        date_textView = (TextView) findViewById(R.id.date_textView);


        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, MMM dd");
        String dateTime = simpleDateFormat.format(calendar.getTime());
        date_textView.setText(dateTime);


        week = getIntent().getIntExtra("Week", 1);
        currentProgress = connection.getWeeklyData(week, userEmail);
        Log.i("DEBUG LVL1", Arrays.toString(currentProgress));

        weekNumber.setText("Level " + week);
        double temp = getWeeklyProgress(currentProgress);
        /*hoursSpent.setText(String.format("%.2f",(temp * 2)));*/
        hoursSpent.setText(String.format("%d",(int)(Math.round(temp * 2 * 60))));
        numWorkouts.setText(String.format("%d",(int)(temp * 70)));
        progress.setProgress((int) (temp*100));
        String percentStr = "" + (int)(temp*100) + "%";
        percent.setText(percentStr);
        setPoseImage(week);

        for(int i = 0; i < 7; i++) {
            if(currentProgress[i] > 0.0) {
                buttonSet(i+1);
            }
        }

        day1B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("TEST", "Level 1 button clicked");
                Intent intent = new Intent(Level1Activity.this, WeeklyActivity.class);
                intent.putExtra("Account", account);
                intent.putExtra("Week", week);
                intent.putExtra("Day", 1);
                intent.putExtra("WeeklyProgress", currentProgress);
                startActivity(intent);
            }
        });

        day2B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("TEST", "Level 1 button clicked");
                Intent intent = new Intent(Level1Activity.this, WeeklyActivity.class);
                intent.putExtra("Account", account);
                intent.putExtra("Week", week);
                intent.putExtra("Day", 2);
                intent.putExtra("WeeklyProgress", currentProgress);
                startActivity(intent);
            }
        });

        day3B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("TEST", "Level 1 button clicked");
                Intent intent = new Intent(Level1Activity.this, WeeklyActivity.class);
                intent.putExtra("Account", account);
                intent.putExtra("Week", week);
                intent.putExtra("Day", 3);
                intent.putExtra("WeeklyProgress", currentProgress);
                startActivity(intent);
            }
        });

        day4B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("TEST", "Level 1 button clicked");
                Intent intent = new Intent(Level1Activity.this, WeeklyActivity.class);
                intent.putExtra("Account", account);
                intent.putExtra("Week", week);
                intent.putExtra("Day", 4);
                intent.putExtra("WeeklyProgress", currentProgress);
                startActivity(intent);
            }
        });

        day5B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("TEST", "Level 1 button clicked");
                Intent intent = new Intent(Level1Activity.this, WeeklyActivity.class);
                intent.putExtra("Account", account);
                intent.putExtra("Week", week);
                intent.putExtra("Day", 5);
                intent.putExtra("WeeklyProgress", currentProgress);
                startActivity(intent);
            }
        });

        day6B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("TEST", "Level 1 button clicked");
                Intent intent = new Intent(Level1Activity.this, WeeklyActivity.class);
                intent.putExtra("Account", account);
                intent.putExtra("Week", week);
                intent.putExtra("Day", 6);
                intent.putExtra("WeeklyProgress", currentProgress);
                startActivity(intent);
            }
        });

        day7B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("TEST", "Level 1 button clicked");
                Intent intent = new Intent(Level1Activity.this, WeeklyActivity.class);
                intent.putExtra("Account", account);
                intent.putExtra("Week", week);
                intent.putExtra("Day", 7);
                intent.putExtra("WeeklyProgress", currentProgress);
                startActivity(intent);
            }
        });
    }

        int counter = 0;
        @Override
        public void onBackPressed() {

            Log.i("TEST", "Back button clicked");
            Intent intent = new Intent(Level1Activity.this, DashboardActivity.class);
            startActivity(intent);
        }

        public void setPoseImage(int week) {
            switch (week) {
                case 1:
                    previewPose.setImageResource(R.drawable.dashboard_image2);
                    return;
                case 2:
                    previewPose.setImageResource(R.drawable.dashboard_image3);
                    return;
                case 3:
                    previewPose.setImageResource(R.drawable.dashboard_image4);
                    return;
                case 4:
                    previewPose.setImageResource(R.drawable.dashboard_image5);
                    return;
                default:
                    previewPose.setImageResource(R.drawable.dashboard_image2);
                    return;

            }
        }

        public double getWeeklyProgress(double[] weekData) {
            double total = 0.0;
            for (int i = 0; i < 7; i++) {
                total += weekData[i];
             }
            return total/7.0;
        }

        public void buttonSet(int n) {
            switch (n) {
                case 1:
                    day1B.setBackgroundColor(getResources().getColor(R.color.lightbrown));
                    break;
                case 2:
                    day2B.setBackgroundColor(getResources().getColor(R.color.lightbrown));
                    break;
                case 3:
                    day3B.setBackgroundColor(getResources().getColor(R.color.lightbrown));
                    break;
                case 4:
                    day4B.setBackgroundColor(getResources().getColor(R.color.lightbrown));
                    break;
                case 5:
                    day5B.setBackgroundColor(getResources().getColor(R.color.lightbrown));
                    break;
                case 6:
                    day6B.setBackgroundColor(getResources().getColor(R.color.lightbrown));
                    break;
                case 7:
                    day7B.setBackgroundColor(getResources().getColor(R.color.lightbrown));
                    break;
                default:
            }
        }

    }


