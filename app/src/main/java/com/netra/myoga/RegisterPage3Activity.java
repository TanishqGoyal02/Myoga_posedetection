package com.netra.myoga;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Timer;

public class RegisterPage3Activity extends AppCompatActivity {

    Button continue_button;

    EditText age, height, weight, firstname, lastname, email ;
    String userFirstName="",userEmail="",userLastName="",userAge="", userHeight="", userWeight="";
    /*int userAge=0, userHeight=0, userWeight=0;*/

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.register_page3);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        age = (EditText) findViewById(R.id.age);
        firstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.lastname);
        email = (EditText) findViewById(R.id.useremail);


        weight.addTextChangedListener(new TextWatcher(){
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
            }

            public void beforeTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                Log.i("ABefore Change", weight.getText().toString());
            }

            public void afterTextChanged(Editable arg0) {
                /*userWeight = Integer.parseInt(weight.getText().toString());*/
                userWeight = weight.getText().toString();
            }
        });

        height.addTextChangedListener(new TextWatcher(){
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
            }

            public void beforeTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                Log.i("ABefore Change", height.getText().toString());
            }

            public void afterTextChanged(Editable arg0) {
                /*userHeight = Integer.parseInt(height.getText().toString());*/
                userHeight = height.getText().toString();
            }
        });

        age.addTextChangedListener(new TextWatcher(){
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
            }

            public void beforeTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                Log.i("ABefore Change", age.getText().toString());
            }

            public void afterTextChanged(Editable arg0) {
                /* userAge = Integer.parseInt(age.getText().toString());*/
                userAge = age.getText().toString();
            }
        });


        firstname.addTextChangedListener(new TextWatcher(){
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
            }

            public void beforeTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                Log.i("ABefore Change", firstname.toString());
            }

            public void afterTextChanged(Editable arg0) {
                userFirstName = firstname.getText().toString();
            }
        });

        lastname.addTextChangedListener(new TextWatcher(){
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
            }

            public void beforeTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                Log.i("ABefore Change", lastname.toString());
            }

            public void afterTextChanged(Editable arg0) {
                userLastName = lastname.getText().toString();
            }
        });


        email.addTextChangedListener(new TextWatcher(){
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
            }

            public void beforeTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                Log.i("ABefore Change", email.toString());
            }

            public void afterTextChanged(Editable arg0) {
                userEmail = email.getText().toString();
            }
        });


        String emailValidate = userEmail.toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


        continue_button = findViewById(R.id.continue_button);

        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( ( firstname.getText().toString().trim().equals("")) )
                {
                    Toast.makeText(RegisterPage3Activity.this, "Please enter your first name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ( ( lastname.getText().toString().trim().equals("")) )
                {
                    Toast.makeText(RegisterPage3Activity.this, "Please enter your last name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ( ( email.getText().toString().trim().equals("")) )
                {
                    Toast.makeText(RegisterPage3Activity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!userEmail.matches(emailPattern))
                {
                    Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
                    return;
                }
                if ( ( age.getText().toString().trim().equals("")) )
                {
                    Toast.makeText(RegisterPage3Activity.this, "Please enter your age", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ( ( weight.getText().toString().trim().equals("")) )
                {
                    Toast.makeText(RegisterPage3Activity.this, "Please enter your weight", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ( ( height.getText().toString().trim().equals("")) )
                {
                    Toast.makeText(RegisterPage3Activity.this, "Please enter your height", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    Log.i("TEST", "Continue button clicked");
                    Intent intent = new Intent(RegisterPage3Activity.this, RegisterPage2Activity.class);
                    intent.putExtra("UserFirstName", userFirstName);
                    intent.putExtra("UserLastName", userLastName);
                    intent.putExtra("UserEmail", userEmail);
                    intent.putExtra("UserAge", userAge);
                    intent.putExtra("UserWeight", userWeight);
                    intent.putExtra("UserHeight", userHeight);
                    startActivity(intent);
                }
            }
        });

    }
}
