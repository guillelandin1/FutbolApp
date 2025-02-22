package com.esei.uvigo.futbolapp;

import android.app.Application;

public class FutbolApplication extends Application {

    private DBManager dbManager;

    public void onCreate(){
        super.onCreate();

        this.dbManager = new DBManager(this);
    }

    public DBManager getDbManager(){
        return dbManager; //Método para obtener la instancia de la BD
    }
}
