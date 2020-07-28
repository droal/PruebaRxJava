package com.example.pruebarxjava.login;

import dagger.Module;

@Module
public class LoginActivtyModel implements LoginActivityMVP.Model{

    private LoginRepository repository;

    /****Dado que en la vista se injecta el presentador con la anotación:
     *
     @Inject
     LoginActivityMVP.Presenter presenter;

     cuando dagger crea el presentador para injectarlo,y como constructor del presentador
     requiere de una instancia del modelo:

     public LoginActivityPresenter(LoginActivityMVP.Model model)

     y como el constructor del modelo requiere una instancia del repositorio
     , automaticamente lo injecta tambien
     ****/

    public LoginActivtyModel(LoginRepository repository){
        this.repository = repository;
    }


    /**
     * En los métodos del modelo va toda la lógica de negocio (validaciones de formato, transformaciones, comprobaciones, etc)
     * **/


    @Override
    public void createUser(String firstName, String lastName) {
        //Acá iría lógica de negocio (por ejemplo comprobar si el usuario existe, validación del email, rangos de edad etc..)
        repository.saveUser(new UserPojo(firstName, lastName));
    }

    @Override
    public UserPojo getUser() {
        //Acá iría lógica de negocio
        return repository.getUser();
    }
}
