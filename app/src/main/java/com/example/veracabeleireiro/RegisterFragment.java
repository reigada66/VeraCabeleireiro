package com.example.veracabeleireiro;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterFragment extends Fragment {

    // Outros atributos e métodos da classe

    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private Button registerButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        // Inicialize as referências aos elementos do layout
        passwordEditText = view.findViewById(R.id.passwordEditText);
        confirmPasswordEditText = view.findViewById(R.id.confirmPasswordEditText);
        registerButton = view.findViewById(R.id.registerButton);

        // Configurar o clique do botão de registro
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obter os valores das senhas
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                // Verificar se as senhas coincidem
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(getActivity(), "As senhas não coincidem", Toast.LENGTH_SHORT).show();
                    return; // Interromper o processo de registro
                }

                // Realizar o registro do usuário
                // ...

                // Exemplo: exibir uma mensagem de registro bem-sucedido
                Toast.makeText(getActivity(), "Registro concluído com sucesso", Toast.LENGTH_SHORT).show();

                // Voltar para o LoginFragment
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.popBackStack(); // Remover o RegisterFragment da pilha
            }
        });

        return view;
    }

    // Outros métodos da classe

}