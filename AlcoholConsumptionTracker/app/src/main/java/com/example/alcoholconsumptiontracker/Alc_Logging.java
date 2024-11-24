package com.example.alcoholconsumptiontracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.alcoholconsumptiontracker.system.Universals;

public class Alc_Logging extends Fragment {

    public Alc_Logging() {
        super(R.layout.fragment_alc__logging);
    }


    @NonNull
    public static Alc_Logging newInstance() {
        Alc_Logging fragment = new Alc_Logging();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Instantiate fragment contents from fragment_alc__select to the parent container
        View root =  inflater.inflate(R.layout.fragment_alc__logging, container, false);

        return root;
    }
}
