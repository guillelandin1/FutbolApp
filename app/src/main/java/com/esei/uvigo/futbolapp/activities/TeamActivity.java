package com.esei.uvigo.futbolapp.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.esei.uvigo.futbolapp.FutbolApplication;
import com.esei.uvigo.futbolapp.R;
import com.esei.uvigo.futbolapp.db.FutbolFacade;

public class TeamActivity extends AppCompatActivity {
    /* LISTAR TODOS LOS JUGADORES CON SUS CORRESPONDIENTES POSICIONES, SI CLICKAS EN UN JUGADOR TE PODRÍA
    LLEVAR A SUS ESTADÍSTICAS DONDE PODRÍAS SACAR UN INFORME DE ESE JUGADOR, ADEMÁS HACEN FALTA MENUS
    PARA FILTRAR JUGADORES POR POSICIÓN Y NOMBRE, ES DECIR, UNA LISTVIEW DE JUGADORES, UN MENU PARA
    FILTRAR, QEU APAREZCA EL NOMBRE DEL EQUIPO ARRIBA*/
    private FutbolFacade futbolFacade;

    private TextView tvTeamName;
    int userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        SharedPreferences sharedPreferences = getSharedPreferences("Session", MODE_PRIVATE);
        userId = sharedPreferences.getInt("user_id", -1);

        futbolFacade = new FutbolFacade((FutbolApplication) getApplication(), this);
        tvTeamName = findViewById(R.id.tvTeamName);
        String teamName = futbolFacade.getTeamName(userId);

        if (teamName == null || teamName.isEmpty()) {
            tvTeamName.setText("Equipo no encontrado"); // Mensaje de error
        } else {
            tvTeamName.setText(teamName); // Mostrar el nombre del equipo
        }


    }


}
