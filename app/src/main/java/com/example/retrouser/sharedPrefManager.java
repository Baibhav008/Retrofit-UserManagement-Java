package com.example.retrouser;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;

public class sharedPrefManager
{
    String SHARED_PREF_NAME="retroapp";
    SharedPreferences sharedPreferences;
    Context context;
    SharedPreferences.Editor editor;

    public sharedPrefManager(Context context) {
        this.context = context;
    }

    void SaveUser(User user)
    {
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.putInt("id", user.getId());
        editor.putString("username",user.getUsername());
        editor.putString("email",user.getEmail());
        editor.putBoolean("logged",true);
        editor.apply();
    }

    boolean isLoggedin()
    {
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("logged",false);

    }

    public User getUser()
    {
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return  new User(sharedPreferences.getInt("id",-1),
                sharedPreferences.getString("username",null),
                sharedPreferences.getString("email",null ));
    }

    void logout()
    {
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
