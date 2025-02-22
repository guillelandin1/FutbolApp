package com.esei.uvigo.futbolapp;

import android.content.Context;

public class FutbolFacade {
    private DBManager dbManager;
    private final Context context;

    //En esta clase interactuaremos con la BD para solo usar una instancia de ella

    public FutbolFacade(FutbolApplication futbolApplication, Context context){
        this.dbManager = futbolApplication.getDbManager();
        this.context = context;
    }
}
