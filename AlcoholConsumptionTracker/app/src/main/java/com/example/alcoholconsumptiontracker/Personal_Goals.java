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

        personalGoal(double goalPrice, int goalServings, int goalCalorie, String goalWritten) {
            this.goalPrice = goalPrice;
            this.goalServings = goalServings;
            this.goalCalorie = goalCalorie;
            this.goalWritten = goalWritten;
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