package com.example.pruebarxjava.login;

import androidx.annotation.Nullable;

public class LoginActivityPresenter implements LoginActivityMVP.Presenter{

    /**El presentador requiere tener referencias tanto de la vista como del modelo
     * dado que el modelo nunca se comunica con la vista**/
    @Nullable
    private LoginActivityMVP.View view;
    private LoginActivityMVP.Model model;

    /****Dado que en la vista se injecta el presentador con la anotación:
            @Inject
            LoginActivityMVP.Presenter presenter;
     cuando dagger crea el presentador para injectarlo, y como el constructor del presentador
     requiere de una instacia del modelo, automaticamente lo injecta tambien
     ****/
    public LoginActivityPresenter(LoginActivityMVP.Model model){
        this.model = model;

    }

    /**Este método es ejecutado en el onResume de la vista*/
    @Override
    public void setView(LoginActivityMVP.View view) {
        this.view = view;
    }

    /**Lógica del botón de la vista*/
    @Override
    public void loginButtonClicked() {
        if(view != null){
            if(view.getFirstName().trim().equals("") || view.getLastName().trim().equals("")){
                view.showInputError();
            }else{
                model.createUser(view.getFirstName().trim(), view.getLastName().trim());
                view.showUserSaved();
                //Authenticar y pasar a listado de juegos
                view.authenticate();
            }
        }
    }

    @Override
    public void getCurrentUser() {
        UserPojo user = model.getUser();

        if (user == null){
            if (view != null){
                view.showUserNotAvailable();
            }
        }else{
            if(view != null){
                view.setFirstName(user.getFirstName());
                view.setLastName(user.getLastName());
            }
        }
    }
}
