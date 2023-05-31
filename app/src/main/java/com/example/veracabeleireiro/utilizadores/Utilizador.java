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
    @SerializedName("discordId")
    private String mDiscordId;
    @SerializedName("pontuacao")
    private Integer mPontuacao;

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

    public String getmDiscordId() {
        if (mDiscordId == null) {
            return "";
        }
        return mDiscordId;
    }

    public void setmDiscordId(String mDiscordId) {
        this.mDiscordId = mDiscordId;
    }

    public Integer getmPontuacao() {
        if (mPontuacao == null) {
            return 0;
        }
        return mPontuacao;
    }

    public void setmPontuacao(Integer mPontuacao) {
        this.mPontuacao = mPontuacao;
    }

    public Utilizador(String username, String email, String password) {
        mUsername = username;
        mEmail = email;
        mPassword = password;
    }

    public Utilizador(String username, String email, String foto, String discord, Integer pontuacao) {
        mUsername = username;
        mEmail = email;
        mFoto = foto;
        mDiscordId = discord;
        mPontuacao = pontuacao;
    }


    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(mEmail).matches();
    }


    public boolean isPasswordLengthGreaterThan5() {
        return mPassword.length() > 5;
    }

}
