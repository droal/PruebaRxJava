package com.example.pruebarxjava.root;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**Clase de Dagger para llevar control de todas las dependencias dentro de la aplicación*/
/**Es posible crear un modulo por funcionalidad de  la aplicacion*/

@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return application;
    }
}
