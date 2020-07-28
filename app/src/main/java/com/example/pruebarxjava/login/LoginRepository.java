package com.example.pruebarxjava.login;

public interface LoginRepository {
    void saveUser(UserPojo user);

    UserPojo getUser();
}
