package com.esei.uvigo.futbolapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class FutbolFacade {
    private DBManager dbManager;
    private final Context context;

    //En esta clase interactuaremos con la BD para solo usar una instancia de ella

    public FutbolFacade(FutbolApplication futbolApplication, Context context){
        this.dbManager = futbolApplication.getDbManager();
        this.context = context;
    }

    public boolean registerUser(String username, String password, String email){
        SQLiteDatabase db = dbManager.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBManager.COLUMN_USERNAME, username);
        values.put(DBManager.COLUMN_PASSWORD, password);
        values.put(DBManager.COLUMN_EMAIL, email);

        long result = db.insert(DBManager.TABLE_USUARIOS, null, values);

        return result != -1;
    }

    public int authenticateUser(String username, String password) { //Método para confirmar que el usuario y contraseña coinciden y existen
        SQLiteDatabase db = dbManager.getReadableDatabase();

        Cursor cursor = db.query(
                DBManager.TABLE_USUARIOS,
                new String[]{DBManager.COLUMN_ID, DBManager.COLUMN_PASSWORD}, // Obtener ID y hash de contraseña
                DBManager.COLUMN_USERNAME + "=?",                             // Filtro: username
                new String[]{username},                                       // Parámetro: valor del username
                null, null, null
        );

        if (cursor.moveToFirst()) {
            int idColumnIndex = cursor.getColumnIndex(DBManager.COLUMN_ID);
            int passwordColumnIndex = cursor.getColumnIndex(DBManager.COLUMN_PASSWORD);

            if (idColumnIndex == -1 || passwordColumnIndex == -1) {
                throw new IllegalArgumentException("Required column not found in the database.");
            }

            int userId = cursor.getInt(idColumnIndex);
            String storedHashedPassword = cursor.getString(passwordColumnIndex);

            // Comparamos la contraseña ingresada con el hash almacenado
            if (Utils.checkPassword(password, storedHashedPassword)) {
                cursor.close();
                return userId; // Devuelve el ID del usuario autenticado
            }
        }

        cursor.close();
        return -1; // Si la autenticación falla
    }

    public String getUsername(int userId){ //MÉTODO PARA DEVOLVER UN USUARIO DE LA BD POR SU ID
        SQLiteDatabase db = dbManager.getReadableDatabase();

        Cursor cursor = db.query(
                DBManager.TABLE_USUARIOS,
                new String[]{DBManager.COLUMN_USERNAME},
                DBManager.COLUMN_ID + " = ?",
                new String[]{String.valueOf(userId)},
                null,
                null,
                null
        );

        if(cursor != null && cursor.moveToFirst()){
            String username = cursor.getString(cursor.getColumnIndexOrThrow(DBManager.COLUMN_USERNAME));
            cursor.close();
            return username;
        }

        if(cursor!=null){
            cursor.close();
        }

        return null;


    }

    public boolean isEmailRegistered(String email){
        SQLiteDatabase db = dbManager.getReadableDatabase();

        Cursor cursor = db.query(
                DBManager.TABLE_USUARIOS,  // Nombre de la tabla
                new String[]{"email"},     // Columnas a devolver
                DBManager.COLUMN_EMAIL + "=?",               // Cláusula WHERE
                new String[]{email},        // Argumentos de la cláusula WHERE
                null,                       // groupBy
                null,                       // having
                null                        // orderBy
        );


        if(cursor.getCount()>0){
            cursor.close();
            return true;
        }else{
            cursor.close();
            return false;
        }

    }
}
