package com.example.pruebarxjava.login;

public class MemoryRepository implements LoginRepository {

    //Para este ejemplo esta variable será la única persistencia (memoria)
    private UserPojo user;

    @Override
    public void saveUser(UserPojo user) {
        if(user == null){
            user = getUser();
        }
        this.user = user;
    }

    @Override
    public UserPojo getUser() {
        /*if(user == null){
            user = new UserPojo("User", "Dummy");
            user.setId(0);
        }*/
        return user;
    }
}
