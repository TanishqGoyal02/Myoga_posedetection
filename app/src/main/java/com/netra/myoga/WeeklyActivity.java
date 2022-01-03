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

public class WeeklyActivity extends AppCompatActivity {

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
        Exercises scalp_massage = new Exercises("Scalp Massage", 47, "2DH1-kKBMIw");
        Exercises neck_rotation = new Exercises("Neck Rotation", 96, "BZguhriLcPM");
        Exercises downward_dog = new Exercises("Downward Dog", 150, "V7IO1zhTxwA");
        Exercises shavasana = new Exercises("Shavasana", 230, "2IqrOz1T4qo");
        Exercises bhramari = new Exercises("Bhramari", 100, "7Qtr3iCLuIE");
        Exercises naadi_shodan = new Exercises("Naadi Shodan", 132, "x79kcA3J1Ec");
        Exercises cool_down = new Exercises("Cool Down", 36, "kUgA8VCD48E");

        //Poses for the Week
        poses.put(om_chanting.getExercise(), om_chanting);
        poses.put(yogic_breathe.getExercise(), yogic_breathe);
        poses.put(head_press.getExercise(), head_press);
        poses.put(scalp_massage.getExercise(), scalp_massage);
        poses.put(neck_rotation.getExercise(), neck_rotation);
        poses.put(downward_dog.getExercise(), downward_dog);
        poses.put(shavasana.getExercise(), shavasana);
        poses.put(bhramari.getExercise(), bhramari);
        poses.put(naadi_shodan.getExercise(), naadi_shodan);
        poses.put(cool_down.getExercise(), cool_down);


        currentProgress = getIntent().getDoubleArrayExtra("WeeklyProgress");

        //TODO : FIX THIS LOGIC:
        //  This is passing all the same poses to all the weeks


        week1 = new WeekModel(1,poses);
        week2 = new WeekModel(2,poses);
        week3 = new WeekModel(3,poses);
        week4 = new WeekModel(4, poses);
        sos = new WeekModel(5, poses);

        Log.i("TEST", "Weekly Models have been created");

        week = getIntent().getIntExtra("Week", 1);
        day = getIntent().getIntExtra("Day", 1);
       updateWeek(week);
        callPose(1);


//        switch (week) {
//            case 1:
//                playerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
//                    @Override
//                    public void onReady(YouTubePlayer youTubePlayer) {
//                        super.onReady(youTubePlayer);
//                        youTubePlayer.loadVideo(poses.get("Om Chanting").getUrl(),0);
//                        youTubePlayer.play();
//                    }
//
//                    @Override
//                    public void onStateChange(YouTubePlayer youTubePlayer, PlayerConstants.PlayerState state) {
//                        super.onStateChange(youTubePlayer, state);
//                    }
//
//                });
//
//
//                break;
//        default:
//            playerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
//                @Override
//                public void onReady(YouTubePlayer youTubePlayer) {
//                    super.onReady(youTubePlayer);
//                    youTubePlayer.loadVideo(poses.get("Om Chanting").getUrl(),0);
//                    youTubePlayer.play();
//                }
//
//                @Override
//                public void onStateChange(YouTubePlayer youTubePlayer, PlayerConstants.PlayerState state) {
//                    super.onStateChange(youTubePlayer, state);
//                }
//
//            });
//            break;
//    }


    }

    public void callPose(int poseNum) {
        Log.i("TEST", "Going into Screen for Pose " + poseNum);
        Intent intent = new Intent(WeeklyActivity.this, PoseCount.class);
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

    public void callComplete() {
        Log.i("TEST", "Going to Completion Screen");
        WeekModel tWeek = chooseWeeklyModel();
        tWeek.setCompletion(chooseExercise(10).getExercise());
        tWeek.setWeeklyCompletion(day-1, tWeek.getDailyCompletion());
        Intent comp = new Intent(WeeklyActivity.this, CompletionActivity.class);
        comp.putExtra("WeeklyProgress", tWeek.getWeeklyCompletion());
        comp.putExtra("TotalTime", chooseWeeklyModel().getDailyCompletion());
        comp.putExtra("Day",day);
        comp.putExtra("Week",week);
        startActivity(comp);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode < 10) {
            if(resultCode == 1) {
                WeekModel tWeek = chooseWeeklyModel();
                tWeek.setCompletion(chooseExercise(requestCode).getExercise());
            }
            callPose(requestCode+1);
        }
        else {
            callComplete();
        }
    }

    private WeekModel chooseWeeklyModel() {
        switch (week) {
            case 1:
                return week1;
            case 2:
                return week2;
            case 3:
                return week3;
            case 4:
                return sos;
            default:
                return week1;
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
                return poses.get("Scalp Massage");
            case 5:
                return poses.get("Neck Rotation");
            case 6:
                return poses.get("Downward Dog");
            case 7:
                return poses.get("Shavasana");
            case 8:
                return poses.get("Bhramari");
            case 9:
                return poses.get("Naadi Shodan");
            case 10:
                return poses.get("Cool Down");

        }
        return poses.get("Cool Down");
    }

    public void updateWeek(int week) {
        switch (week){
            case 1:
                week1.copyWeeklyCompletion(currentProgress);
                break;
            case 2:
                week2.copyWeeklyCompletion(currentProgress);
                break;
            case 3:
                week3.copyWeeklyCompletion(currentProgress);
                break;
            case 4:
                week4.copyWeeklyCompletion(currentProgress);
                break;
            case 5:
                sos.copyWeeklyCompletion(currentProgress);
                break;
            default:
        }
    }
}
