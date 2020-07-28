package com.example.pruebarxjava.http;

import com.example.pruebarxjava.http.twitchpojos.Twitch;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**En esta interfaz se declaran todos los métodos de consumo de la API Rest*/
public interface TwitchAPIService {



    //Mediante anotaciones se declara el verbo y el end point
    //Con el método Call se indica el tipo de respuesta esperado
    /*@GET("games/top")
    Call<Twitch> getTopGames(@Header("Authorization") String authorization, @Header("Client-ID") String clientId);
*/


    //Empleando reactivex el resultado de la busqueda será manejado por un objeto Observable
    @GET("games/top")
    Observable<Twitch> getTopGamesObservable(@Header("Authorization") String authorization, @Header("Client-ID") String clientId);
}
