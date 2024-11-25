package com.example.alcoholconsumptiontracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.alcoholconsumptiontracker.system.DatabaseManager;
import com.example.alcoholconsumptiontracker.system.Drink;
import com.example.alcoholconsumptiontracker.system.DrinkTemplate;
import com.example.alcoholconsumptiontracker.system.DrinkTemplateManager;
import com.example.alcoholconsumptiontracker.system.Test;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.alcoholconsumptiontracker.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import kotlinx.coroutines.MainCoroutineDispatcher;

/// WARNING: Don't create more than one instance of MainActivity
public class MainActivity extends AppCompatActivity {

    ///
    /// Locals
    ///
    private ActivityMainBinding binding;

    ///
    ///  Globals
    ///
    // Represents whether the main activity globals have been initialized or not.
    private static boolean initialized = false;

    // Represents the content view
    private static View contentView;

    // Represents the bottomNavigation bar
    private static BottomNavigationView navView;

    /// Represents the fragment manager, an object used to
    /// switch between app scenes.
    private static FragmentManager fragmentManager;

    /// Represents the host fragment (scene) ID
    ///     ID of fragment that contains fragments
    ///     within the main activity.
    private static int hostFragmentID;

    /// Represents the id of the current fragment
    ///     hosted within the host Fragment
    private static int currentFragmentID;

    /// Non-host fragments and their IDs
    ///     Fragments accessed from MainActivity displayed within the
    ///     host fragment of main activity.
    private static HashMap<Integer, Fragment> fragmentDictionary;

    ///  Global DrinkTemplateManager
    private static DrinkTemplateManager drinkTemplateManager;

    /// Global Temporary DrinkList (later to be given to date system)
    private static List<Drink> drinkList;


    ///  Global DatabaseManager
    private static DatabaseManager databaseManager;

    ///
    ///  Constructors* (methods ran on creation)
    ///
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ///
        /// Window Requests
        ///     *Called before adding content
        ///
        // Remove action Bar
        getSupportActionBar().hide();


        ///
        /// Set content of Main Activity
        ///
        // Set contents of main
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        MainActivity.contentView = binding.getRoot();


        // Initialize global fragment components
        this.CreateHelperInitializeFragmentObjects();

        // Initialize global DatabaseManager
        this.CreateHelperInitializeDatabaseManager();

        // Initialize global DrinkTemplateManager
        this.CreateHelperInitializeDrinkTemplateManager();

        // Initialize global DrinkList (temporary)
        MainActivity.drinkList = new ArrayList<Drink>();


        // Initialize the bottom navigation menu. Set the home screen as daily_View
        //  Initialize bottom navigation menu listeners.
        MainActivity.navView = findViewById(R.id.nav_view);
        MainActivity.navView.setSelectedItemId(R.id.navigation_home);
        MainActivity.navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                int itemID = item.getItemId();
                if (itemID == R.id.nav_logging){
                    return MainActivity.ChangeActiveFragment(R.id.alc_Select);
                }
                else if (itemID == R.id.navigation_home){
                    return MainActivity.ChangeActiveFragment(R.id.daily_View);
                }
                else if (itemID == R.id.nav_reporting){
                    return MainActivity.ChangeActiveFragment(R.id.weekly_View);
                }
                else{
                    return false;
                }
            }
        });

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        /*
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.alc_Select, R.id.daily_View, R.id.weekly_View)
                .build();
        NavController navigationController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navigationController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navigationController);
        */

<<<<<<< HEAD
        DrinkTemplate testTemplate = new DrinkTemplate();
        testTemplate.SetName("test name");
        drinkTemplateManager.PutTemplate(testTemplate);
        testTemplate.SetName("a gin n rum matie");
        testTemplate.SetType((short)2);
        testTemplate.SetServings((short)1);
        testTemplate.SetCalories((float)400);
        testTemplate.SetPrice((float)1000000.98);

        drinkTemplateManager.PutTemplate(testTemplate);
        testTemplate = new DrinkTemplate();

        testTemplate.SetName("test name 3");

        drinkTemplateManager.PutTemplate(testTemplate);
        testTemplate = new DrinkTemplate();

        testTemplate.SetName("test name 4");

        drinkTemplateManager.PutTemplate(testTemplate);
        testTemplate = new DrinkTemplate();

        testTemplate.SetName("test name 5");

        drinkTemplateManager.PutTemplate(testTemplate);
        testTemplate = new DrinkTemplate();

        testTemplate.SetName("test name 6");

        drinkTemplateManager.PutTemplate(testTemplate);
        testTemplate = new DrinkTemplate();

        testTemplate.SetName("test name 7");

        drinkTemplateManager.PutTemplate(testTemplate);
        testTemplate = new DrinkTemplate();

        testTemplate.SetName("test name 8");
        drinkTemplateManager.PutTemplate(testTemplate);

        MainActivity.initialized = true;

    }
    ///
    /// Constructor helpers
    ///
    /// <summary>
    ///  Initializes fragment IDs, fragment dictionary, and fragment manager
    /// </summary>
    private void CreateHelperInitializeFragmentObjects(){
        // Initialize the support fragment manager
        MainActivity.fragmentManager = getSupportFragmentManager();

        // Initialize host fragment ID
        hostFragmentID = R.id.mainActivityFragment;
        currentFragmentID = hostFragmentID;

        // Initialize dictionary
        // Initialize non-host fragment IDs in Dictionary
        //
        MainActivity.fragmentDictionary = new HashMap<>(20);
        fragmentDictionary.put(R.id.alc_Edit, null);
        fragmentDictionary.put(R.id.alc_Select, null);
        fragmentDictionary.put(R.id.alc_Create, null);
        fragmentDictionary.put(R.id.alc_Logging, null);
        fragmentDictionary.put(R.id.alc_Programming, null);
        fragmentDictionary.put(R.id.daily_View, null);
        fragmentDictionary.put(R.id.monthly_View, null);
        fragmentDictionary.put(R.id.personal_Goals, null);
        fragmentDictionary.put(R.id.personal_Info, null);
        fragmentDictionary.put(R.id.weekly_View, null);
=======

        Intent intent = new Intent(this, Alc_Select.class);
        this.startActivity(intent);
>>>>>>> e57f1641fa7028d4f01637296e9aeb298859512b
    }

    /// <summary>
    ///    Initializes the global database manager
    /// </summary>
    private void CreateHelperInitializeDatabaseManager(){
        MainActivity.databaseManager = new DatabaseManager(this.getApplicationContext());
    }

    /// <summary>
    ///     Initializes the global drink template manager
    /// </summary>
    private void CreateHelperInitializeDrinkTemplateManager(){
        MainActivity.drinkTemplateManager = new DrinkTemplateManager();
    }

    ///
    /// Getters and Setters
    ///
    public static Fragment GetFragmentByID(int targetID){
        if (MainActivity.fragmentDictionary == null){
            throw new RuntimeException("Fragment Dictionary Uninitialized.");
        }
        if (MainActivity.fragmentDictionary.containsKey(targetID)){

            // If the fragment is uninitialized, initialize it before returning
            if (MainActivity.fragmentDictionary.get(targetID) == null){
                if (targetID == R.id.alc_Create){
                    MainActivity.fragmentDictionary.put(targetID, Alc_Create.newInstance(null, null));
                }
                else if (targetID == R.id.alc_Edit){
                    MainActivity.fragmentDictionary.put(targetID, Alc_Edit.newInstance(null, null));
                }
                else if (targetID == R.id.alc_Select){
                    MainActivity.fragmentDictionary.put(targetID, Alc_Select.newInstance());
                }
                else if (targetID == R.id.alc_Programming){
                    MainActivity.fragmentDictionary.put(targetID, Alc_Programming.newInstance(null, null));
                }
                else if (targetID == R.id.alc_Logging){
                    MainActivity.fragmentDictionary.put(targetID, Alc_Logging.newInstance());
                }
                else if (targetID == R.id.daily_View){
                    MainActivity.fragmentDictionary.put(targetID, Daily_View.newInstance(null, null));
                }
                else if (targetID == R.id.monthly_View){
                    MainActivity.fragmentDictionary.put(targetID, Monthly_View.newInstance(null, null));
                }
                else if (targetID == R.id.personal_Goals){
                    MainActivity.fragmentDictionary.put(targetID, Personal_Goals.newInstance(null, null));
                }
                else if (targetID == R.id.personal_Info){
                    MainActivity.fragmentDictionary.put(targetID, Personal_Info.newInstance(null, null));
                }
                else if (targetID == R.id.weekly_View){
                    MainActivity.fragmentDictionary.put(targetID, Weekly_View.newInstance(null, null));
                }
            }
            return MainActivity.fragmentDictionary.get(targetID);
        }
        else{
            throw new RuntimeException("Index not found");
        }
    }
    public static void SetFragmentByID(int targetID, Fragment newValue){
        if (MainActivity.fragmentDictionary == null){
            throw new RuntimeException("Fragment Dictionary Uninitialized.");
        }
        if (MainActivity.fragmentDictionary.containsKey(targetID)){
            MainActivity.fragmentDictionary.replace(targetID, newValue);
        }
        else{
            MainActivity.fragmentDictionary.put(targetID, newValue);
        }
    }
    public static boolean Initialized(){
        return MainActivity.initialized;
    }
    public static View GetContentView(){
        if (MainActivity.contentView == null) throw new RuntimeException("Content view uninitialized");
        else return MainActivity.contentView;
    }
    public static BottomNavigationView GetNavView(){
        if (MainActivity.navView == null) throw new RuntimeException("NavView uninitialized");
        else return MainActivity.navView;
    }
    public static DatabaseManager GetDatabaseManager(){
        if (!MainActivity.databaseManager.Initialized() || !MainActivity.initialized)
            throw new RuntimeException("Database uninitialized.");
        else return MainActivity.databaseManager;
    }
    public static DrinkTemplateManager GetDrinkTemplateManager(){
        if (!MainActivity.initialized){
            throw new RuntimeException("Drink Template Manager uninitialized.");
        }
        else return MainActivity.drinkTemplateManager;
    }

    // Gets the global drink list
    public static List<Drink> GetDrinkList(){
        return MainActivity.drinkList;
    }
    /// <summary>
    ///     Puts a drink in the global drink list.
    ///     Returns:
    ///         True if successful
    ///         False otherwise
    /// </summary>
    public static boolean PutDrinkInDrinkList(Drink newDrink){
        try{
            MainActivity.drinkList.add(newDrink);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    /// <summary>
    ///     Removes a drink from the global drink list by index.
    ///     Returns:
    ///         True if successful
    ///         False otherwise
    /// </summary>
    public static boolean RemoveDrinkFromDrinkList(int index){
        // Check for out of range
        if (index >= MainActivity.drinkList.size() || index < 0){
            return false;
        }
        try{
            MainActivity.drinkList.remove(index);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    ///
    ///  Methods
    ///
    /// <summary>
    ///  Given an inputted fragment, this method tries to change the
    ///     active fragment of MainActivity with the inputted fragment.
    ///  Returns:
    ///     True if successful
    ///     False otherwise
    /// </summary>
    public static boolean ChangeActiveFragment(int fragmentID){

        // If the fragment dictionary isn't initialized, if the dictionary doesn't contain the
        //  target fragment, or if the fragment is set to null, return false.
        if (MainActivity.fragmentDictionary == null) return false;
        if (!MainActivity.fragmentDictionary.containsKey(fragmentID)) return false;

        // Null warning found, but cannot be null at this point due to the previous
        //  if statements.
        MainActivity.fragmentManager.
                beginTransaction().
                replace(R.id.mainActivityFragment, MainActivity.GetFragmentByID(fragmentID)).
                commitNow();
        MainActivity.currentFragmentID = fragmentID;
        return true;
    }
}