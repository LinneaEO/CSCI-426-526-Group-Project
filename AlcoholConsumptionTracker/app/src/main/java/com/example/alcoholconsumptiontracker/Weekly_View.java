package com.example.alcoholconsumptiontracker;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class Weekly_View extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_weekly__view, container, false);

        // Find the EditText
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        EditText editTextWeekRange = view.findViewById(R.id.editTextWeekRange);

        // Get current week start and end dates
        String weekRange = getCurrentWeekRange();
        editTextWeekRange.setText(weekRange);

        return view;
    }

    private String getCurrentWeekRange() {

        Calendar calendar = Calendar.getInstance();

        // Set to the start of the week (e.g., Sunday)
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd", Locale.getDefault());
        String startOfWeek = dateFormat.format(calendar.getTime());

        // Move to the end of the week (Saturday)
        calendar.add(Calendar.DAY_OF_WEEK, 6);
        String endOfWeek = dateFormat.format(calendar.getTime());

        return startOfWeek + " - " + endOfWeek;
    }
}