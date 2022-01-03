package com.netra.myoga.models;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WeekModel implements Serializable {

    //map from name to exercise object, url included in exercise model
 private Map<String, Exercises> poses = new HashMap<>();
 private Map<String, Boolean> dailyCompletion = new HashMap<>();
 private double [] weeklyCompletion = new double[10];
 private int weekNo;


    public WeekModel() {}

    public WeekModel(int weekNo,Map<String,Exercises> poses)
    {
        this.weekNo = weekNo;
        this.poses = poses;
        Set<String> names = poses.keySet();
        for (String s: names) {
            this.dailyCompletion.put(s, false);
        }
        for(int i = 0; i < 7; i++) {
            weeklyCompletion[i] = 0.0;
        }
    }


    public Collection<Exercises> getExercises() {
        return this.poses.values();
    }

    public Exercises getPose(String name) {
        return poses.get(name);
    }

    //to edit a single exercise object
//    public void editExercise (Exercises pose, String url) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            poses.putIfAbsent(pose,url);
//        }
//    }

    public double getDailyCompletion() {
        int complete = 0;
        Collection<Boolean> completionList = dailyCompletion.values();
        for(Boolean b: completionList) {
            if(b == true) {
                complete++;
            }
        }
        return (double) complete / completionList.size();
    }

    public double[] getWeeklyCompletion() {
        return weeklyCompletion;
    }

    public void setWeeklyCompletion(int dayNumber, double value) {
        if(dayNumber >= 0 && dayNumber < 7) {
            weeklyCompletion[dayNumber] = value  ;
        }
    }

    public void copyWeeklyCompletion(double[] weekly) {
        for(int i = 0; i < 7; i++) {
            weeklyCompletion[i] = weekly[i];
        }
    }

    public void setCompletion(String exName) {
        if (poses.containsKey(exName)) {
            dailyCompletion.put(exName, true);
            poses.get(exName).setComplete(true);
        }
    }

    public void setWeekNo(int week_no)
    {
        this.weekNo = week_no;
    }

    public int getWeekNo()
    {
        return this.weekNo;
    }

//    public String WeekJsonModel()
//    {
//        return new Gson().toJson(new WeekModel(this.weekNo,this.completion));
//    }
}
