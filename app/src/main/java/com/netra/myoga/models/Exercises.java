package com.netra.myoga.models;

import java.io.Serializable;
import java.util.Map;

//Exercise Entity ; <String : Exercise name, String: corresponding video>
public class Exercises implements Serializable {

    private  String exercise;
    private boolean complete;
    private int duration;
    private String uri;

    public Exercises() {

    }

    public Exercises(String posename, int duration, String uri)
    {
        this.exercise = posename;
        this.complete = false;
        this.duration = duration;
        this.uri = uri;
    }

    public void setComplete(boolean completion)
    {
        this.complete = completion;
    }

    public boolean getCompletion()
    {
        return  this.complete;
    }

    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    public int getDuration() { return this.duration; }

    public void setExercise(String exercise)
    {
        this.exercise = exercise;
    }

    public String getExercise() {
        return this.exercise;
    }

    public void setUri(String uri)
    {
        this.uri = uri;
    }

    public String getUri() {return this.uri;}

}
