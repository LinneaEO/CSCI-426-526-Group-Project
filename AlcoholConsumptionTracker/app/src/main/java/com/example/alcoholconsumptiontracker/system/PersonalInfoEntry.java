package com.example.alcoholconsumptiontracker.system;

import android.util.Log;

import com.example.alcoholconsumptiontracker.Personal_Info;

public class PersonalInfoEntry {
    private  String userName;
    private  int weight;
    private  double height;
    private  String sex;
    private  int age;

    public PersonalInfoEntry() {
        this.userName = null;
        this.weight = 0;
        this.height = 0.0;
        this.sex = null;
        this.age = 0;
    }

    // Setterz and getterz

    // Getters - when u wanna know
    public  String getUserName() {
        return userName;
    }
    public  int getWeight() {
        return weight;
    }
    public  double getHeight() {
        return height;
    }
    public  String getSex() {
        return sex;
    }
    public  int getAge() {
        return age;
    }

    // Setters - when u wanna change
    public  void setUserName(String newUserName) {
        this.userName = newUserName;
        Log.d("PersonalInfoEntry", "UserName updated to: " + userName);
    }

    public  void setWeight(int newWeight) {
        this.weight = newWeight;
    }

    public  void setHeight(Double newHeight) {
        this.height = newHeight;
    }

    public  void setSex(String newSex) {
        this.sex = newSex;
    }

    public  void setAge(int newAge) {
        this.age = newAge;
    }

}
