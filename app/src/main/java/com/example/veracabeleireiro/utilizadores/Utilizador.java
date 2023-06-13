package com.example.veracabeleireiro.utilizadores;

import android.util.Patterns;

import com.google.gson.annotations.SerializedName;

public class Utilizador {
    @SerializedName("username")
    private String mUsername;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("password")
    private String mPassword;
    @SerializedName("foto")
    private String mFoto;

    public String getmUsername() {
        return mUsername;
    }

    public void setmUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getmEmail() {
        if (mEmail == null) {
            return "";
        }
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPassword() {
        if (mPassword == null) {
            return "";
        }
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getmFoto() {
        if (mFoto == null) {
            return "";
        }
        return mFoto;
    }

    public void setmFoto(String mFoto) {
        this.mFoto = mFoto;
    }


    public Utilizador(String username, String password) {
        mUsername = username;
        mPassword = password;
    }

    public Utilizador(String username, String email, String foto) {
        mUsername = username;
        mEmail = email;
        mFoto = foto;
    }


    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(mEmail).matches();
    }


    public boolean isUsernameValid() {
        return mUsername.length() > 5;
    }


    public boolean isPasswordLengthGreaterThan5() {
        return mPassword.length() > 5;
    }

}
