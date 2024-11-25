package com.example.alcoholconsumptiontracker;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import androidx.fragment.app.Fragment;

import com.example.alcoholconsumptiontracker.system.Drink;
import com.example.alcoholconsumptiontracker.system.DrinkTemplate;
import com.example.alcoholconsumptiontracker.system.Universals;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Alc_Logging extends Fragment {

    ///
    ///  Locals
    ///

    ///
    /// Globals
    ///
    // Represents the list of fields of the selected template
    private static ListView selectedTemplateFieldList;

    // Represents the textbox where drink occasion is entered
    private static EditText drinkOccasionTextbox;

    // Represents the button that triggers a time dialog to set the time of drink consumption
    private static Button drinkTimeOfDrinkDialogButton;

    // Represents the button that adds logs a drink based on the selected template, time chosen, and occasion
    private static Button addOneDrink;

    // Represents the textview that contains the time of consumption
    private static TextView drinkTimeOfDrinkText;
    private static int drinkMinute;
    private static int drinkHour;

    // Represents an image of the selected template
    private static ImageView selectedTemplateImage;

    // Represents the template received from alcohol select
    private static DrinkTemplate selectedTemplate;


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

        // Set globals to null (reset)
        Alc_Logging.selectedTemplateFieldList = null;
        Alc_Logging.selectedTemplateImage = null;
        Alc_Logging.selectedTemplate = null;
        Alc_Logging.drinkOccasionTextbox = null;
        Alc_Logging.drinkTimeOfDrinkDialogButton = null;
        Alc_Logging.drinkTimeOfDrinkText = null;

        // Get the drink template list item to populate with template content
        Alc_Logging.selectedTemplateFieldList = root.findViewById(R.id.drinkLoggingDrinkInformation);

        // Get the selected template from alcohol select (should not be null at this point)
        Alc_Logging.selectedTemplate = Alc_Select.GetSelectedTemplate();
        if (selectedTemplate != null){

            // Populate list based on template information
            List<String> templateFields = new ArrayList<String>();
            templateFields.add(
                    Universals.DrinkLoggingUI.DrinkTemplateTags.name +
                    selectedTemplate.GetName()
            );
            templateFields.add(
                    Universals.DrinkLoggingUI.DrinkTemplateTags.servings +
                    Short.toString(selectedTemplate.GetServings())
            );
            templateFields.add(
                    Universals.DrinkLoggingUI.DrinkTemplateTags.type +
                    selectedTemplate.GetType().Get()
            );
            templateFields.add(
                    Universals.DrinkLoggingUI.DrinkTemplateTags.price +
                    Float.toString(selectedTemplate.GetPrice())
            );
            templateFields.add(
                    Universals.DrinkLoggingUI.DrinkTemplateTags.calories +
                    Float.toString(selectedTemplate.GetCalories())
            );

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    getContext(),
                    R.layout.alc_logging_list_item,
                    templateFields
            );
            Alc_Logging.selectedTemplateFieldList.setAdapter(adapter);
        }

        // Set up the occasion textbox
        Alc_Logging.drinkOccasionTextbox = root.findViewById((R.id.drinkLoggingOccassionInput));
        Alc_Logging.drinkOccasionTextbox.setText("");
        Alc_Logging.drinkOccasionTextbox.setTextColor(Alc_Logging.PrimaryLoggingTextColor());
        Alc_Logging.drinkOccasionTextbox.setHint(Universals.DrinkLoggingUI.UITags.OccasionDefaultText);//display the hint
        Alc_Logging.drinkOccasionTextbox.setSingleLine(false);
        Alc_Logging.drinkOccasionTextbox.setVerticalScrollBarEnabled(true);
        Alc_Logging.drinkOccasionTextbox.setHorizontallyScrolling(false);

        // Set up the time of drink textbox
        Alc_Logging.drinkTimeOfDrinkText = root.findViewById(R.id.alcLoggingDrinkTime);
        Date currentTime = Calendar.getInstance().getTime();
        Alc_Logging.drinkHour = currentTime.getHours();
        Alc_Logging.drinkMinute = currentTime.getMinutes();
        Alc_Logging.drinkTimeOfDrinkText.setText(FormatTimeString(Alc_Logging.drinkHour, Alc_Logging.drinkMinute));

        // Set up the time of drink button
        Alc_Logging.drinkTimeOfDrinkDialogButton = root.findViewById(R.id.alcLoggingTimeSelectorButton);
        Alc_Logging.drinkTimeOfDrinkDialogButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener(
                        ) {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                            }
                        };
                        MaterialTimePicker picker = new MaterialTimePicker.Builder().
                                setHour(Alc_Logging.drinkHour).
                                setMinute(Alc_Logging.drinkMinute).
                                setInputMode(MaterialTimePicker.INPUT_MODE_KEYBOARD).
                                setTimeFormat(TimeFormat.CLOCK_12H).
                                build();
                        picker.addOnPositiveButtonClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Alc_Logging.drinkMinute = picker.getMinute();
                                Alc_Logging.drinkHour = picker.getHour();
                                Alc_Logging.drinkTimeOfDrinkText.setText(FormatTimeString(Alc_Logging.drinkHour, Alc_Logging.drinkMinute));
                            }
                        });
                        picker.show(getActivity().getSupportFragmentManager(), "Select Time of Drink");
                    }
                }
        );

        // Set up the log drink button
        Alc_Logging.addOneDrink = root.findViewById(R.id.alcLoggingAddOneDrink);
        // When the button is pressed, add one drink which is stored to the daily log
        Alc_Logging.addOneDrink.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Drink loggedDrink = Alc_Logging.selectedTemplate.ProduceDrink(
                                String.valueOf(Alc_Logging.drinkOccasionTextbox.getText()),
                                (short)Alc_Logging.drinkHour,
                                (short)Alc_Logging.drinkMinute
                        );
                        MainActivity.PutDrinkInDrinkList(loggedDrink);
                    }

                }
        );

        return root;
    }

    ///
    /// Class colors
    ///
    private static int PrimaryLoggingTextColor(){
        return Color.parseColor("#000000");
    }
    private static int SecondaryLoggingTextColor(){ return Color.parseColor("#666666");}

    /// <summary>
    /// Given hours and minutes, this method produces a string in the form:
    ///     HH:MM A/P
    /// where HH is hours (0-23)
    /// where MM is minutes (0-59)
    /// where A/P is AM or PM
    /// </summary>
    private static String FormatTimeString(int hours, int minutes){

        String hoursToken = "";
        String minutesToken = "";
        String apToken = "";

        // Hours token
        if (hours < 0 || hours > 23){
            hoursToken = "-1";
        }
        else if (hours > 11){
            hours = hours - 12;
            if (hours < 10){
                hoursToken += "0";
            }
            hoursToken += Integer.toString(hours);
            apToken = "PM";
        }
        else{
            if (hours < 10){
                hoursToken += "0";
            }
            hoursToken += Integer.toString(hours);
            apToken = "AM";
        }


        // Minutes token
        if (minutes < 0 || minutes > 59){
            minutesToken = "-1";
        }
        else{
            if (minutes < 10){
                minutesToken += "0";
            }
            minutesToken += Integer.toString(minutes);
        }

        // Return formatted string
        return hoursToken + ":" + minutesToken + " " + apToken;
    }
}
