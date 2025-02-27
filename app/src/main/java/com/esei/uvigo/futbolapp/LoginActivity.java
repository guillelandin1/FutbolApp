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

public class LoginActivity extends AppCompatActivity {
    private FutbolFacade futbolFacade;
    private EditText edtUsername,edtPassword;
    private Button btnLogin, btnRecoverPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        futbolFacade = new FutbolFacade((FutbolApplication) getApplication(), this);

        //Referenciamos a los elementos del layout
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRecoverPassword = findViewById(R.id.btnResetPass);

        //Manejo boton Login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if(username.isEmpty() || password.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Por favor cubra ambos campos ", Toast.LENGTH_SHORT).show();
                    return;

                }

                int userId = futbolFacade.authenticateUser(username,password);

                if(userId!= -1){
                    Toast.makeText(LoginActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                    if(futbolFacade.hasTeam(userId)){

                        Intent intent = new Intent(LoginActivity.this,TeamActivity.class);
                        startActivity(intent);
                        finish();

                    }else{
                        Intent intent = new Intent(LoginActivity.this, CreateTeamActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }else{
                    Toast.makeText(LoginActivity.this, "Inicio de Sesión fallido, inténtelo de nuevo", Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });

        btnRecoverPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO -> Gestionar el envío de token para recuperar contraseña
            }
        });


    }


    public void saveSession(String username, int userId) { //Guardar la sesión por si la app se cierra inesperadamente
        SharedPreferences sharedPreferences = getSharedPreferences("Session", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("loggeduser", username); // Guardar el nombre de usuario
        editor.putInt("user_id", userId);         // Guardar el ID del usuario
        editor.putBoolean("isLogged", true);      // Marcar como logueado
        editor.apply();
    }
}
