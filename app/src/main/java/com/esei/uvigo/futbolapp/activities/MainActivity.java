package com.esei.uvigo.futbolapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.esei.uvigo.futbolapp.FutbolApplication;
import com.esei.uvigo.futbolapp.R;
import com.esei.uvigo.futbolapp.db.FutbolFacade;


public class MainActivity extends AppCompatActivity {

    private FutbolFacade futbolFacade;
    private Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Inicializamos el futbolFacade
        futbolFacade = new FutbolFacade((FutbolApplication) getApplication(), this);
        //logoutUser();

        SharedPreferences sharedPreferences = getSharedPreferences("Session", MODE_PRIVATE);
        boolean isLogged = sharedPreferences.getBoolean("isLogged", false);


        //Si ya estás logeado entras directamente y tienes un equipo
        if(isLogged && hasTeam()){
            Intent intent = new Intent(MainActivity.this, TeamActivity.class);
            startActivity(intent);
            finish();
            return;

        }

        if(isLogged){
            Intent intent = new Intent(MainActivity.this, CreateTeamActivity.class);
            startActivity(intent);
            finish();
            return;

        }
        //Si no estás logeado, configuramos el layout.
        setContentView(R.layout.activity_main);

         //Inicializamos las vistas
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        //Configuramos los listeners de los botones
        btnLogin.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        btnRegister.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });




    }



    public boolean hasTeam(){
        SharedPreferences sharedPreferences = getSharedPreferences("Session", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("user_id", -1);

        return futbolFacade.hasTeam(userId);
    }


}