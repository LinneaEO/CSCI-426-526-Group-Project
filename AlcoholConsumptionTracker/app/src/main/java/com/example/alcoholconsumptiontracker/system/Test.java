package com.example.alcoholconsumptiontracker.system;

import android.util.Log;

///
///  Class responsible for testing each class's test methods
///
public class Test {

    ///
    ///  Tests all methods present from all classes.
    ///     Always prints failure cases
    ///     Optionally prints success cases
    ///
    public static void TestAll(boolean printAllMessages){

        // Notify begin testing
        Log.d(Universals.TestMessages.TestMessageTag, "-------Begin Testing-------");

        // ---- Alcohol Logging
        // DrinkTemplate Methods
        DrinkTemplate.TestProduceDrink(printAllMessages);

        // Drink Methods
        Drink.TestGettersAndSetters(printAllMessages);

        // DrinkTemplateManager Methods
        DrinkTemplateManager.TestPutTemplate(printAllMessages);
        DrinkTemplateManager.TestModifyTemplate(printAllMessages);
        DrinkTemplateManager.TestRemoveTemplate(printAllMessages);

        // ----
        // Notify end testing
        Log.d(Universals.TestMessages.TestMessageTag, "-------End Testing-------");

    }
}
