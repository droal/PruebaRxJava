package com.example.pruebarxjava.http.twitchpojos;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**POJO que representa o almacena un arreglo con los objetos "Game" resultado de la consulta*/
public class Twitch {

    @SerializedName("data")
    @Expose
    private List<Game> game = null;
    @SerializedName("pagination")
    @Expose
    private Pagination pagination;

    public List<Game> getGame() {
        return game;
    }

    public void setGame(List<Game> game) {
        this.game = game;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

}