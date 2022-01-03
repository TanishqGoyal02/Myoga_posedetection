package com.netra.myoga;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class PoseControlFlow extends AppCompatActivity {
    int day;
    int week;

    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        day = getIntent().getIntExtra("Day",2);
        week = getIntent().getIntExtra("Week",2);
        //this is where we will call the chain of exercises


        //
        callPose(1);

        //long endTime   = System.nanoTime();
        //totalTime = (endTime - startTime)/1_000_000_000;



    }

    public void callPose(int poseNum) {
        Log.i("TEST", "Going into Screen for Pose " + poseNum);
        Intent intent = new Intent(PoseControlFlow.this, PoseCount.class);
        intent.putExtra("SequenceNum",poseNum);
        intent.putExtra("Day",day);
        intent.putExtra("Week",week);
        startActivityForResult(intent,poseNum);
    }

//    public void callComplete(long totalTime) {
//        Log.i("TEST", "Going to Completion Screen");
//        Intent comp = new Intent(PoseControlFlow.this, CompletionActivity.class);
//        comp.putExtra("TotalTime",totalTime);
//        comp.putExtra("Day",day);
//        comp.putExtra("Week",week);
//        startActivity(comp);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode < 10) {
            callPose(requestCode+1);
        }
        else {
            Log.i("DBUTTON", "Return to Main Page");
            Intent intent = new Intent(PoseControlFlow.this, DashboardActivity.class);
            startActivity(intent);
        }
    }

}
