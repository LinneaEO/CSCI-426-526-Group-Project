package com.example.alcoholconsumptiontracker;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Monthly_View#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Monthly_View extends Fragment {

    private TextView textViewMonth;
    private GridLayout gridLayoutCalendar;
    private Button buttonCalories, buttonUnits, buttonBAC, buttonMoney;
    private String activeDataset = "calories";

    public Monthly_View() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Monthly_View.
     */
    // TODO: Rename and change types and number of parameters
    public static Monthly_View newInstance(String param1, String param2) {
        Monthly_View fragment = new Monthly_View();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_monthly__view, container, false);

        // Find views
        TextView textViewMonth = view.findViewById(R.id.textViewMonth);
        gridLayoutCalendar = view.findViewById(R.id.gridLayoutCalendar);
        buttonCalories = view.findViewById(R.id.button_calories_m);
        buttonUnits = view.findViewById(R.id.button_units_m);
        buttonBAC = view.findViewById(R.id.button_bac_m);
        buttonMoney = view.findViewById(R.id.button_money_m);

        // Set current month
        String currentMonth = getCurrentMonth();
        textViewMonth.setText(currentMonth);

        // Default table view
        populateCalendar(getCaloriesData(), buttonCalories);

        // Button listeners
        buttonCalories.setOnClickListener(v -> {
            activeDataset = "calories";
            populateCalendar(getCaloriesData(), buttonCalories);
        });

        buttonUnits.setOnClickListener(v -> {
            activeDataset = "units";
            populateCalendar(getUnitsData(), buttonUnits);
        });

        buttonBAC.setOnClickListener(v -> {
            activeDataset = "bac";
            populateCalendar(getBACData(), buttonBAC);
        });

        buttonMoney.setOnClickListener(v -> {
            activeDataset = "money";
            populateCalendar(getMoneyData(), buttonMoney);
        });

        return view;
    }

    // Gets the current month
    private String getCurrentMonth() {
        Calendar calendar = Calendar.getInstance(); // Get the current date
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", Locale.getDefault());
        return monthFormat.format(calendar.getTime());
    }

    // Populate the calendar table with data
    private void populateCalendar(float[] data, Button activeButton) {
        gridLayoutCalendar.removeAllViews(); // Clear existing cells

        Calendar calendar = Calendar.getInstance();
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int cellWidth = 150;
        int cellHeight = 150;

        // Add cells to the GridLayout
        for (int day = 1; day <= daysInMonth; day++) {
            TextView textViewCell = new TextView(getContext());
            textViewCell.setText(String.valueOf(day) + "\n" + data[day - 1]);
            textViewCell.setPadding(12, 12, 12, 12);
            textViewCell.setGravity(Gravity.CENTER);

            GradientDrawable cellBackground = new GradientDrawable();
            cellBackground.setColor(Color.WHITE); // Set background color
            cellBackground.setStroke(2, Color.GRAY); // Add a gray border
            cellBackground.setCornerRadius(8); // Rounded corners
            textViewCell.setBackground(cellBackground);


            // TODO: set threshold to goal value
            if ((activeDataset.equals("calories") && data[day - 1] > 300) ||
                    (activeDataset.equals("units") && data[day - 1] > 3) ||
                    (activeDataset.equals("bac") && data[day - 1] > 0.08) ||
                    (activeDataset.equals("money") && data[day - 1] > 20)) {
                textViewCell.setBackgroundResource(R.drawable.red_cell_background);
            } else if ((activeDataset.equals("calories") && data[day - 1] < 100) ||
                    (activeDataset.equals("units") && data[day - 1] <= 1) ||
                    (activeDataset.equals("bac") && data[day - 1] <= 0.02) ||
                    (activeDataset.equals("money") && data[day - 1] <= 10)) {
                textViewCell.setBackgroundResource(R.drawable.green_cell_background); // Highlight cell in green
            } else {
                textViewCell.setBackgroundResource(R.drawable.cell_background);
            }



            // Grid layout
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = cellWidth;
            params.height = cellHeight;
            textViewCell.setLayoutParams(params);

            gridLayoutCalendar.addView(textViewCell);
        }

        highlightActiveButton(activeButton);
    }

    private void highlightActiveButton(Button highlightButton){
        // Set all buttons back to default color
        buttonCalories.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.default_button));
        buttonUnits.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.default_button));
        buttonBAC.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.default_button));
        buttonMoney.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.default_button));

        // Highlight active button
        highlightButton.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.active_button));
    }

    // Example data for Calories
    private float[] getCaloriesData() {
        return new float[]{200, 250, 300, 150, 0, 100, 50, 300, 400, 350, 200, 0, 250, 100, 50, 200, 150, 300, 400, 350, 200, 250, 300, 150, 0, 100, 50, 300, 400, 350, 200};
    }

    // Example data for Units
    private float[] getUnitsData() {
        return new float[]{2, 1, 3, 0, 0, 1, 1, 4, 3, 2, 0, 0, 2, 3, 1, 2, 1, 0, 1, 2, 3, 0, 0, 1, 1, 4, 3, 2, 0, 0, 2};
    }

    // Example data for BAC
    private float[] getBACData() {
        return new float[]{0.05f, 0.06f, 0.08f, 0, 0, 0.07f, 0.11f, 0.06f, 0.08f, 0, 0, 0.07f, 0.05f, 0.06f, 0.12f, 0, 0, 0.07f, 0.05f, 0.06f, 0.08f, 0, 0, 0.07f, 0.05f, 0.06f, 0.08f, 0, 0, 0.13f, 0};
    }

    // Example data for Money
    private float[] getMoneyData() {
        return new float[]{10, 12, 15, 8, 0, 5, 7, 25, 15, 10, 8, 0, 5, 7, 10, 12, 15, 8, 0, 5, 7, 22, 15, 10, 8, 0, 5, 7, 10, 12, 15};
    }

}