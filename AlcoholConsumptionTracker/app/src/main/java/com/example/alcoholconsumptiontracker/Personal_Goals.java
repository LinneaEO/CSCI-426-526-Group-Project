package com.example.alcoholconsumptiontracker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Personal_Goals extends Fragment {
    // Constructor for personal goals - daily, monthly, weekly
    public class personalGoal {
        double goalPrice;
        int goalServings;
        int goalCalorie;
        String goalWritten;

        personalGoal(double goalPricePerson, int goalServings, int goalCalorie, String goalWritten) {
            //this.goalPrice = goalPrice;
            //this.goalServings = goalServings;
            //this.goalCalorie = goalCalorie;
            //this.goalWritten = goalWritten;
            goalPrice = 0.00;
            goalServings = 0;
            goalCalorie = 0;
            goalWritten = null;
        }

        // Getters
        public double getGoalPrice() {
            return this.goalPrice;
        }
        public int getGoalServings() {
            return this.goalServings;
        }
        public int getGoalCalorie() {
            return this.goalCalorie;
        }
        public String getGoalWritten() {
            return this.goalWritten;
        }

        // Setters - make em void cuz u dont wanna return anything
        public void setGoalPrice(double newGoalPrice) {
            this.goalPrice = newGoalPrice;
        }

        public void setGoalServings(int newGoalServings) {
            this.goalServings = newGoalServings;
        }

        public void setGoalCalorie(int newGoalCaolorie) {
            this.goalCalorie = newGoalCaolorie;
        }

        public void setGoalWritten(String newGoalWritten) {
            this.goalWritten = newGoalWritten;
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personal__goals, container, false);
    }
}