package com.example.pruebarxjava.list;

import android.content.Context;

import com.example.pruebarxjava.root.ApplicationComponent;

import dagger.Module;
import dagger.Provides;

@Module
public class GameListModule {

    @Provides
    public GameListMVP.Presenter provideGameListPresenter(GameListMVP.Model model){
        return new GameListPresenter(model);
    }

    @Provides
    public GameListMVP.Model provideGameListModel(GameListRepository repository){
        return new GameListModel(repository);
    }

    @Provides
    public GameListRepository provideGameListRepository(ApplicationComponent component, Context context){
        return new TwitchApiRepository(component, context);
    }
}
