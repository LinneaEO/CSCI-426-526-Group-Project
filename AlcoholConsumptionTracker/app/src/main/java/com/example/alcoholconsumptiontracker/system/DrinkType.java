/// <summary>
///  File: DrinkType.java
///     Description: Contains the class DrinkType which is meant
///        to simulate an enum containing 4 members, Beer, Wine,
///        Liquor, and Cocktail respectively
///
/// </summary>

package com.example.alcoholconsumptiontracker.system;


public class DrinkType {

    private short value;

    /// <summary>
    ///  Default constructor
    /// </summary>
    public DrinkType(){
        this.value = (char)0;
    }

    /// <summary>
    ///  Getter. Returns value as a string
    /// </summary>
    public String Get(){
       return DrinkTypeFromShort(this.value);
    }
    /// <summary>
    ///  Setters
    /// </summary>
    public void Set(short value){
        if (value < 0) this.value = 0;
        else this.value = value;
    }
    public void Set(String value){
        if (DrinkTypeFromString(value) < 0) this.value = 0;
        else this.value = DrinkTypeFromString(value);
    }


    /// <summary>
    ///  Given an inputted short, this function returns the string Drink Type
    ///     the short represents. If no string is represented by the inputted value,
    ///     "n/a" is returned instead.
    /// </summary>
    public static String DrinkTypeFromShort(short value){
        switch(value){
            case 0: return "Beer";
            case 1: return "Wine";
            case 2: return "Liquor";
            case 3: return "Cocktail";
            default: return "n/a";
        }
    }

    /// <summary>
    ///  Given an inputted string, this function returns the short Drink Type
    ///     the string represents. If no short is represented by the inputted value,
    ///     -1 is returned instead.
    /// </summary>
    public static short DrinkTypeFromString(String value){
        switch(value){
            case "Beer": return 0;
            case "Wine": return 1;
            case "Liquor": return 2;
            case "Cocktail": return 3;
            default: return -1;
        }
    }
}
