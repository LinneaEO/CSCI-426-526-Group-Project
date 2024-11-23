package com.example.alcoholconsumptiontracker;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;


public class Daily_View extends Fragment {

    private LineChart lineChart;
    private TextView totalTextView;
    private Button buttonCalories, buttonUnits, buttonBAC, buttonMoney;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_daily__view, container, false);

        //  Get id and set the current date to the EditText
        EditText editTextDate = view.findViewById(R.id.editTextDate2);
        String currentDate = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(new Date());
        editTextDate.setText(currentDate);

        // Find views
        lineChart = view.findViewById(R.id.lineChart);
        totalTextView = view.findViewById(R.id.total_value);
        buttonCalories = view.findViewById(R.id.button_calories);
        buttonUnits = view.findViewById(R.id.button_units);
        buttonBAC = view.findViewById(R.id.button_bac);
        buttonMoney = view.findViewById(R.id.button_money);

        // Set up event listeners
        buttonCalories.setOnClickListener(v ->
            { setupLineChart(getCaloriesData(),"Calories", buttonCalories);
            });
        buttonUnits.setOnClickListener(v ->
            { setupLineChart(getUnitsData(), "Units", buttonUnits);
            });
        buttonBAC.setOnClickListener(v ->
            { setupLineChart(getBACData(), "BAC", buttonBAC);
            });
        buttonMoney.setOnClickListener(v ->
            { setupLineChart(getMoneyData(),"Money", buttonMoney);
            });

        // Use calorie line chart as default
        setupLineChart(getCaloriesData(),"Calories", buttonCalories);

        return view;
    }

    // Set up the LineChart
    private void setupLineChart(ArrayList<Entry> values, String label, Button activeButton) {
        LineDataSet lineDataSet = new LineDataSet(values, label);
        lineDataSet.setLineWidth(2f);
        lineDataSet.setCircleRadius(4f);
        lineDataSet.setColor(getResources().getColor(R.color.purple_500));
        lineDataSet.setCircleColor(getResources().getColor(R.color.teal_200));

        // Create a LineData object and set it to the chart
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);

        // Customize the X-axis for weekdays
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f); // One label per unit
        xAxis.setGranularityEnabled(true);
        xAxis.setValueFormatter(new Daily_View.dayValueFormatter()); // custom formatter
        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setEnabled(false);

        // Refresh the chart
        lineChart.invalidate();

        // Calculate total
        float total = calculateTotal(values);
        if (activeButton == buttonMoney){
            totalTextView.setText("Total " + label + ": $" + total);
            lineChart.getDescription().setText("Money spent today");
        }
        if (activeButton == buttonBAC){
            totalTextView.setText("Total " + label + ": %" + total);
            lineChart.getDescription().setText("BAC levels today");
        }
        if (activeButton == buttonCalories){
            totalTextView.setText("Total " + label + ": " + total);
            lineChart.getDescription().setText("Calories consumed today");
        }
        if (activeButton == buttonUnits){
            totalTextView.setText("Total " + label + ": #" + total);
            lineChart.getDescription().setText("Units drank today");
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

    private ArrayList<Entry> getCaloriesData() {
        ArrayList<Entry> values = new ArrayList<>();
        values.add(new Entry(1, 0));
        values.add(new Entry(2, 0));
        values.add(new Entry(3, 0));
        values.add(new Entry(4, 0));
        values.add(new Entry(5, 0));
        values.add(new Entry(6, 0));
        values.add(new Entry(7, 200));
        values.add(new Entry(8, 155));
        values.add(new Entry(9, 0));
        values.add(new Entry(10, 150));
        values.add(new Entry(11, 0));
        values.add(new Entry(12, 150));
        values.add(new Entry(13, 0));
        return values;
    }

    private ArrayList<Entry> getUnitsData() {
        ArrayList<Entry> values = new ArrayList<>();
        values.add(new Entry(1, 0));
        values.add(new Entry(2, 0));
        values.add(new Entry(3, 0));
        values.add(new Entry(4, 0));
        values.add(new Entry(5, 0));
        values.add(new Entry(6, 0));
        values.add(new Entry(7, 2));
        values.add(new Entry(8, 1));
        values.add(new Entry(9, 0));
        values.add(new Entry(10, 1.5F));
        values.add(new Entry(11, 0));
        values.add(new Entry(12, 1));
        values.add(new Entry(13, 0));
        return values;
    }

    private ArrayList<Entry> getBACData() {
        ArrayList<Entry> values = new ArrayList<>();
        values.add(new Entry(1, 0));
        values.add(new Entry(2, 0));
        values.add(new Entry(3, 0));
        values.add(new Entry(4, 0));
        values.add(new Entry(5, 0));
        values.add(new Entry(6, 0));
        values.add(new Entry(7, 0));
        values.add(new Entry(8, 0.11F));
        values.add(new Entry(9, 0));
        values.add(new Entry(10, 0.08F));
        values.add(new Entry(11, 0));
        values.add(new Entry(12, 0.12F));
        values.add(new Entry(13, 0));
        return values;
    }

    private ArrayList<Entry> getMoneyData() {
        ArrayList<Entry> values = new ArrayList<>();
        values.add(new Entry(1, 0));
        values.add(new Entry(2, 0));
        values.add(new Entry(3, 0));
        values.add(new Entry(4, 0));
        values.add(new Entry(5, 0));
        values.add(new Entry(6, 0));
        values.add(new Entry(7, 5.12F));
        values.add(new Entry(8, 10));
        values.add(new Entry(9, 0));
        values.add(new Entry(10, 0));
        values.add(new Entry(11, 23.99F));
        values.add(new Entry(12, 0));
        values.add(new Entry(13, 0));
        return values;
    }

    // Custom label values for x-axis
    private static class dayValueFormatter extends ValueFormatter {
        private final String[] weekdays = {"3pm", "4pm", "5pm", "6pm", "7pm", "8pm", "9pm", "10pm", "11pm", "12am", "1am", "2am", "3am"};

        @Override
        public String getFormattedValue(float value) {
            int index = (int) value - 1; // Convert value to index
            if (index >= 0 && index < weekdays.length) {
                return weekdays[index];
            }

            // Edge case
            else {
                return "";
            }
        }
    }

    private float calculateTotal(ArrayList<Entry> values) {
        float total = 0.0F;
        for (Entry entry : values) {
            total += entry.getY(); // Sum up the Y values
        }

        total = (Math.round(total * 100F) / 100F);

        return total;
    }

}