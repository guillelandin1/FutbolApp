package com.esei.uvigo.futbolapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TeamActivity extends AppCompatActivity {
    /* LISTAR TODOS LOS JUGADORES CON SUS CORRESPONDIENTES POSICIONES, SI CLICKAS EN UN JUGADOR TE PODRÍA
    LLEVAR A SUS ESTADÍSTICAS DONDE PODRÍAS SACAR UN INFORME DE ESE JUGADOR, ADEMÁS HACEN FALTA MENUS
    PARA FILTRAR JUGADORES POR POSICIÓN Y NOMBRE, ES DECIR, UNA LISTVIEW DE JUGADORES, UN MENU PARA
    FILTRAR, QEU APAREZCA EL NOMBRE DEL EQUIPO ARRIBA*/
    FutbolFacade futbolFacade;
    int userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        futbolFacade = new FutbolFacade((FutbolApplication) getApplication(), this);


    }
}
