package com.netra.myoga;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

public class Week4Activity extends AppCompatActivity {

    TextView userName, email;
    ImageView userImage;
    Button level1_button;

    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions options;
    private GoogleSignInAccount account;


    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        //setContentView(R.layout.level4_homepage);


        userName = (TextView) findViewById(R.id.userName);
        email = (TextView) findViewById(R.id.Email);
        userImage = (ImageView) findViewById(R.id.userImage);
        //level1_button = (Button) findViewById(R.id.week1);



    }
}

