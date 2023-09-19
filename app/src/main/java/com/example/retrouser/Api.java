package com.example.retrouser;

import com.example.retrouser.RetroResponse.FetchUserResponse;
import com.example.retrouser.RetroResponse.LoginResponse;
import com.example.retrouser.RetroResponse.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api
{
    @FormUrlEncoded
    @POST("register.php")
    Call<RegisterResponse> register(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> login(

            @Field("email") String email,
            @Field("password") String password
    );

    @GET("fetchusers.php")
    Call<FetchUserResponse> fetchAllUsers(
         );
}
