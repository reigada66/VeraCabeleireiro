package com.example.veracabeleireiro.utilizadores;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegisterViewModel extends ViewModel {
    public MutableLiveData<String> errorPassword = new MutableLiveData<>();
    public MutableLiveData<String> errorEmail = new MutableLiveData<>();

    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> username = new MutableLiveData<>();
    public MutableLiveData<Boolean> estado = new MutableLiveData<>();

    public MutableLiveData<Integer> busy;

    LiveData<String> getEmail(){
        return email;
    }
    LiveData<Boolean> getEstado() {return estado; }
    public MutableLiveData<Integer> getBusy() {

        if (busy == null) {
            busy = new MutableLiveData<>();
            busy.setValue(8);
        }

        return busy;
    }


    public RegisterViewModel() {
        estado.setValue(false);
    }

    private MutableLiveData<Utilizador> userMutableLiveData;

    LiveData<Utilizador> getUser() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }

        return userMutableLiveData;
    }


    public void onLoginClicked() {

        getBusy().setValue(0); //View.VISIBLE
        estado.setValue(true);
    }
}