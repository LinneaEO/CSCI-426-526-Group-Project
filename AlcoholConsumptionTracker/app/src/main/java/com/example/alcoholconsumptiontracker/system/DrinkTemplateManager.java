/// <summary>
///  File: Drink.java
///     Description: Meant to contain the object representing a collection of DrinkTemplates
///         Provides drink templates to the frontend for alcohol drink logging
///         Provides drink templates to the backend for storage
///         Provides drinks to the frontend produced by contained drink templates for reporting
/// </summary>
package com.example.alcoholconsumptiontracker.system;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;

///
/// Drink Template Class
///     Stores drink templates for the app
///
public class DrinkTemplateManager {

    ///
    /// Local variables
    ///
    // Represents a dictionary of templates.
    // Key = template name (String), value = template (DrinkTemplate)
    private HashMap<String, DrinkTemplate> templateHashMap;

    ///
    /// Constructors
    ///
    // Default. Initializes with no templates
    public DrinkTemplateManager(){
        this.templateHashMap = new HashMap<>();
    }
    // Initialize with templates from files

    ///
    /// Setters and Getters
    ///
    public HashMap<String, DrinkTemplate> GetTemplateList(){
        return this.templateHashMap;
    }

    ///
    /// Methods
    ///
    ///
    /// - Frontend
    ///

    /// <summary>
    /// Given a string representing the drink template key, a string representing
    ///     a drink occasion, an hour and minute representing time of day being drunk,
    ///     this method searches this object's templateHashMap for that template,
    ///     makes a drink from that template, then returns that drink. If this
    ///     method fails to do so, it returns null.
    /// </summary>
    public Drink ProduceDrink(String key, String occasion, short hour, short minute){

        DrinkTemplate temp = this.GetTemplate(key);
        if (temp != null) return temp.ProduceDrink(occasion, hour, minute);
        else return null;
    }

    ///
    /// - System
    ///

    /// <summary>
    /// Searches for a template within the template hashmap by key (name) and returns
    /// it if it exists. returns null otherwise.
    /// </summary>
    public DrinkTemplate GetTemplate(String key){
        // If the key isn't contained in the map, return null
        if (!this.templateHashMap.containsKey(key))  return null;

        // If the key is contained in the map, return that template
        //  *Template can be null
        return this.templateHashMap.get(key);
    }
    /// <summary>
    /// Puts a new template into the dictionary using its name as its key.
    /// Given an inputted template, this method checks to see if a template with the input's
    ///     same name (key) exists in the dictionary.
    ///     If it doesn't, enter the inputted template into the dictionary with its name being its key, return true
    ///     If it does, return false
    /// </summary>
    public boolean PutTemplate(DrinkTemplate newTemplate){
        if (this.templateHashMap.containsKey(newTemplate.GetName())) return false;
        this.templateHashMap.put(newTemplate.GetName(), newTemplate);
        return true;
    }
    /// <summary>
    /// Modifies an existing template by changing one value with another.
    /// Given an inputted template, this method checks to see if a template with the input's
    ///     same name (key) exists in the dictionary.
    ///     If it does, enter the inputted template into the dictionary with its name being its key, return true
    ///     If it doesn't, return false
    /// </summary>
    public boolean ModifyTemplate(DrinkTemplate newTemplateVersion){
        if (this.templateHashMap.containsKey(newTemplateVersion.GetName())) {
            this.templateHashMap.put(newTemplateVersion.GetName(), newTemplateVersion);
            return true;
        }
        return false;
    }
    /// <summary>
    /// Deletes an existing template within the dictionary with a matching key.
    /// Given an inputted template key, this method checks to see if a template with the input's
    ///     same key exists in the dictionary.
    ///     If it does, delete the template, return true
    ///     If it doesn't, return false
    /// </summary>
    public boolean DeleteTemplate(String templateKey){
        if (this.templateHashMap.containsKey(templateKey)) {
            this.templateHashMap.remove(templateKey);
            return true;
        }
        return false;
    }

    ///
    /// - Backend
    ///
    public boolean ReadTemplateList(String filePath){
        return false;
    }
    public boolean WriteTemplateList(String filePath){
        return false;
    }
}
