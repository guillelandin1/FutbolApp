package com.esei.uvigo.futbolapp.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.esei.uvigo.futbolapp.R;
import com.esei.uvigo.futbolapp.db.FutbolFacade;

public class SettingsActivity extends  MainActivity{
    private FutbolFacade futbolFacade;
    int userId;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });

    }

    public void logoutUser() {
        // Al hacer logout, eliminamos el estado de sesión guardado
        SharedPreferences sharedPreferences = getSharedPreferences("Session", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLogged", false);  // Marcamos como no logueado
        editor.putInt("user_id", -1);  // Restablecemos el ID de usuario
        editor.apply();

        // Redirigir a la pantalla de login o de creación de equipo
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();  // Finalizamos la actividad actual
    }
}
