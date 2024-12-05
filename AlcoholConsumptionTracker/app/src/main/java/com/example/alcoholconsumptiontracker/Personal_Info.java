package com.example.alcoholconsumptiontracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Personal_Info extends Fragment {

    public static EditText nameInput;
    public static EditText ageInput;
    public static EditText sexInput;
    public static EditText weightInput;
    public static EditText heightInput;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_personal__info, container, false);
        ImageButton gearIcon = null;
        ImageButton finishIcon = null;

        Personal_Info.nameInput = root.findViewById(R.id.edittextnameid);
        Personal_Info.ageInput = root.findViewById(R.id.edittextageid);
        Personal_Info.sexInput = root.findViewById(R.id.edittextsexid);
        Personal_Info.weightInput = root.findViewById(R.id.edittextweightid);
        Personal_Info.heightInput = root.findViewById(R.id.edittextheightid);



        gearIcon = root.findViewById(R.id.geariconid);
        finishIcon = root.findViewById(R.id.finishbuttonid);

        nameInput.setEnabled(false);
        ageInput.setEnabled(false);
        sexInput.setEnabled(false);
        weightInput.setEnabled(false);
        heightInput.setEnabled(false);


        gearIcon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                nameInput.setEnabled(true);
                ageInput.setEnabled(true);
                sexInput.setEnabled(true);
                weightInput.setEnabled(true);
                heightInput.setEnabled(true);
            }
        });

        finishIcon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                nameInput.setEnabled(false);
                ageInput.setEnabled(false);
                sexInput.setEnabled(false);
                weightInput.setEnabled(false);
                heightInput.setEnabled(false);



                String newName = String.valueOf(nameInput.getText());


            }
        });
        return root;
    }
}