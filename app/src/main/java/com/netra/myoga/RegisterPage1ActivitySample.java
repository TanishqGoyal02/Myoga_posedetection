package com.netra.myoga;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Timer;

public class RegisterPage1ActivitySample extends AppCompatActivity {

    CheckBox Checkbox1,Checkbox2,Checkbox3,Checkbox4,Checkbox5;
    Button continue_button;
    DBConnection connection = new DBConnection(this);
    String userDetailStatus = "", userGender = "",userFirstName="",userEmail="",userLastName="",userAge="", userHeight="", userWeight="";
   /* int userAge=0, userHeight=0, userWeight=0;*/

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.register_page1_sample);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Checkbox1 = findViewById(R.id.checkBox1);
        Checkbox2 = findViewById(R.id.checkBox2);
        Checkbox3 = findViewById(R.id.checkBox3);
        Checkbox4 = findViewById(R.id.checkBox4);
        Checkbox5 = findViewById(R.id.checkBox5);
        continue_button = findViewById(R.id.continue_button);


        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            userFirstName = bundle.getString("UserFirstName");
            userLastName = bundle.getString("UserLastName");
            userEmail = bundle.getString("UserEmail");
            userAge = bundle.getString("UserAge");
            userWeight = bundle.getString("UserWeight");
            userHeight = bundle.getString("UserHeight");
            userGender = bundle.getString("UserGender");
            userDetailStatus = bundle.getString("UserDetails");
        }

        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("TEST", "Continue button clicked");

                Intent intent = new Intent(RegisterPage1ActivitySample.this, DashboardActivity.class);
                startActivity(intent);
                updateDB();
            }
        });
    }

    public void updateDB()
    {

        Log.i("Update DB", userFirstName);
        Log.i("Update DB", userLastName);
        Log.i("Update DB", userEmail);
        Log.i("Update DB", userGender);
        Log.i("Update DB", String.valueOf(userAge));
        Log.i("Update DB", String.valueOf(userHeight));
        Log.i("Update DB", String.valueOf(userWeight));
        Log.i("Update DB", String.valueOf(userDetailStatus));
        connection.insertUser(userFirstName,userLastName,userEmail,userGender,userAge,userHeight,userWeight,userDetailStatus);

        String data = connection.getData().toString();

        Log.i("Table Data:" , data);
       /* ArrayList<String> result = connection.getAllUsers();

        for(String contact : result)
        {
            Log.i("Contacts: ", contact);
        }*/

    }

}
