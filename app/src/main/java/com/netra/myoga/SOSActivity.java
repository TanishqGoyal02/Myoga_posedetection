package com.netra.myoga;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.netra.myoga.models.Exercises;
import com.netra.myoga.models.WeekModel;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//import ticker.views.com.ticker.widgets.circular.timer.view.CircularView;

public class SOSActivity extends AppCompatActivity {

    private Map <String, Exercises> poses = new HashMap<>();
    private WeekModel week1, week2, week3, week4, sos;
    private int week, day;
    private String api_key = "AIzaSyC6I5inDAdAlH9j2_OOER2cmCGAew79XN4";
    private double [] currentProgress = new double[10];


    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

//        Exercises
        Exercises om_chanting = new Exercises("Om Chanting", 94, "RV1aGuksqFM"); //830FIB_Z0aM
        Exercises yogic_breathe = new Exercises("Yogic Breathing", 77, "DmVCOMQ9_o0"); //P_B-0agON_U
        Exercises head_press =  new Exercises("Head Press", 48, "d74kuYbpiFY"); //4D28HgC_tFY
       /* Exercises scalp_massage = new Exercises("Scalp Massage", 47, "2DH1-kKBMIw");
        Exercises neck_rotation = new Exercises("Neck Rotation", 96, "BZguhriLcPM");
        Exercises downward_dog = new Exercises("Downward Dog", 150, "V7IO1zhTxwA");
        Exercises shavasana = new Exercises("Shavasana", 230, "2IqrOz1T4qo");*/
        Exercises bhramari = new Exercises("Bhramari", 100, "7Qtr3iCLuIE");
        //Exercises naadi_shodan = new Exercises("Naadi Shodan", 132, "x79kcA3J1Ec");
        Exercises cool_down = new Exercises("Cool Down", 36, "kUgA8VCD48E");

        //Poses for the Week
        poses.put(om_chanting.getExercise(), om_chanting);
        poses.put(yogic_breathe.getExercise(), yogic_breathe);
        poses.put(head_press.getExercise(), head_press);
        /*poses.put(scalp_massage.getExercise(), scalp_massage);
        poses.put(neck_rotation.getExercise(), neck_rotation);
        poses.put(downward_dog.getExercise(), downward_dog);
        poses.put(shavasana.getExercise(), shavasana);*/
        poses.put(bhramari.getExercise(), bhramari);
        //poses.put(naadi_shodan.getExercise(), naadi_shodan);
        poses.put(cool_down.getExercise(), cool_down);


        currentProgress = getIntent().getDoubleArrayExtra("WeeklyProgress");
        sos = new WeekModel(5, poses);

        week = getIntent().getIntExtra("Week", 5);
        day = getIntent().getIntExtra("Day", 1);
        //updateWeek(week);
        callPose(1);

    }

    public void callPose(int poseNum) {
        Log.i("TEST", "Going into Screen for Pose " + poseNum);
        Intent intent = new Intent(SOSActivity.this, PoseCount.class);
        Log.i("ERROR", "Is this where its breaking?");
        //intent.putExtra("WeekModel", chooseWeeklyModel());
        Exercises temp = chooseExercise(poseNum);
        Log.i("NAME", temp.getExercise());
        intent.putExtra("ExName", temp.getExercise());
        Log.i("Exerices", temp.getExercise());
        intent.putExtra("ExUrl", temp.getUri());
        intent.putExtra("Duration", temp.getDuration());
        intent.putExtra("PoseNum", poseNum);
        startActivityForResult(intent, poseNum);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode < 5) {
            if(resultCode == 1) {
                /*WeekModel tWeek = chooseWeeklyModel();
                tWeek.setCompletion(chooseExercise(requestCode).getExercise());*/
            }
            callPose(requestCode+1);
        }
        else {
            Intent intent = new Intent(SOSActivity.this, DashboardActivity.class);
            startActivity(intent);
        }
    }

    private Exercises chooseExercise(int poseNum) {
        switch (poseNum) {
            case 1:
                return poses.get("Om Chanting");
            case 2:
                return poses.get("Yogic Breathing");
            case 3:
                return poses.get("Head Press");
            case 4:
                return poses.get("Bhramari");
            case 5:
                return poses.get("Cool Down");

        }
        return poses.get("Cool Down");
    }
}
