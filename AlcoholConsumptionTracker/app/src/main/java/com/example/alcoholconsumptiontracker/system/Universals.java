package com.example.alcoholconsumptiontracker.system;

///
///  Represents the class of constants used throughout the project under
///     different contexts. Meant as a method to provide consistency.
///     In an instance where a programmer uses a certain number or
///     certain string to represent data and is expected to remember
///     that certain string or number's meaning, there is room for
///     error. The class of universals seeks to eliminate ths error
///     case.
///
public class Universals {

    ///
    /// Contains general constants used in the app
    ///
    public static class General{
        public static String EmptyString(){
            return "";
        }
    }

    ///
    ///  The class of static error messages for each class
    ///
    public static class TestMessages{

        /// Generic tag associated with test messages for logging functions.
        public static String TestMessageTag = "<Test Message>";
        /// Generic message title that appears in front of all error messages.
        public static String ErrorMessageTitle = "Error - ";
        /// Generic message title that appears in front of all test passing messages.
        public static String PassMessageTitle = "Pass - ";

        ///
        ///  The class of error messages for drink template
        ///
        public static class DrinkTemplateMessages{

            ///
            public static String DrinkTemplateErrorMessageTitle = ErrorMessageTitle + "DrinkTemplate: ";
            public static String DrinkTemplatePassMessageTitle = PassMessageTitle + "DrinkTemplate: ";

            public static String ProduceDrinkMessage(boolean pass, int testCase){
                if (pass){
                    return "Produce Drink Method Success. Test Case <" + testCase + ">";
                }
                else
                    return "Produce Drink Method Failure. Test Case <" + testCase + ">";
            }


        }

        ///
        ///  The class of error messages for drink
        ///
        public static class DrinkMessages{
            ///
            public static String DrinkErrorMessageTitle = ErrorMessageTitle + "Drink: ";
            public static String DrinkPassMessageTitle = PassMessageTitle + "Drink: ";

            public static String GetterSetterMessage(boolean pass, int testCase){
                if (pass){
                    return "Drink Getter Setter Methods Success. Test Case <" + testCase + ">";
                }
                else
                    return "Produce Getter Setter Methods Failure. Test Case <" + testCase + ">";
            }

        }
    }

}
