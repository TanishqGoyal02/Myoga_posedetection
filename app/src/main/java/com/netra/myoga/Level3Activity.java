package com.netra.myoga;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class Level3Activity extends AppCompatActivity {

    TextView userName, email;
    ImageView userImage;
    Button level3_button;
    Button day1B, day2B, day3B, day4B, day5B, day6B, day7B;

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

        //userName = (TextView) findViewById(R.id.userName);
        //email = (TextView) findViewById(R.id.Email);
        //userImage = (ImageView) findViewById(R.id.userImage);
        //level3_button = (Button) findViewById(R.id.level3);
        day1B = (Button) findViewById(R.id.day1);
        day2B = (Button) findViewById(R.id.day2);
        day3B = (Button) findViewById(R.id.day3);
        day4B = (Button) findViewById(R.id.day4);
        day5B = (Button) findViewById(R.id.day5);
        day6B = (Button) findViewById(R.id.day6);
        day7B = (Button) findViewById(R.id.day7);

        day1B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("TEST", "Level 1 button clicked");
                Intent intent = new Intent(Level3Activity.this, WeeklyActivity.class);
                intent.putExtra("Account",account);
                intent.putExtra("Week",3);
                intent.putExtra("Day",1);
                startActivity(intent);
            }
        });

        day2B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("TEST", "Level 1 button clicked");
                Intent intent = new Intent(Level3Activity.this, WeeklyActivity.class);
                intent.putExtra("Account",account);
                intent.putExtra("Week",3);
                intent.putExtra("Day",2);
                startActivity(intent);
            }
        });

        day3B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("TEST", "Level 1 button clicked");
                Intent intent = new Intent(Level3Activity.this, WeeklyActivity.class);
                intent.putExtra("Account",account);
                intent.putExtra("Week",3);
                intent.putExtra("Day",3);
                startActivity(intent);
            }
        });

        day4B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("TEST", "Level 1 button clicked");
                Intent intent = new Intent(Level3Activity.this, WeeklyActivity.class);
                intent.putExtra("Account",account);
                intent.putExtra("Week",3);
                intent.putExtra("Day",4);
                startActivity(intent);
            }
        });

        day5B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("TEST", "Level 1 button clicked");
                Intent intent = new Intent(Level3Activity.this, WeeklyActivity.class);
                intent.putExtra("Account",account);
                intent.putExtra("Week",3);
                intent.putExtra("Day",5);
                startActivity(intent);
            }
        });

        day6B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("TEST", "Level 1 button clicked");
                Intent intent = new Intent(Level3Activity.this, WeeklyActivity.class);
                intent.putExtra("Account",account);
                intent.putExtra("Week",3);
                intent.putExtra("Day",6);
                startActivity(intent);
            }
        });

        day7B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("TEST", "Level 1 button clicked");
                Intent intent = new Intent(Level3Activity.this, WeeklyActivity.class);
                intent.putExtra("Account",account);
                intent.putExtra("Week",3);
                intent.putExtra("Day",7);
                startActivity(intent);
            }
        });



    }

    @Override
    public void onBackPressed(){

        Log.i("TEST", "Back button clicked");
        Intent intent = new Intent(Level3Activity.this, DashboardActivity.class);
        startActivity(intent);
    }
}

