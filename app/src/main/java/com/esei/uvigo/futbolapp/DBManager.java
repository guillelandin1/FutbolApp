package com.esei.uvigo.futbolapp;

import android.content.Context;

public class DBManager {
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
    public static final String COLUMN_USUARIO_ID = "usuario_id";

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
    public static final String COLUMN_ID_EQUIPO_FK = "id_equipo_fk";

    public DBManager(Context context) {
        super(context,
                DB_NAME,
                null,
                DB_VERSION);
    }


}
