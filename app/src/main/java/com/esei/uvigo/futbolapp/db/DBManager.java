package com.esei.uvigo.futbolapp.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBManager extends SQLiteOpenHelper {
    private static final String DB_NAME = "FutbolAppDB";
    private static final int DB_VERSION = 1;

    //Tabla de Usuarios
    public static final String TABLE_USUARIOS = "usuarios";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_EMAIL = "email";

    //Tabla de Equipo

    public static final String TABLE_EQUIPO = "equipo";
    public static final String COLUMN_EQUIPO_ID = "_id";
    public static final String COLUMN_NOMBRE_EQUIPO = "nombre";


    //Tabla de jugadores
    public static final String TABLE_JUGADOR = "jugador";
    public static final String COLUMN_ID_JUGADOR = "_id";
    public static final String COLUMN_NOMBRE_JUGADOR = "nombre";
    public static final String COLUMN_PARTIDOS_JUGADOS = "partidos_jugados";
    public static final String COLUMN_GOLES = "goles";
    public static final String COLUMN_ASISTENCIAS = "asistencias";
    public static final String COLUMN_MINUTOS = "minutos";
    public static final String COLUMN_TARJETAS_AMARILLAS = "tarjetas_amarillas";
    public static final String COLUMN_TARJETAS_ROJAS = "tarjetas_rojas";

    public DBManager(Context context) {
        super(context,
                DB_NAME,
                null,
                DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        try{
            db.beginTransaction();

            //Crear tabla Usuarios
            String CREATE_TABLE_USUARIOS = "CREATE TABLE " + TABLE_USUARIOS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USERNAME + " TEXT NOT NULL UNIQUE, " +
                    COLUMN_PASSWORD + " TEXT NOT NULL, " +
                    COLUMN_EMAIL + " TEXT NOT NULL UNIQUE)";

            db.execSQL(CREATE_TABLE_USUARIOS);

            //Crear tabla de equipo
            String CREATE_TABLE_EQUIPO = "CREATE TABLE " + TABLE_EQUIPO + " (" +
                    COLUMN_EQUIPO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOMBRE_EQUIPO + " TEXT NOT NULL, " +
                    " user_id INTEGER NOT NULL , " +
                    "FOREIGN KEY(user_id) REFERENCES " + TABLE_USUARIOS + "(" + COLUMN_ID + ")" +
                    "ON DELETE CASCADE" + ")";

            db.execSQL(CREATE_TABLE_EQUIPO);

            //Crear tabla de jugador
            String CREATE_TABLE_JUGADOR = "CREATE TABLE " + TABLE_JUGADOR + " (" +
                    COLUMN_ID_JUGADOR + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOMBRE_JUGADOR + " TEXT NOT NULL, " +
                    COLUMN_PARTIDOS_JUGADOS + " INTEGER, " +
                    COLUMN_GOLES + " INTEGER, " +
                    COLUMN_ASISTENCIAS + " INTEGER, " +
                    COLUMN_MINUTOS + " INTEGER, " +
                    COLUMN_TARJETAS_AMARILLAS + " INTEGER, " +
                    COLUMN_TARJETAS_ROJAS + " INTEGER, " +
                     "equipo_id INTEGER NOT NULL, " +
                    "FOREIGN KEY(equipo_id) REFERENCES " + TABLE_EQUIPO + "(" + COLUMN_EQUIPO_ID + ")" +
                     "ON DELETE CASCADE" + ")";
            db.execSQL(CREATE_TABLE_JUGADOR);


            db.setTransactionSuccessful();

        }catch(SQLException e){
            Log.e("DBManager", "Error creando tablas: " + e.getMessage());
        }finally {
            db.endTransaction();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        try {
            db.beginTransaction();

            // Eliminar tablas existentes
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_JUGADOR);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_EQUIPO);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);


            onCreate(db);

            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e("DBManager", "Error actualizando base de datos: " + e.getMessage());
        } finally {
            db.endTransaction();
        }

    }


}
