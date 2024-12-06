package com.example.alcoholconsumptiontracker.system;

import com.example.alcoholconsumptiontracker.Personal_Info;

public class PersonalInfoEntry {
    private static String userName;
    private static int weight;
    private static double height;
    private static String sex;
    private static int age;

    public void personalInfoEntry() {
        this.userName = null;
        this.weight = 0;
        this.height = 0.0;
        this.sex = null;
        this.age = 0;
    }

    // Setterz and getterz

    // Getters - when u wanna know
    public static String getUserName() {
        return userName;
    }
    public static int getWeight() {
        return weight;
    }
    public static double getHeight() {
        return height;
    }
    public static String getSex() {
        return sex;
    }
    public static int getAge() {
        return age;
    }

    // Setters - when u wanna change
    public static void setUserName(String newUserName) {
        PersonalInfoEntry.userName = newUserName;
    }

    public static void setWeight(int newWeight) {
        PersonalInfoEntry.weight = newWeight;
    }

    public static void setHeight(int newHeight) {
        PersonalInfoEntry.height = newHeight;
    }

    public static void setSex(String newSex) {
        PersonalInfoEntry.sex = newSex;
    }

    public static void setAge(int newAge) {
        PersonalInfoEntry.age = newAge;
    }

}
