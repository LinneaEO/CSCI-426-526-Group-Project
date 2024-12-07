package com.example.alcoholconsumptiontracker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class Personal_Goals extends Fragment {
    // Constructor for personal goals - daily, monthly, weekly

    public static EditText goalPriceDailyInput;
    public static EditText goalServingsDailyInput;
    public static EditText goalCalorieDailyInput;
    public static EditText dailyWrittenGoals;

    public static EditText goalPriceWeeklyInput;
    public static EditText goalServingsWeeklyInput;
    public static EditText goalCalorieWeeklyInput;
    public static EditText weeklyWrittenGoals;


    public static EditText goalPriceMonthlyInput;
    public static EditText goalServingsMonthlyInput;
    public static EditText goalCalorieMonthlyInput;
    public static EditText monthlyWrittenGoals;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_personal__goals, container, false);

//        goalpricedailyid
//                goalservingsdailyid
//        goalcaloriesdailyid
//                goalinfodailyid
//        goalpriceweeklyid
//                goalservingsweeklyid
//        goalcaloriesweeklyid
//                goalinfoweeklyid
//        goalpricemonthlyid
//                goalservingsmonthlyid
//        goalcaloriesmonthlyid
//                goalinfomonthlyid

        goalPriceDailyInput = root.findViewById(R.id.goalpricedailyid);
        goalServingsDailyInput = root.findViewById(R.id.goalservingsdailyid);
        goalCalorieDailyInput = root.findViewById(R.id.goalcaloriesdailyid);
        dailyWrittenGoals = root.findViewById(R.id.goalinfodailyid);

        goalPriceWeeklyInput = root.findViewById(R.id.goalpriceweeklyid);
        goalServingsWeeklyInput = root.findViewById(R.id.goalpriceweeklyid);
        goalCalorieWeeklyInput = root.findViewById(R.id.goalpriceweeklyid);
        weeklyWrittenGoals = root.findViewById(R.id.goalinfoweeklyid);

        goalPriceMonthlyInput = root.findViewById(R.id.goalpricemonthlyid);
        goalServingsMonthlyInput = root.findViewById(R.id.goalservingsmonthlyid);
        goalCalorieMonthlyInput = root.findViewById(R.id.goalcaloriesmonthlyid);
        monthlyWrittenGoals = root.findViewById(R.id.goalinfomonthlyid);



        return root;
    }
}