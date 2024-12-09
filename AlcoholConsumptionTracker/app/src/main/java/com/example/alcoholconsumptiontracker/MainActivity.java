package com.example.alcoholconsumptiontracker;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.alcoholconsumptiontracker.system.DatabaseManager;
import com.example.alcoholconsumptiontracker.system.Drink;
import com.example.alcoholconsumptiontracker.system.DrinkTemplate;
import com.example.alcoholconsumptiontracker.system.DrinkTemplateManager;
import com.example.alcoholconsumptiontracker.ui.home.HomeFragment;
import com.example.alcoholconsumptiontracker.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.alcoholconsumptiontracker.databinding.ActivityMainBinding;
import java.util.HashMap;
import kotlinx.coroutines.MainCoroutineDispatcher;
import java.util.ArrayList;
import java.util.List;

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
    public static FragmentManager fragmentManager;

    /// Represents the host fragment (scene) ID
    ///     ID of fragment that contains fragments
    ///     within the main activity.
    public static int hostFragmentID;

    /// Represents the id of the current fragment
    ///     hosted within the host Fragment
    private static int currentFragmentID;

    /// Non-host fragments and their IDs
    ///     Fragments accessed from MainActivity displayed within the
    ///     host fragment of main activity.
    private static ArrayList<Integer> fragmentIds;

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
                    return MainActivity.ChangeActiveFragment(R.id.home_Fragment);
                }
                else if (itemID == R.id.nav_reporting){
                    return MainActivity.ChangeActiveFragment(R.id.view_Landing);
                }
                else{
                    return false;
                }
            }
        });


        // testing
        DrinkTemplate testTemplate = new DrinkTemplate();
        testTemplate.SetName("test name");
        drinkTemplateManager.PutTemplate(testTemplate);
        testTemplate = new DrinkTemplate();

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
        /////// end testing

        // Set the first scene to daily view
        MainActivity.ChangeActiveFragment(R.id.daily_View);

        // Set initialized to true
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
        MainActivity.fragmentIds = new ArrayList<>(20);
        fragmentIds.add(R.id.alc_Create_Edit);
        fragmentIds.add(R.id.home_Fragment);
        fragmentIds.add(R.id.alc_Select);
        fragmentIds.add(R.id.alc_Logging);
        fragmentIds.add(R.id.alc_Programming);
        fragmentIds.add(R.id.daily_View);
        fragmentIds.add(R.id.view_Landing);
        fragmentIds.add(R.id.monthly_View);
        fragmentIds.add(R.id.personal_Goals);
        fragmentIds.add(R.id.personal_Info);
        fragmentIds.add(R.id.weekly_View);
        fragmentIds.add(R.id.blank_fragment);

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
        if (MainActivity.fragmentIds == null){
            throw new RuntimeException("Fragment IDs Uninitialized.");
        }
        if (MainActivity.fragmentIds.contains(targetID)){
            // Return new instance of the fragment based on id
                if (targetID == R.id.alc_Create_Edit)
                    return Alc_Create_Edit.newInstance(null, null);
                else if (targetID == R.id.alc_Select)
                    return Alc_Select.newInstance();
                else if (targetID == R.id.alc_Programming)
                    return Alc_Programming.newInstance(null, null);
                else if (targetID == R.id.alc_Logging)
                    return Alc_Logging.newInstance();
                else if (targetID == R.id.daily_View)
                    return Daily_View.newInstance(null, null);
                else if (targetID == R.id.weekly_View)
                    return Weekly_View.newInstance(null, null);
                else if (targetID == R.id.monthly_View)
                    return Monthly_View.newInstance(null, null);
                else if (targetID == R.id.personal_Goals)
                    return Personal_Goals.newInstance(null, null);
                else if (targetID == R.id.personal_Info)
                    return new Personal_Info();
                else if (targetID == R.id.home_Fragment)
                    return HomeFragment.newInstance(null, null);
                else if (targetID == R.id.view_Landing)
                   return new NotificationsFragment();
                else
                    return new BlankFragment();
        }
        else{
            throw new RuntimeException("Index not found");
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
        if (MainActivity.fragmentIds == null) return false;
        if (!MainActivity.fragmentIds.contains(fragmentID)) return false;

        // Null warning found, but cannot be null at this point due to the previous
        //  if statements.
        MainActivity.fragmentManager.
                beginTransaction().
                replace(MainActivity.hostFragmentID, MainActivity.GetFragmentByID(fragmentID)).
                commitNowAllowingStateLoss();
        MainActivity.currentFragmentID = fragmentID;
        return true;
    }

    /// <summary>
    ///     Reloads the active fragment, resetting all data within it.
    /// </summary>
    public static boolean ReloadActiveFragment(){
        // If the fragment dictionary isn't initialized, return false.
        if (MainActivity.fragmentIds == null) return false;

        // Reload the active fragment
        MainActivity.fragmentManager.
                beginTransaction().
                replace(MainActivity.hostFragmentID, MainActivity.GetFragmentByID(MainActivity.currentFragmentID)).
                commitNowAllowingStateLoss();

        return true;
    }
}