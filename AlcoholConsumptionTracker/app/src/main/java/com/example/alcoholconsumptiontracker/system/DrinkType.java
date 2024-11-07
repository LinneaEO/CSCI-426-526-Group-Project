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
    DrinkType(){
        this.value = (char)0;
    }

    /// <summary>
    ///  Getter. Returns value as a string
    /// </summary>
    public String Get(){
       switch(this.value){
           case 0: return "Beer";
           case 1: return "Wine";
           case 2: return "Liquor";
           case 3: return "Cocktail";
           default: return "n/a";
       }
    }

    /// <summary>
    ///  Setter
    /// </summary>
    public void Set(short value){
        this.value = value;
    }
}
