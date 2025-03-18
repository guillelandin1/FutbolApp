package com.esei.uvigo.futbolapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
    private Button btnLogout;
    int userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        SharedPreferences sharedPreferences = getSharedPreferences("Session", MODE_PRIVATE);
        userId = sharedPreferences.getInt("user_id", -1);
        String teamName = sharedPreferences.getString("teamname",null);

        futbolFacade = new FutbolFacade((FutbolApplication) getApplication(), this);
        tvTeamName = findViewById(R.id.tvTeamName);
        btnLogout = findViewById(R.id.btnLogout);
        //String teamName = futbolFacade.getTeamName(userId);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogoutClick();
            }
        });

        if (teamName == null || teamName.isEmpty()) {
            tvTeamName.setText("Equipo no encontrado"); // Mensaje de error
        } else {
            tvTeamName.setText(teamName); // Mostrar el nombre del equipo
        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        this.getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            onLogoutClick();
            return true;

        } else if (item.getItemId() == R.id.action_settings) {
            Intent intent = new Intent(TeamActivity.this, SettingsActivity.class);
            startActivity(intent);
            return true;

        }else{
            return super.onOptionsItemSelected(item);
        }
    }

    private void onLogoutClick(){
        SharedPreferences prefs = getSharedPreferences("Session", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.clear();
        editor.apply();

        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }




}
