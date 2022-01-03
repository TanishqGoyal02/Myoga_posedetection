package com.netra.myoga.models;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

/**TODO :  Use ROOM for version 2
https://medium.com/swlh/to-do-list-app-using-room-and-mvvm-architecture-37c88deb7648

 **/


public class    User {

    private String email;
    private String userName;
    private static ArrayList<User> users = new ArrayList<>();

    //  TODO : make it enum
    private String gender;

    //  TODO : make it enum
    private String purpose;

    private int weight;
    private int height;
    private int age;


    //  Fixme : Bad Design Pattern
    public User(String email, String gender, String purpose, int height, int weight, int age) {
        this.email = email;
        this.gender = gender;
        this.purpose = purpose;
        this.height = height;
        this.weight = weight;
        this.age = age;

    }

    public void setUsers(User user)
    {
        users.add(user);
    }

    public static ArrayList<User> getUsersList()
    {
        return users;
    }

    public static String getUsersJson()
    {
        return new Gson().toJson(users);
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
