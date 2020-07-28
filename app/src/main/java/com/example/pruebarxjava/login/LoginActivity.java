package com.example.pruebarxjava.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pruebarxjava.R;
import com.example.pruebarxjava.http.TwitchAPIService;
import com.example.pruebarxjava.http.twitchpojos.TokenResponse;
import com.example.pruebarxjava.http.TwitchAuthService;
import com.example.pruebarxjava.list.GamesListActivity;
import com.example.pruebarxjava.root.App;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements LoginActivityMVP.View{

    @Inject
    LoginActivityMVP.Presenter presenter;

    @Inject
    TwitchAPIService twitchAPIService;
    @Inject
    TwitchAuthService twitchAuthService;

    EditText etName, etApellido;
    Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App) getApplication()).getComponent().inject(this);

        etName = findViewById(R.id.et_login_nombre);
        etApellido = findViewById(R.id.et_login_apellido);
        btnEntrar = findViewById(R.id.btn_login_entrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loginButtonClicked();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);

        //Si el usuario se ha logeado  previamente, precargar los datos
        //presenter.getCurrentUser();
    }

    @Override
    public String getFirstName() {
        return this.etName.getText().toString();
    }

    @Override
    public String getLastName() {
        return this.etApellido.getText().toString();
    }

    @Override
    public void showUserNotAvailable() {
        Toast.makeText(this, "Error, el usuario no está disponible", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInputError() {
        Toast.makeText(this, "Error, nombre y apellido son necesarios", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserSaved() {
        Toast.makeText(this, "¡Usuario guardado correctamente!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setFirstName(String firstName) {
        this.etName.setText(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        this.etApellido.setText(lastName);
    }

    @Override
    public void authenticate() {
        Call<TokenResponse> call = twitchAuthService.authentication("tuev4s9hwme3ddcgvogj1psrk0h3qp", "7npkwnqy6ccy8a6mm59r7t8nvwid5w", "client_credentials");

        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("AppPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("token", response.body().getAccessToken());
                editor.commit();

                Intent intent = new Intent(getApplicationContext(), GamesListActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {

            }
        });

    }
}
