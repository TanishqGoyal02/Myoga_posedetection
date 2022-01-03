package com.netra.myoga;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

public class Week1Activity extends AppCompatActivity {

    TextView userName, email;
    ImageView userImage;
    Button level1_button;
    Button day1B;

    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions options;
    private GoogleSignInAccount account;


    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.level1_homepage);


        userName = (TextView) findViewById(R.id.userName);
        email = (TextView) findViewById(R.id.Email);
        userImage = (ImageView) findViewById(R.id.userImage);
        //level1_button = (Button) findViewById(R.id.week1);
        day1B = (Button) findViewById(R.id.day1);

        day1B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("TEST", "Level 1 button clicked");
                Intent intent = new Intent(Week1Activity.this, PoseControlFlow.class);
                intent.putExtra("Account",account);
                intent.putExtra("Week",1);
                intent.putExtra("Day",1);
                startActivity(intent);
            }
        });

    }
}

