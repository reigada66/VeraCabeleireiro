package com.example.veracabeleireiro.utilizadores;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.veracabeleireiro.R;
import com.example.veracabeleireiro.databinding.FragmentLoginBinding;
import android.util.Patterns;

public class LoginFragment extends Fragment {


    private FragmentLoginBinding loginbinding;
    private NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        LoginViewModel loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

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

        // Observe the estado LiveData
        loginViewModel.getEstado().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean estado) {
                if (estado) {
                    // Navigate to nav_home using the navController
 //                   navController.navigate(R.id.action_nav_login_to_nav_home);
                    navController.navigate(R.id.nav_home); // Update to nav_home destination

                }
            }
        });


        loginViewModel.getUsername().observe(getViewLifecycleOwner(), new Observer<String>() {

            @Override
            public void onChanged(@Nullable String username) {
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