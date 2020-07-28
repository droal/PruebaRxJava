package com.example.pruebarxjava.list;

import com.example.pruebarxjava.http.twitchpojos.Game;
import com.example.pruebarxjava.http.twitchpojos.Twitch;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class GameListModel implements GameListMVP.Model {

    private GameListRepository gameListRepository;

    public GameListModel(GameListRepository gameListRepository) {
        this.gameListRepository = gameListRepository;
    }


    @Override
    public Observable<Game> getGameList() {
        return Observable.wrap(gameListRepository.getTopGamesResponse()
                .concatMap(new Function<Twitch, ObservableSource<Game>>() {
                    @Override
                    public ObservableSource<Game> apply(Twitch twitch) throws Exception {
                        return Observable.fromIterable(twitch.getGame());
                    }
                }));
    }


/*    @Override
    public List<Game> getGameList(GameListPresenter.CustomCallback customCallback) {
        return gameListRepository.getGamesList(customCallback);
    }*/
}
