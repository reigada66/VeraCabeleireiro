package com.example.veracabeleireiro.utilizadores;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.Call;
import retrofit2.Response;
import androidx.navigation.NavController;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.veracabeleireiro.R;

import retrofit2.Callback;public class LoginViewModel extends ViewModel {

    public interface ApiService {
        @GET("csrf-token/")
        Call<ResponseBody> getCsrfToken();

        @POST("login/")
        Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);
    }

    private Context context; // Add a Context field

    public MutableLiveData<String> errorPassword = new MutableLiveData<>();
    public MutableLiveData<String> errorUsername = new MutableLiveData<>();

    public MutableLiveData<String> username = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    private MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<Boolean> estado = new MutableLiveData<>();
    public MutableLiveData<Boolean> registado = new MutableLiveData<>();
    public MutableLiveData<String> mensagem = new MutableLiveData<>();

    public MutableLiveData<String> getUsername(){
        return username;
    }
    LiveData<Boolean> getEstado() {return estado; }
    LiveData<Boolean> getRegistado() {return registado; }


    public LoginViewModel() {
        registado.setValue(false);
    }


    @SuppressLint("RestrictedApi")
    public void onRegistarClicked() {
        registado.setValue(true);
//        errorUsername.setValue("estala");

    }
    private NavController navController;

    public void setNavController(NavController navController) {
        this.navController = navController;
    }

    public void onLoginClicked() {
        mensagem.setValue("a tentar");

        // Create a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://leonardomcp640.pythonanywhere.com/users/api/")  // Replace with your Django server's API base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an API service interface
        ApiService apiService = retrofit.create(ApiService.class);

        // Make a request to fetch the CSRF token
        Call<ResponseBody> csrfTokenCall = apiService.getCsrfToken();
        csrfTokenCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    // CSRF token fetched successfully, proceed with login request
 // Fetch the CSRF token
                    String csrfToken = response.headers().get("csrftoken");

// Create a LoginRequest object with the username, password, and CSRF token
                    LoginRequest loginRequest = new LoginRequest(username.getValue(), password.getValue(), csrfToken);

                    mensagem.setValue("request");

                    // Make the login request using the fetched CSRF token
                    Call<LoginResponse> loginCall = apiService.loginUser(loginRequest);
                    loginCall.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (response.isSuccessful()) {
                                // Login successful, handle the response
                                LoginResponse loginResponse = response.body();
                                estado.setValue(true);
                                mensagem.setValue("sucesso");

                                // You can access the response data and perform necessary actions

                                // Navigate to fragment_home
                                navController.navigate(R.id.nav_home);
                            } else {
                                Log.d("LoginResponse", "Response code: " + response.code());
                                Log.d("LoginResponse", "Response body: " + response.body());

                                // Handle the error based on the response code and message
                                if (response.code() == 400) {
                                    // Invalid credentials
                                    mensagem.setValue("Invalid credentials. Please try again.");
                                } else {
                                    // Other error
                                    mensagem.setValue("Login failed. Please try again later.");
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            mensagem.setValue("falha de rede");
                            // Handle network failure or other errors
                        }
                    });
                } else {
                    // Handle the error
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // Handle network failure or other errors
            }
        });
    }
}