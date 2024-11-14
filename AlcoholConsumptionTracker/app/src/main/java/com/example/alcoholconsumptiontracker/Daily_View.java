package com.example.alcoholconsumptiontracker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Daily_View extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_daily__view, container, false);

        //  Get id and set the current date to the EditText
        EditText editTextDate = view.findViewById(R.id.editTextDate2);
        String currentDate = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(new Date());
        editTextDate.setText(currentDate);

        return view;
    }

}