package com.example.veracabeleireiro.utilizadores;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("message")
    private String message;

    // Add getters and setters for the message field

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}