/// <summary>
///  File: Drink.java
///     Description: Meant to contain the object representing a collection of DrinkTemplates
///         Provides drink templates to the frontend for alcohol drink logging
///         Provides drink templates to the backend for storage
///         Provides drinks to the frontend produced by contained drink templates for reporting
/// </summary>
package com.example.alcoholconsumptiontracker.system;

import android.content.Context;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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
    public boolean RemoveTemplate(String templateKey){
        if (this.templateHashMap.containsKey(templateKey)) {
            this.templateHashMap.remove(templateKey);
            return true;
        }
        return false;
    }

    ///
    /// - Backend
    ///
    public boolean ReadTemplateList(File targetDirectory){
        return false;
    }

    /// <summary>
    ///     Writes the contents of the DrinkTemplateManager to a directory.
    ///     targetDirectory: directory to store xml file
    ///     fileName: name of file to be created or overwritten and stored to.
    ///         name of file expected to NOT contain ".xml"
    ///     Given an inputted directory file and file name,
    ///     this method:
    ///         - Creates a new XML file within the targetDirectory
    ///             with the name of fileName
    ///         - Stores the contents of its templateDictionary within fileName
    ///     Returns true if successful in both steps.
    ///     Returns false otherwise.
    /// </summary>
    public boolean WriteTemplateList(File targetDirectory, String fileName) throws IOException, ParserConfigurationException, TransformerException {

        // Locals
        StreamResult targetStream;
        Element root;
        Element tempElement;
        Element tempElement2;
        DrinkTemplate tempTemplate;
        Iterator<Map.Entry<String, DrinkTemplate>> hashmapIterator = this.templateHashMap.entrySet().iterator();
        DocumentBuilderFactory f;
        DocumentBuilder b;
        Document d;
        TransformerFactory tf;
        Transformer t;
        DOMSource DOMDocument;
        File outputFile;

        // From the inputted directory file and name, attempt to
        //  create a file within that directory.
        //      -If target directory doesn't exist, return false
        if (!targetDirectory.exists()) return false;
        //      -If file within directory exists, set the output stream to that file
        //          Otherwise, create that file
        //      Set the output stream of this method to the result of either case
        try {
            outputFile = new File(targetDirectory.getAbsolutePath() + "/" + fileName + ".xml");
            if (outputFile.exists()) {
                targetStream = new StreamResult(new PrintWriter(new FileOutputStream(outputFile, false)));
            } else {
                if (!outputFile.createNewFile()) return false;
                targetStream = new StreamResult(new PrintWriter(new FileOutputStream(outputFile)));
            }
        }
        catch (FileNotFoundException e){
            // If file isn't found, file was deleted immediately after creation. Return false.
            Log.d(Universals.ErrorMessages.ErrorMessageTag,Universals.ErrorMessages.DrinkTemplateManagerErrorMessages.WriteTemplatesErrorFailedToCreateFile);
            return false;
        }
        catch (IOException e){
            // If IO error encountered, return false.
            Log.d(Universals.ErrorMessages.ErrorMessageTag,Universals.ErrorMessages.DrinkTemplateManagerErrorMessages.WriteTemplatesErrorFileNotFound);
            return false;
        }

        // Create document to write XML elements to
        try{
            f = DocumentBuilderFactory.newInstance();
            b = f.newDocumentBuilder();
            d = b.newDocument();
        } catch (ParserConfigurationException e) {
            // If error encountered, close output stream an return false.
            Log.d(Universals.ErrorMessages.ErrorMessageTag,Universals.ErrorMessages.DrinkTemplateManagerErrorMessages.WriteTemplatesErrorFailedToCreateDocument);
            targetStream.getOutputStream().close();
            return false;
        }

        // Write header as root
        root = d.createElement(Universals.XMLTags.DrinkTemplateManagerTags.Header());
        d.appendChild(root);

        // For each template member, write its contents
        while (hashmapIterator.hasNext()){

            // Get current element
            tempTemplate = hashmapIterator.next().getValue();

            // Create template with following format:
            //  -Name
            //  -Servings
            //  -Type
            //  -APV
            //  -Calories
            //  -Price
            //  -ImageFilePath
            //

            // Header
            tempElement = d.createElement(Universals.XMLTags.DrinkTemplateTags.Header());

            // Name
            tempElement2 = d.createElement(Universals.XMLTags.DrinkTemplateTags.Name());
            tempElement2.appendChild(d.createTextNode(tempTemplate.GetName()));
            tempElement.appendChild(tempElement2);

            // Servings
            tempElement2 = d.createElement(Universals.XMLTags.DrinkTemplateTags.Servings());
            tempElement2.appendChild(d.createTextNode(String.valueOf(tempTemplate.GetServings())));
            tempElement.appendChild(tempElement2);

            // Type
            tempElement2 = d.createElement(Universals.XMLTags.DrinkTemplateTags.Type());
            tempElement2.appendChild(d.createTextNode(String.valueOf(tempTemplate.GetType().GetValue())));
            tempElement.appendChild(tempElement2);

            // APV
            tempElement2 = d.createElement(Universals.XMLTags.DrinkTemplateTags.APV());
            tempElement2.appendChild(d.createTextNode(String.valueOf(tempTemplate.GetAPV())));
            tempElement.appendChild(tempElement2);

            // Calories
            tempElement2 = d.createElement(Universals.XMLTags.DrinkTemplateTags.Calories());
            tempElement2.appendChild(d.createTextNode(String.valueOf(tempTemplate.GetCalories())));
            tempElement.appendChild(tempElement2);

            // Price
            tempElement2 = d.createElement(Universals.XMLTags.DrinkTemplateTags.Price());
            tempElement2.appendChild(d.createTextNode(String.valueOf(tempTemplate.GetPrice())));
            tempElement.appendChild(tempElement2);

            // Image File Path
            tempElement2 = d.createElement(Universals.XMLTags.DrinkTemplateTags.Name());
            tempElement2.appendChild(d.createTextNode(tempTemplate.GetName()));
            tempElement.appendChild(tempElement2);

            // Append onto root
            root.appendChild(tempElement);
        }

        // Write to file via transformer
        try{
            tf = TransformerFactory.newInstance();
            t = tf.newTransformer();
            DOMDocument = new DOMSource(d);
            t.transform(DOMDocument, targetStream);

        } catch (TransformerFactoryConfigurationError | TransformerException e) {
            // If error encountered, close output stream an return false.
            Log.d(Universals.ErrorMessages.ErrorMessageTag,Universals.ErrorMessages.DrinkTemplateManagerErrorMessages.WriteTemplatesErrorTransformerError);
            targetStream.getOutputStream().close();
            return false;
        }

        // Close stream when finished. Verify file exists and is unlocked before returning true
        if (outputFile.exists()) return true;
        return false;
    }


    ///
    /// Test Methods
    ///
    /// <summary>
    ///  Each test method is self contained and runs different scenarios based on the
    ///     method associated with that method. Results of tests are printed to LogCat using
    ///     Log.d method
    ///     Cases are split into two categories: Test non-exception and test exception
    ///         Test non-exception tests normal use of methods
    ///         Test exception test methods throwing exceptions when they should be
    ///     Additionally, tests can be set to only print failure messages or all messages.
    /// </summary>
    ///

    // Test Put Template System method
    public static void TestPutTemplate(boolean printAllMessages){

        // Test Locals
        DrinkTemplateManager testManager = new DrinkTemplateManager();
        DrinkTemplate testTemplate = new DrinkTemplate();
        String testTemplateName = "test name ";
        short testShort = 0;

        // Non-exception cases
        //  -Case 1, normal values
        for (testShort = 0; testShort < 10; testShort++){
            testTemplate.SetName(testTemplateName + testShort);
            testManager.PutTemplate(testTemplate);
        }
        for (testShort = 0; testShort < 10; testShort++){
            if (testManager.GetTemplate(testTemplateName + testShort) == null) {
                testShort = 11;
                Log.d(
                        Universals.TestMessages.TestMessageTag,
                        Universals.TestMessages.DrinkTemplateManagerMessages.TemplatePutMessage(false, 1));
            }
        }
        if (testShort == 10 && printAllMessages){
            Log.d(
                    Universals.TestMessages.TestMessageTag,
                    Universals.TestMessages.DrinkTemplateManagerMessages.TemplatePutMessage(true, 1));
        }

        // -Case 2, put template while key of template exists within dictionary (expect false return)
        testManager = new DrinkTemplateManager();
        for (testShort = 0; testShort < 10; testShort++){
            testTemplate.SetName(testTemplateName + testShort);
            testManager.PutTemplate(testTemplate);
        }
        for (testShort = 0; testShort < 10; testShort++){
            testTemplate.SetName(testTemplateName + testShort);
            if (testManager.PutTemplate(testTemplate)) {
                testShort = 11;
                Log.d(
                        Universals.TestMessages.TestMessageTag,
                        Universals.TestMessages.DrinkTemplateManagerMessages.TemplatePutMessage(false, 12));
            }
        }
        if (testShort == 10 && printAllMessages){
            Log.d(
                    Universals.TestMessages.TestMessageTag,
                    Universals.TestMessages.DrinkTemplateManagerMessages.TemplatePutMessage(true, 2));
        }
    }
    // Test Modify Template System method
    public static void TestModifyTemplate(boolean printAllMessages){

        // Test Locals
        DrinkTemplateManager testManager = new DrinkTemplateManager();
        DrinkTemplate testTemplate = new DrinkTemplate();
        String testTemplateName = "test name ";
        short testShort = 0;

        // Non-exception cases
        //  -Case 1, normal values
        testTemplate.SetServings((short)1);
        for (testShort = 0; testShort < 10; testShort++){
            testTemplate.SetName(testTemplateName + testShort);
            testManager.PutTemplate(testTemplate);
        }
        for (testShort = 0; testShort < 10; testShort++){
            testTemplate.SetName(testTemplateName + testShort);
            testTemplate.SetServings((short)10);
            if (!testManager.ModifyTemplate(testTemplate)) {
                testShort = 11;
                Log.d(
                        Universals.TestMessages.TestMessageTag,
                        Universals.TestMessages.DrinkTemplateManagerMessages.TemplateModifyMessage(false, 1));
            }
            else if (testManager.GetTemplate(testTemplate.GetName()).GetServings() != 10){
                testShort = 11;
                Log.d(
                        Universals.TestMessages.TestMessageTag,
                        Universals.TestMessages.DrinkTemplateManagerMessages.TemplateModifyMessage(false, 1));
            }
        }
        if (testShort == 10 && printAllMessages){
            Log.d(
                    Universals.TestMessages.TestMessageTag,
                    Universals.TestMessages.DrinkTemplateManagerMessages.TemplateModifyMessage(true, 1));
        }

        // -Case 2, modify a non-existent template (expected false return)
        testManager = new DrinkTemplateManager();
        for (testShort = 0; testShort < 10; testShort++){
            testTemplate.SetName(testTemplateName + testShort);
            if (testManager.ModifyTemplate(testTemplate)) {
                testShort = 11;
                Log.d(
                        Universals.TestMessages.TestMessageTag,
                        Universals.TestMessages.DrinkTemplateManagerMessages.TemplateModifyMessage(false, 2));
            }
        }
        if (testShort == 10 && printAllMessages){
            Log.d(
                    Universals.TestMessages.TestMessageTag,
                    Universals.TestMessages.DrinkTemplateManagerMessages.TemplateModifyMessage(true, 2));
        }
    }

    // Test Remove Template System Method
    public static void TestRemoveTemplate(boolean printAllMessages){
        // Test Locals
        DrinkTemplateManager testManager = new DrinkTemplateManager();
        DrinkTemplate testTemplate = new DrinkTemplate();
        String testTemplateName = "test name ";
        short testShort = 0;

        // Non-exception cases
        //  -Case 1, normal values
        for (testShort = 0; testShort < 10; testShort++){
            testTemplate.SetName(testTemplateName + testShort);
            testManager.PutTemplate(testTemplate);
        }
        for (testShort = 0; testShort < 10; testShort++){
            testTemplate.SetName(testTemplateName + testShort);
            if (!testManager.RemoveTemplate(testTemplate.GetName())) {
                testShort = 11;
                Log.d(
                        Universals.TestMessages.TestMessageTag,
                        Universals.TestMessages.DrinkTemplateManagerMessages.TemplateRemoveMessage(false, 1));
            }
        }
        if (testShort == 10 && printAllMessages){
            Log.d(
                    Universals.TestMessages.TestMessageTag,
                    Universals.TestMessages.DrinkTemplateManagerMessages.TemplateRemoveMessage(true, 1));
        }

        // -Case 2, remove a non-existent template (expected false return)
        testManager = new DrinkTemplateManager();
        for (testShort = 0; testShort < 10; testShort++){
            testTemplate.SetName(testTemplateName + testShort);
            if (testManager.RemoveTemplate(testTemplate.GetName())) {
                testShort = 11;
                Log.d(
                        Universals.TestMessages.TestMessageTag,
                        Universals.TestMessages.DrinkTemplateManagerMessages.TemplateRemoveMessage(false, 2));
            }
        }
        if (testShort == 10 && printAllMessages){
            Log.d(
                    Universals.TestMessages.TestMessageTag,
                    Universals.TestMessages.DrinkTemplateManagerMessages.TemplateRemoveMessage(true, 2));
        }
    }

    // Test WriteTemplateList Backend Method
    public static void TestWriteTemplateList(boolean printAllMessages, Context context) {

        // Locals
        DatabaseManager dbm = new DatabaseManager(context);
        DrinkTemplateManager testManager;
        DrinkTemplate testTemplate;

        // Non-exception cases
        //  -Case 1, store to app root directory
        testManager = new DrinkTemplateManager();
        testTemplate = new DrinkTemplate();
        testManager.PutTemplate(testTemplate);
        try{
            testManager.WriteTemplateList(dbm.GetAppRootDirectory(),"testDrinkTemplateFile");
            if (printAllMessages)
                Log.d(
                        Universals.TestMessages.TestMessageTag,
                        Universals.TestMessages.DrinkTemplateManagerMessages.TemplateWriteTemplateListMessage(false, 1)
                );
        }
        catch (IOException | ParserConfigurationException | TransformerException e){
            Log.d(
                    Universals.TestMessages.TestMessageTag,
                    Universals.TestMessages.DrinkTemplateManagerMessages.TemplateWriteTemplateListMessage(false, 1)
            );
        }

        // Exception cases

    }

    public static void TestReadTemplateList(boolean printAllMessages, Context context){
        
    }

}
