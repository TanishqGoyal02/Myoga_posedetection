package com.netra.myoga;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

//import org.tensorflow.lite.examples.poseestimation.MainActivity2;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DashboardActivity extends AppCompatActivity {

    TextView date_textView;
    Button week1_button, week2_button,week3_button,week4_button;
    Button sosbutton, dojobutton;
    boolean isPressed = false;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.dashboard);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        date_textView = findViewById(R.id.date_textView);
        week1_button = findViewById(R.id.week1_button);
        week2_button = findViewById(R.id.week2_button);
        week3_button = findViewById(R.id.week3_button);
        week4_button = findViewById(R.id.week4_button);
        sosbutton = findViewById(R.id.sos_button);
        dojobutton = findViewById(R.id.DOJO);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, MMM dd");
        String dateTime = simpleDateFormat.format(calendar.getTime());
        date_textView.setText(dateTime);


        week1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("TEST", "Week 1 button clicked");
                Intent intent = new Intent(DashboardActivity.this, Level1Activity.class);
                intent.putExtra("Week", 1);
                startActivity(intent);
            }
        });


        week2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("TEST", "Week 2 button clicked");
                Intent intent = new Intent(DashboardActivity.this, Level1Activity.class);
                intent.putExtra("Week", 2);
                startActivity(intent);
            }
        });

        week3_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("TEST", "Week 3 button clicked");
                Intent intent = new Intent(DashboardActivity.this, Level1Activity.class);
                intent.putExtra("Week", 3);
                startActivity(intent);
            }
        });


        week4_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("TEST", "Week 4 button clicked");

                Intent intent = new Intent(DashboardActivity.this, Level1Activity.class);
                intent.putExtra("Week", 4);
                startActivity(intent);
            }
        });

        sosbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("TEST", "SOS button clicked");
                Intent intent = new Intent(DashboardActivity.this, SOSActivity.class);
                startActivity(intent);
            }
        });

        dojobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("TEST", "DOJO button clicked");
                Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });




    }

    @Override
    public void onBackPressed() {

        if (isPressed){

            finishAffinity();
            System.exit(0);
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

