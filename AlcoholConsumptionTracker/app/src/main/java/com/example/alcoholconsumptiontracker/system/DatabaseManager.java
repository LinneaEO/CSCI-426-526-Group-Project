/// <summary>
///  File: DatabaseManager.java
///     Description: Contains the class and methods of setting up and providing access information
///     to the internal storage backend of this app.
/// </summary>
package com.example.alcoholconsumptiontracker.system;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Environment;

import java.io.File;

public class DatabaseManager {

    ///
    /// Locals
    ///
    // Represents whether the database is initialized or not for backend use.
    private boolean initialized;
    // The internal storage root directory and name
    private File appRootDirectory;
    private static String appRootDirectoryName = "appDir";

    // The internal storage image directory and name
    private File imageDirectory;
    private static String imageDirectoryName = "imgDir";

    /// <summary>
    ///  Constructor. Initializes the database manager by
    ///     -Initializing the appRootDirectory and maintaining its folderPath
    ///     -Initializing other directories and maintaining their folderPaths (within internal storage)
    ///  If successfully initialized, initialized is set to true. Otherwise, it is set to false.
    ///     *database access isn't possible with this class if uninitialized.
    /// </summary>
    public DatabaseManager(Context context){
        this.initialized = this.InitializeDatabase(context);
    }

    ///
    /// Getters and Setters
    ///

    /// <summary>
    ///     Gets the app root directory.
    ///     If initialized, returns directory
    ///     Returns null otherwise.
    /// </summary>
    public File getAppRootDirectory() {
        if (initialized)
            return appRootDirectory;
        else
            return null;
    }
    /// <summary>
    ///     Gets the app image directory.
    ///     If initialized, returns directory
    ///     Returns null otherwise.
    /// </summary>
    public File getImageDirectory(){
        if (initialized)
            return imageDirectory;
        else
            return null;
    }

    ///
    /// Methods
    ///

    /// <summary>
    ///  Given an inputted context, this method initializes the database of the app
    ///     within the given context.
    ///     If initialized successfully, set initialized to true
    ///     Otherwise, set initialized to false.
    ///     Returns the initialized status of the database manager when complete.
    /// </summary>
    public boolean InitializeDatabase(Context context){

        try{
            File rootDir = context.getFilesDir();
            File appDir = new File(rootDir.getAbsolutePath() + "/" + appRootDirectoryName);

            // If an app directory is successfully created, initialize all other sub-directories
            //  within it as this implies this is the first time the directory is being created.
            if (appDir.mkdir()){

                // Initialize image directory
                File imgDir = new File(appDir.getAbsolutePath() + "/" + imageDirectoryName);
            }

            // After directory is created or is found within the internal storage, set the database
            //  manager's main directory. Then return.
            this.appRootDirectory = appDir;

            // Set initialized to true
            this.initialized = true;
        } catch (Exception e) {
            // If an error was encountered, set initialized to false
            this.initialized = false;
        }

        return this.initialized;
    }
}
