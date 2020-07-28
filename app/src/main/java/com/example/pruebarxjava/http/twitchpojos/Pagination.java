package com.example.pruebarxjava.http.twitchpojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**POJO que representa el objeto que permite la función de paginación de los resultados de la consulta*/
public class Pagination {

    @SerializedName("cursor")
    @Expose
    private String cursor;

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

}
