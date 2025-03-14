package com.esei.uvigo.futbolapp.activities;

import android.annotation.SuppressLint;
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
}
