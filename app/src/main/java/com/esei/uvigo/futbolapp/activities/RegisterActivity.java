package com.esei.uvigo.futbolapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.esei.uvigo.futbolapp.FutbolApplication;
import com.esei.uvigo.futbolapp.R;
import com.esei.uvigo.futbolapp.Utils;
import com.esei.uvigo.futbolapp.db.FutbolFacade;

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
               }if(!Utils.isValidEmail(email)){
                   Toast.makeText(RegisterActivity.this,"El email no tiene el formato correcto, inténtelo de nuevo",Toast.LENGTH_SHORT).show();
                   return;
               }if(!Utils.isValidPasswd(password)){
                   Toast.makeText(RegisterActivity.this, "La contraseña debe contener al menos 8 caracteres,una letra y un número", Toast.LENGTH_SHORT).show();
                   return;
               }if(futbolFacade.isEmailRegistered(email)){
                   Toast.makeText(RegisterActivity.this,"Este email ya tiene una cuenta registrada", Toast.LENGTH_SHORT).show();
                   return;
               }if(registerUser(username,password,email)){
                   Toast.makeText(RegisterActivity.this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                   startActivity(intent);
                   finish();

               }else{
                   Toast.makeText(RegisterActivity.this, "Este usuario ya existe", Toast.LENGTH_SHORT).show();
               }
           }

       });
    }

    private boolean registerUser(String username, String password, String email){
        String hashedPassword = Utils.hashPassword(password);

        return futbolFacade.registerUser(username,hashedPassword,email);
    }
}
