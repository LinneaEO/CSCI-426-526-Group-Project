package com.example.alcoholconsumptiontracker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.alcoholconsumptiontracker.system.DrinkTemplate;
import com.example.alcoholconsumptiontracker.system.DrinkType;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Alc_Edit#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Alc_Edit extends Fragment {


    ///
    ///  Globals
    ///
    // Represents the template being edited
    private static DrinkTemplate templateEditing;

    // Represents the template image of the template
    private static ImageView templateImage;

    // Represents the template image change button
    private static ImageButton templateChangeImageButton;

    // Represents the template name text entry
    private static EditText templateNameTextbox;
    // Represents the original name of the template before editing
    private static String originalTemplateName;

    // Represents the template servings text entry
    private static EditText templateServingsTextbox;

    // Represents the template type text entry
    private static AutoCompleteTextView templateTypeAutoTextbox;

    // Represents the template calories text entry
    private static EditText templateCaloriesTextbox;

    // Represents the template price text entry
    private static EditText templatePriceTextbox;

    // Represents the finish editing button
    private static ImageButton alcEditFinishEditingButton;

    // Represents the cancel button
    private static ImageButton alEditCancelEditButton;


    public Alc_Edit() {
        // Required empty public constructor
    }

    public static Alc_Edit newInstance(String param1, String param2) {
        Alc_Edit fragment = new Alc_Edit();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        View root = inflater.inflate(R.layout.fragment_alc__edit, container, false);


        // Reset globals to null
        Alc_Edit.templateEditing = null;
        Alc_Edit.templateImage = null;
        Alc_Edit.templateChangeImageButton = null;
        Alc_Edit.templateNameTextbox = null;
        Alc_Edit.templateServingsTextbox = null;
        Alc_Edit.templateTypeAutoTextbox = null;
        Alc_Edit.templateCaloriesTextbox = null;
        Alc_Edit.templatePriceTextbox = null;
        Alc_Edit.alcEditFinishEditingButton = null;
        Alc_Edit.alEditCancelEditButton = null;

        // Set up drink template (pull from alc programming)
        Alc_Edit.templateEditing = Alc_Programming.GetSelectedTemplate();
        //  -Fail case. If no template was found, create a blank new one.
        if (Alc_Edit.templateEditing == null){
            Alc_Edit.templateEditing = new DrinkTemplate();
            Alc_Edit.templateEditing.SetName("Default Name");
            Alc_Edit.templateEditing.SetServings((short)1);
        }
        Alc_Edit.originalTemplateName = Alc_Edit.templateEditing.GetName();

        // Set up template name textbox
        Alc_Edit.templateNameTextbox = root.findViewById(R.id.alcEditName);
        Alc_Edit.templateNameTextbox.setText(String.valueOf(Alc_Edit.GetEditingTemplate().GetName()));
        Alc_Edit.templateNameTextbox.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (!hasFocus){
                            EditText nameNextBox = (EditText) v;
                            String newName = String.valueOf(nameNextBox.getText());
                            // If there is no change in name, return
                            if (newName.equals(Alc_Edit.GetEditingTemplate().GetName())){
                                return;
                            }
                            // If the name is contained within another template, return and reset the template name
                            //  to what it was previously.
                            if (MainActivity.GetDrinkTemplateManager().ContainsTemplate(newName)){
                                Toast.makeText(
                                        MainActivity.GetContentView().getContext(),
                                        "Template name already exists",
                                        Toast.LENGTH_LONG
                                ).show();
                                nameNextBox.setText(Alc_Edit.OriginalEditingTemplateName());
                                return;
                            }

                            // If the name isn't contained and not a repeat name, change the name
                            //  of the template.
                            DrinkTemplate newTemplate = Alc_Edit.GetEditingTemplate();
                            newTemplate.SetName(newName);
                            Alc_Edit.SetEditingTemplate(newTemplate);
                        }
                    }
                }
        );

        // Set up template servings textbox
        Alc_Edit.templateServingsTextbox = root.findViewById(R.id.alcEditServings);
        Alc_Edit.templateServingsTextbox.setText(String.valueOf(Alc_Edit.GetEditingTemplate().GetServings()));
        Alc_Edit.templateServingsTextbox.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (!hasFocus){
                            EditText nameNextBox = (EditText) v;
                            short newServings = 1;
                            try{
                                newServings = Short.parseShort(String.valueOf(nameNextBox.getText()));
                                if (newServings < 1){
                                    Toast.makeText(
                                            MainActivity.GetContentView().getContext(),
                                            "Minimum of 1 serving(s)",
                                            Toast.LENGTH_LONG
                                    ).show();
                                    newServings = 1;
                                }
                            } catch (NumberFormatException e) {
                                newServings = 1;
                            }

                            // Set the servings of the template
                            DrinkTemplate newTemplate = Alc_Edit.GetEditingTemplate();
                            newTemplate.SetServings(newServings);
                            Alc_Edit.SetEditingTemplate(newTemplate);
                        }
                    }
                }
        );

        // Set up template calories textbox
        Alc_Edit.templateCaloriesTextbox = root.findViewById(R.id.alcEditCalories);
        Alc_Edit.templateCaloriesTextbox.setText(String.valueOf(Alc_Edit.GetEditingTemplate().GetCalories()));
        Alc_Edit.templateCaloriesTextbox.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (!hasFocus){
                            EditText nameNextBox = (EditText) v;
                            float newCalories = 0;
                            try{
                                newCalories = Short.parseShort(String.valueOf(nameNextBox.getText()));
                                if (newCalories < 0){
                                    Toast.makeText(
                                            MainActivity.GetContentView().getContext(),
                                            "Minimum of 0 calories",
                                            Toast.LENGTH_LONG
                                    ).show();
                                    newCalories = 0;
                                }
                            } catch (NumberFormatException e) {
                                newCalories = 0;
                            }

                            // Set the servings of the template
                            DrinkTemplate newTemplate = Alc_Edit.GetEditingTemplate();
                            newTemplate.SetCalories(newCalories);
                            Alc_Edit.SetEditingTemplate(newTemplate);
                        }
                    }
                }
        );

        // Set up template price textbox
        Alc_Edit.templatePriceTextbox = root.findViewById(R.id.alcEditPrice);
        Alc_Edit.templatePriceTextbox.setText(String.valueOf(Alc_Edit.GetEditingTemplate().GetPrice()));
        Alc_Edit.templatePriceTextbox.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (!hasFocus){
                            EditText nameNextBox = (EditText) v;
                            float newPrice = 0;
                            try{
                                newPrice = Short.parseShort(String.valueOf(nameNextBox.getText()));
                                if (newPrice < 0){
                                    Toast.makeText(
                                            MainActivity.GetContentView().getContext(),
                                            "Minimum of 0 dollars",
                                            Toast.LENGTH_LONG
                                    ).show();
                                    newPrice = 0;
                                }
                            } catch (NumberFormatException e) {
                                newPrice = 0;
                            }

                            // Set the servings of the template
                            DrinkTemplate newTemplate = Alc_Edit.GetEditingTemplate();
                            newTemplate.SetPrice(newPrice);
                            Alc_Edit.SetEditingTemplate(newTemplate);
                        }
                    }
                }
        );

        // Set up template type box
        Alc_Edit.templateTypeAutoTextbox = root.findViewById(R.id.alcEditTypeInputTextbox);
        String[] drinkTypes = DrinkType.DrinkTypeNames();
        ArrayAdapter adapter = new ArrayAdapter(
                MainActivity.GetContentView().getContext(),
                R.layout.alc_edit_create_dropdown_type,
                drinkTypes);
        Alc_Edit.templateTypeAutoTextbox.setAdapter(adapter);
        Alc_Edit.templateTypeAutoTextbox.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (!hasFocus){
                            AutoCompleteTextView typeTextBox = (AutoCompleteTextView) v;
                            String newType = String.valueOf(typeTextBox.getText());

                            // Set the servings of the template
                            DrinkTemplate newTemplate = Alc_Edit.GetEditingTemplate();
                            newTemplate.SetType(DrinkType.DrinkTypeFromString(newType));
                            Alc_Edit.SetEditingTemplate(newTemplate);
                        }
                    }
                }
        );

        return root;
    }

    ///
    /// Getters and Setters
    ///
    /// <summary>
    ///     Represents the template being edited's name
    ///     before editing began.
    /// </summary>
    public static String OriginalEditingTemplateName(){
        return Alc_Edit.originalTemplateName;
    }

    /// <summary>
    ///     Represents the template being edited
    /// </summary>
    public static DrinkTemplate GetEditingTemplate(){
        return Alc_Edit.templateEditing;
    }
    public static void SetEditingTemplate(DrinkTemplate newTemplate){
        Alc_Edit.templateEditing = newTemplate;
    }
}