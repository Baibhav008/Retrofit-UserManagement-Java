package com.example.retrouser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrouser.RetroResponse.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateActivity extends AppCompatActivity {

    TextView loginBack;
    AppCompatButton signUp;
    EditText nameText,emailText,passText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        loginBack=findViewById(R.id.loginBack);
        signUp=findViewById(R.id.signUp);
        nameText=findViewById(R.id.nameText);
        passText=findViewById(R.id.passText);
        emailText=findViewById(R.id.emailText);

        loginBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(CreateActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String username = nameText.getText().toString();
                String email = emailText.getText().toString();
                String password = passText.getText().toString();

                if(username.isEmpty())
                {
                    nameText.requestFocus();
                    nameText.setError("Please enter");
                    return;
                }
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

                Call<RegisterResponse> call = RetrofitClient.getInstance().getApi().register(username,email,password);
                call.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response)
                    {
                        RegisterResponse registerResponse = response.body();
                        if(response.isSuccessful())
                        {

                            if(registerResponse.getError().equals("000"))
                            {
                                Intent i = new Intent(CreateActivity.this,MainActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(i);
                                finish();
                            }
                            Toast.makeText(CreateActivity.this, registerResponse.getMessage().toString(), Toast.LENGTH_SHORT).show();


                        }
                        else
                        {
                            Toast.makeText(CreateActivity.this, registerResponse.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t)
                    {
                        Toast.makeText(CreateActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });

    }
}