package com.example.assignment3;

import android.app.Application;

public class MyApp extends Application {

    private StorageManager storageManager = new StorageManager();

    public StorageManager getStorageManager() {
        return storageManager;
    }
}
