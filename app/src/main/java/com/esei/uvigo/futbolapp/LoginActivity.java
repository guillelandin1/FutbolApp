package com.esei.uvigo.futbolapp;

import android.content.Intent;
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

                //TODO -> Gestionar la autentificación de Usuario y Contraseña

            }
        });

        btnRecoverPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO -> Gestionar el envío de token para recuperar contraseña
            }
        });


    }
}
