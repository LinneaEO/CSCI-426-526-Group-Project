/// <summary>
///  File: Drink.java
///     Description: Meant to contain the object representing a logged Drink and its methods
/// </summary>

package com.example.alcoholconsumptiontracker.system;

import android.content.Intent;

import java.time.LocalTime;

public class Drink {

    ///
    /// Local variables
    ///

    /// These locals have a 1 to 1 correspondence with Drink Template
    //
    private String name; // Name of drink
    private DrinkType type; // Type of drink
    private short servings; // Number of servings ( 0 >= )
    private float aPV; // Alcohol Per volume ( 0.0 >= )
    private float price; // Price ( 0.0 >= , dollars USD)
    private Intent image; // Image from gallery
    // End corresponding locals

    private String occassion; // Occasion for drinking
    private short hourOfConsumption; // Time drink was drunk in the form hour, minute. Military time
    private short minuteOfConsumption;
    //


    ///
    /// Constructors
    ///
    // Default
    Drink(){
        this.name = Universals.General.EmptyString();
        this.type = new DrinkType();
        this.servings = 0;
        this.aPV = 0;
        this.price = 0;
        this.image = new Intent();
        this.occassion = Universals.General.EmptyString();
        this.hourOfConsumption = 0;
        this.minuteOfConsumption = 0;
    }
    // Construct from Drink Template
    public Drink(DrinkTemplate template){
        this.name = template.GetName();
        this.type = template.GetType();
        this.servings = template.GetServings();
        this.aPV = template.GetAPV();
        this.price = template.GetPrice();
        /// TODO, implement image case
    }

    ///
    /// Setters and Getters
    ///
    // Occasion
    public String GetOccasion(){
        return this.occassion;
    }
    public void SetOccassion(String newOccasion){
        this.occassion = newOccasion;
    }
    // Hour of Consumption
    public short GetHourOfConsumption(){
        return this.hourOfConsumption;
    }
    public void SetHourOfConsumption(short newHour){
        if (newHour < 0) this.hourOfConsumption = 0;
        else this.hourOfConsumption = newHour;
    }
    // Minute of Consumption
    public short GetMinuteOfConsumption(){
        return this.minuteOfConsumption;
    }
    public void SetMinuteOfConsumption(short newMinute){
        if (newMinute < 0) this.minuteOfConsumption = 0;
        else this.minuteOfConsumption = newMinute;
    }

}
