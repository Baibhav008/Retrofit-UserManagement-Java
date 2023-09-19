package com.example.retrouser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrouser.RetroResponse.LoginResponse;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextView signUpBack;
    AppCompatButton Login;
    EditText emailText,passText;
    sharedPrefManager sharedPrefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        signUpBack=findViewById(R.id.signUpBack);
        Login=findViewById(R.id.Login);

        passText=findViewById(R.id.passText);
        emailText=findViewById(R.id.emailText);
        sharedPrefManager = new sharedPrefManager(getApplicationContext());

        signUpBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(LoginActivity.this,CreateActivity.class);
                startActivity(i);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {


                String email = emailText.getText().toString();
                String password = passText.getText().toString();

                if(email.isEmpty())
                {
                    emailText.requestFocus();
                    emailText.setError("Please enter");
                    return;
                }
                if(password.isEmpty())
                {
                    passText.requestFocus();
                    passText.setError("Please enter");
                    return;

                }

                Call<LoginResponse> call = RetrofitClient.getInstance().getApi().login(email,password);
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                        LoginResponse loginResponse = response.body();
                        if(response.isSuccessful())
                        {
                            if(loginResponse.getError().toString().equals("000"))
                            {

                                sharedPrefManager.SaveUser(loginResponse.getUser());
                                Intent i = new Intent(LoginActivity.this,MainActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(i);
                            }
                            Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();


                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(sharedPrefManager.isLoggedin())
        {
            Intent i = new Intent(LoginActivity.this,MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);

        }
    }
}