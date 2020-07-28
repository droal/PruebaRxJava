package com.example.pruebarxjava.list;

import com.example.pruebarxjava.http.twitchpojos.Game;

import java.util.List;

import io.reactivex.Observable;

public interface GameListMVP {

    interface View{
        void showGameList(List<Game> games);
        void showError(int idMessage);
    }

    interface Presenter{
        void setView(GameListMVP.View view);
        void requestGamesButton();
        void handleApiError(Throwable error);
    }

    interface Model{
        Observable<Game> getGameList();
    }

    /*interface Model{
        List<Game> getGameList(GameListPresenter.CustomCallback customCallback);
    }*/
}

