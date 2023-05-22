package com.example.veracabeleireiro;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inflar o fragmento principal
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new LoginFragment())
                .commit();
    }

    // Outros métodos da classe
}