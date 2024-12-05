package com.example.alcoholconsumptiontracker;

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

    private static EditText nameInput;

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

        Personal_Info.nameInput = root.findViewById(R.id.edittextnameid);
        gearIcon = root.findViewById(R.id.geariconid);

        nameInput.setEnabled(false);

        gearIcon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                nameInput.setEnabled(true);
            }
        });
        return root;
    }
}