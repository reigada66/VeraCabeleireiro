package com.example.veracabeleireiro.utilizadores;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.veracabeleireiro.R;
import com.example.veracabeleireiro.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {


    private FragmentLoginBinding loginbinding;
    private NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LoginViewModel loginViewModel =
                new ViewModelProvider(this).get(LoginViewModel.class);

        loginbinding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = loginbinding.getRoot();
        loginbinding.setLoginViewModel(loginViewModel);
        loginbinding.setLifecycleOwner(this);

        loginViewModel.getRegistado().observe(getViewLifecycleOwner(), new Observer<Boolean>() {

            @Override
            public void onChanged(@Nullable Boolean registo) {
                Toast.makeText(getActivity(), "email : ", Toast.LENGTH_SHORT).show();
                if (registo) {
                    navController.navigate(R.id.action_nav_login_to_nav_register);
                }
            }
        });

        loginViewModel.getEmail().observe(getViewLifecycleOwner(), new Observer<String>() {

            @Override
            public void onChanged(@Nullable String email) {
                if (Patterns.EMAIL_ADDRESS.matcher(email).matches())
                    loginViewModel.errorEmail.setValue("Email ok!");
                else
                    loginViewModel.errorEmail.setValue("Email incorreto!");
            }
        });

        loginViewModel.getUser().observe(getViewLifecycleOwner(), new Observer<Utilizador>() {

            @Override
            public void onChanged(@Nullable Utilizador user) {
                if (user.getmEmail().length() > 0 || user.getmPassword().length() > 0)
                    Toast.makeText(getActivity(), "email : " + user.getmEmail() + " password " + user.getmPassword(), Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.navController = Navigation.findNavController(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        loginbinding = null;
    }
}