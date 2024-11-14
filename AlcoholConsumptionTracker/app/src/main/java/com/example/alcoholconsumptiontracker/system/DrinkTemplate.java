/// <summary>
///  File: DrinkTemplate.java
///     Description: Contains the class and methods for drink templates. Templates enable easier creation of drinks and
///         allow for programmable drinks in the app.
/// </summary>
///
package com.example.alcoholconsumptiontracker.system;

import android.content.Intent;

public class DrinkTemplate {

    //
    private String name; // Name of drink
    private DrinkType type; // Type of drink
    private short servings; // Number of servings ( 0 >= )
    private float aPV; // Alcohol Per volume ( 0.0 >= )
    private float price; // Price ( 0.0 >= , dollars USD)
    //private Intent image; // Image from gallery

    ///
    /// Constructors
    ///
    // Default
    public DrinkTemplate(){
        this.name = Universals.General.EmptyString();
        this.type = new DrinkType();
        this.servings = 0;
        this.aPV = 0;
        this.price = 0;
    }

    ///
    ///  Setters and Getters
    ///
    // Name
    public String GetName(){return this.name;}
    public void SetName(String newName){this.name = newName;}
    // Type
    public DrinkType GetType(){return this.type;}
    public void SetType(DrinkType newType){this.type = newType;}
    // Servings
    public short GetServings(){
        return this.servings;
    }
    public void SetServings(short newServings){
        if (newServings < 0) this.servings = 0;
        else this.servings = newServings;
    }
    // APV
    public float GetAPV(){return this.aPV;}
    public void SetAPV(float newAPV){
        if (newAPV < 0) this.aPV = 0;
        else this.aPV = newAPV;
    }
    // Price
    public float GetPrice(){return this.price;}
    public void SetPrice(float newPrice){
        if (newPrice < 0) this.price = 0;
        else this.price = newPrice;
    }
    // Image

    ///
    /// Methods
    ///
    /// <summary>
    ///  Given an occasion, hour, and minute, produces a new drink object using
    ///     the contents of this drink template.
    /// </summary>
    public Drink ProduceDrink(String occasion, short hour, short minute){
        Drink newDrink = new Drink(this);
        newDrink.SetOccassion(occasion);
        newDrink.SetHourOfConsumption(hour);
        newDrink.SetMinuteOfConsumption(minute);
        return newDrink;
    }

}
