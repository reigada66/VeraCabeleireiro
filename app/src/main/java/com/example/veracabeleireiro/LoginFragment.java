package com.example.veracabeleireiro;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginFragment extends Fragment {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button registerButton;

    // ...

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        usernameEditText = view.findViewById(R.id.usernameEditText);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        loginButton = view.findViewById(R.id.loginButton);
        registerButton = view.findViewById(R.id.registerButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar para o fragmento de registro (register)
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                RegisterFragment registerFragment = new RegisterFragment();
                fragmentTransaction.replace(R.id.fragment_container, registerFragment);
                fragmentTransaction.commit();
            }
        });

        return view;
    }

    private void login() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Realizar a validação do username e password aqui
        if (isValidCredentials(username, password)) {
            // Credenciais corretas, redirecionar para a página de início (homepage)
            Intent intent = new Intent(getActivity(), HomePage.class);
            startActivity(intent);
        } else {
            // Credenciais incorretas, exibir mensagem de erro
            Toast.makeText(getActivity(), "Credenciais inválidas", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidCredentials(String username, String password) {
        // Verifique se o username e a password correspondem aos valores esperados
        if (username.equals("usuario") && password.equals("senha")) {
            return true; // Credenciais válidas
        } else {
            return false; // Credenciais inválidas
        }
    }
}