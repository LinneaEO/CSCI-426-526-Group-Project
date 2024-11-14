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

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;


public class Daily_View extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_daily__view, container, false);

        //  Get id and set the current date to the EditText
        EditText editTextDate = view.findViewById(R.id.editTextDate2);
        String currentDate = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(new Date());
        editTextDate.setText(currentDate);

        // Set up line chart data
        LineChart lineChart = view.findViewById(R.id.lineChart);
        setupLineChart(lineChart);

        return view;
    }

    private void setupLineChart(LineChart lineChart) {
        // Sample data for the line chart
        ArrayList<Entry> values = new ArrayList<>();
        values.add(new Entry(1, 0));
        values.add(new Entry(2, 0));
        values.add(new Entry(3, 0));
        values.add(new Entry(4, 0));
        values.add(new Entry(5, 0));
        values.add(new Entry(6, 0));
        values.add(new Entry(7, 200));
        values.add(new Entry(8, 150));
        values.add(new Entry(9, 0));
        values.add(new Entry(10, 150));
        values.add(new Entry(11, 0));
        values.add(new Entry(12, 150));

        // Create a dataset and style it
        LineDataSet lineDataSet = new LineDataSet(values, "Daily Calories");
        lineDataSet.setLineWidth(2f);
        lineDataSet.setCircleRadius(4f);
        lineDataSet.setColor(getResources().getColor(R.color.purple_500));
        lineDataSet.setCircleColor(getResources().getColor(R.color.teal_200));

        // Add the dataset to a list of datasets
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);

        // Create a LineData object and set it to the chart
        LineData lineData = new LineData(dataSets);
        lineChart.setData(lineData);

        // Customize the X and Y axes
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f); // one unit interval
        xAxis.setGranularityEnabled(true);

        YAxis leftAxis = lineChart.getAxisLeft();
        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setEnabled(false); // Hide the right Y-axis

        // Refresh the chart
        lineChart.invalidate();
    }

}