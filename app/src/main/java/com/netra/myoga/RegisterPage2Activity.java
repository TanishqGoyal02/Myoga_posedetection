package com.netra.myoga;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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

public class RegisterPage2Activity extends AppCompatActivity {

    ImageButton male_button,female_button,other_button;
    Button continue_button;
    Boolean malebuttonclicked=false,femalebuttonclicked=false,otherbuttonclicked=false;
    String userDetail = "", userGender = "",userFirstName="",userEmail="",userLastName="",userAge="", userHeight="", userWeight="";
    /*int userAge=0, userHeight=0, userWeight=0;*/

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.register_page2);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        male_button = findViewById(R.id.male_button);
        female_button = findViewById(R.id.female_button);
        other_button = findViewById(R.id.other_button);
        continue_button = findViewById(R.id.continue_button);
        userDetail = getIntent().getStringExtra("UserDetails");


        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            userFirstName = bundle.getString("UserFirstName");
            userLastName = bundle.getString("UserLastName");
            userEmail = bundle.getString("UserEmail");
            userAge = bundle.getString("UserAge");
            userWeight = bundle.getString("UserWeight");
            userHeight = bundle.getString("UserHeight");
        }


        male_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                male_button.setBackgroundResource(R.drawable.registration_page2_circle_darkcolor);
                female_button.setBackgroundResource(R.drawable.registration_page2_circle);
                other_button.setBackgroundResource(R.drawable.registration_page2_circle);
                malebuttonclicked = true;
                femalebuttonclicked = false;
                otherbuttonclicked = false;
                userGender = "Male";
            }
        });


        female_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                female_button.setBackgroundResource(R.drawable.registration_page2_circle_darkcolor);
                male_button.setBackgroundResource(R.drawable.registration_page2_circle);
                other_button.setBackgroundResource(R.drawable.registration_page2_circle);
                malebuttonclicked = false;
                femalebuttonclicked = true;
                otherbuttonclicked = false;
                userGender = "Female";
            }
        });


        other_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                other_button.setBackgroundResource(R.drawable.registration_page2_circle_darkcolor);
                female_button.setBackgroundResource(R.drawable.registration_page2_circle);
                male_button.setBackgroundResource(R.drawable.registration_page2_circle);
                malebuttonclicked = false;
                femalebuttonclicked = false;
                otherbuttonclicked = true;
                userGender = "Other";
            }
        });


        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(malebuttonclicked || femalebuttonclicked || otherbuttonclicked == true) {

                    Log.i("TEST", "Continue button clicked");

                    Intent intent = new Intent(RegisterPage2Activity.this, RegisterPage1Activity.class);
                    intent.putExtra("UserDetails", userDetail);
                    intent.putExtra("UserGender", userGender);
                    intent.putExtra("UserFirstName", userFirstName);
                    intent.putExtra("UserLastName", userLastName);
                    intent.putExtra("UserEmail", userEmail);
                    intent.putExtra("UserAge", userAge);
                    intent.putExtra("UserWeight", userWeight);
                    intent.putExtra("UserHeight", userHeight);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(RegisterPage2Activity.this, "Please select the gender", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

