package com.example.pruebarxjava.list;

import androidx.annotation.Nullable;

import com.example.pruebarxjava.R;
import com.example.pruebarxjava.http.twitchpojos.Game;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;


public class GameListPresenter implements GameListMVP.Presenter{

    @Nullable
    private GameListMVP.View view;
    private GameListMVP.Model model;

    private Disposable dispGame = null;

    public GameListPresenter(GameListMVP.Model  model) {
        this.model = model;
    }

    @Override
    public void setView(GameListMVP.View view) {
        this.view = view;
    }

    @Override
    public void requestGamesButton() {
        if (view != null){

            List<Game> topGames = new ArrayList<>();

            dispGame = model.getGameList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableObserver<Game>() {
                @Override
                public void onNext(Game value) {
                    topGames.add(value);
                }

                @Override
                public void onError(Throwable e) {
                    handleApiError(e);
                }

                @Override
                public void onComplete() {
                    view.showGameList(topGames);
                }
            });

        }
    }



    @Override
    public void handleApiError(Throwable error) {
        if(error instanceof HttpException){
            switch (((HttpException) error).code()) {
                case 400:
                    view.showError(R.string.error_400);
                    break;
                case 401:
                case 403:
                    view.showError(R.string.error_autorizacion);
                    break;
                case 404:
                    view.showError(R.string.error_dato_no_encontrado);
                    break;
                default:
                    view.showError(R.string.error_servidor);
                    break;
            }
        }
        else if(error instanceof JsonSyntaxException){
            view.showError(R.string.error_json);
        }
        else{
            view.showError(R.string.error_conexion);
        }
    }


    /*
    public interface CustomCallback {
        void onSucess( List<Game> topGames );
        void onFailure();
    }

    @Override
    public void requestGamesButton() {
            if (view != null){
                List<Game> games = model.getGameList(new CustomCallback() {
                    @Override
                    public void onSucess(List<Game> topGames) {
                        view.showGameList(topGames);
                    }

                    @Override
                    public void onFailure() {

                    }
                });
            }
    }*/
}
