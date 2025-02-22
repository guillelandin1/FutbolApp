package com.esei.uvigo.futbolapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private FutbolFacade futbolFacade;
    private EditText edtUsername,edtPassword,edtEmail;
    private Button btnRegister;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        futbolFacade = new FutbolFacade((FutbolApplication) getApplication(), this);

        //Referenciamos los elementos del Layout para trabajar con ellos
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtEmail = findViewById(R.id.edtEmail);
        btnRegister = findViewById(R.id.btnRegister);


       btnRegister.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v){
               String username = edtUsername.getText().toString().trim();
               String password = edtPassword.getText().toString().trim();
               String email = edtEmail.getText().toString().trim();

               if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                   Toast.makeText(RegisterActivity.this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
                   return;
               } else {
                   Toast.makeText(RegisterActivity.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
               }
           }

       });
    }
}
