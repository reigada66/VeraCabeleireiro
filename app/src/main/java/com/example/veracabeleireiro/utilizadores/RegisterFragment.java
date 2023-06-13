package com.example.veracabeleireiro.utilizadores;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.veracabeleireiro.databinding.FragmentRegisterBinding;

public class RegisterFragment extends Fragment {

    private RegisterViewModel mViewModel;

    private FragmentRegisterBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RegisterViewModel registerViewModel =
                new ViewModelProvider(this).get(RegisterViewModel.class);

        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.setRegisterViewModel(registerViewModel);
        binding.setLifecycleOwner(this);

        registerViewModel.getEmail().observe(getViewLifecycleOwner(), new Observer<String>() {

            @Override
            public void onChanged(@Nullable String email) {
                if (Patterns.EMAIL_ADDRESS.matcher(email).matches())
                    registerViewModel.errorEmail.setValue("Email ok!");
                else
                    registerViewModel.errorEmail.setValue("Email incorreto!");
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}