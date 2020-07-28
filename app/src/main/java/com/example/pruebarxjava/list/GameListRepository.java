package com.example.pruebarxjava.list;

import com.example.pruebarxjava.http.twitchpojos.Game;
import com.example.pruebarxjava.http.twitchpojos.Twitch;

import java.util.List;

import io.reactivex.Observable;

public interface GameListRepository {

    /*List<Game> getGamesList(GameListPresenter.CustomCallback customCallback);*/

    Observable<Twitch> getTopGamesResponse();
}
