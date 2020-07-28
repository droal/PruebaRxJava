package com.example.pruebarxjava.login;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @Provides
    public LoginActivityMVP.Presenter provideLoginActivityPresenter(LoginActivityMVP.Model model){
        return new LoginActivityPresenter(model);
    }

    @Provides
    public LoginActivityMVP.Model provideLoginActivityModel(LoginRepository repository){
        return  new LoginActivtyModel(repository);
    }

    @Provides
    public LoginRepository provideLoginRepository(){
        //Aca se debe cambiar, si la fuente de datos cambia, por ejemplosi se usa una base de datos o datos en cloud, etc
        return new MemoryRepository();
    }
}
