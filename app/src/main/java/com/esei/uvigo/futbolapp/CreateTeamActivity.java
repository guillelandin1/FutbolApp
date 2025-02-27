package com.esei.uvigo.futbolapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CreateTeamActivity extends AppCompatActivity {

    FutbolFacade futbolFacade;
    private EditText edtTeamname;
    private Button btnCreateTeam;
    private int userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);

        futbolFacade = new FutbolFacade((FutbolApplication) getApplication(), this);

        edtTeamname = findViewById(R.id.edtTeamname);
        btnCreateTeam = findViewById(R.id.btnCreateTeam);

        SharedPreferences sharedPreferences = getSharedPreferences("Session", MODE_PRIVATE);
        userId = sharedPreferences.getInt("user_id", -1);

        btnCreateTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String teamname = edtTeamname.toString().trim();

                if(teamname.isEmpty()){
                    Toast.makeText(CreateTeamActivity.this, "Introduzca el nombre del equipo", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(futbolFacade.registerTeam(userId,teamname)){
                    Toast.makeText(CreateTeamActivity.this, "Equipo creado con éxito", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CreateTeamActivity.this, TeamActivity.class);
                    startActivity(intent);
                    finish();

                }else{
                    //TODO -> Gestionar que el equipo tenga mínimo 4 caracteres
                    Toast.makeText(CreateTeamActivity.this, "El equipo debe contener al menos 4 carácteres", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }
}
