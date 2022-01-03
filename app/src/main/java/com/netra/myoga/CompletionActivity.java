package com.netra.myoga;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.ArrayList;

public class CompletionActivity extends AppCompatActivity {
    double percentDone;
    int day;
    int week;
    private GoogleSignInAccount account;
    TextView numExercises, timeView, weekDay;
    Button returnDash;
    double [] weeklyProgress = new double[10];
    DBConnection connection = new DBConnection(this);
    String userEmail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.completionbetapage);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            String personEmail = account.getEmail();
            userEmail = personEmail;
        }

        day = getIntent().getIntExtra("Day",1);
        week = getIntent().getIntExtra("Week",1);
        percentDone = getIntent().getDoubleExtra("TotalTime",0);
        weeklyProgress = getIntent().getDoubleArrayExtra("WeeklyProgress");

        numExercises = (TextView) findViewById(R.id.exercisesDone);
        timeView = (TextView) findViewById(R.id.durationOf);
        weekDay = (TextView) findViewById(R.id.dayComp);
        returnDash = (Button) findViewById(R.id.backDash);

        numExercises.setText(String.format("%d", (int) (percentDone*10)));
        timeView.setText(String.format("%d", (int)(percentDone * 20)));
        String dayStr = "Week " + week + ", Day " + day + " Completed!";
        weekDay.setText(dayStr);

        connection.UpdateUserProgress(weeklyProgress, userEmail, week);
        Log.i("DEBUG", "Database has been updated with weekly progress for week");




         returnDash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TEST", "Skipping to next exercise");
                Intent intent = new Intent(CompletionActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });



    }

    @Override
    public void onBackPressed(){

        Log.i("TEST", "Back button clicked");
        Intent intent = new Intent(CompletionActivity.this, DashboardActivity.class);
        startActivity(intent);
    }
}
