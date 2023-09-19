package com.example.retrouser.RetroResponse;

import com.example.retrouser.User;

import java.util.List;

public class FetchUserResponse
{
    List<User> users;
    String error;

    public FetchUserResponse(List<User> users, String error) {
        this.users = users;
        this.error = error;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
