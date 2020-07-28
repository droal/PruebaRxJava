package com.example.pruebarxjava.list;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pruebarxjava.R;
import com.example.pruebarxjava.http.twitchpojos.Game;
import com.example.pruebarxjava.root.App;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class GamesListActivity extends AppCompatActivity implements GameListMVP.View{

    @Inject
    GameListMVP.Presenter presenter;

    ListView listView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_list);

        ((App) getApplication()).getComponent().inject(this);

        Button btnSearch = findViewById(R.id.btn_games_list_search);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.requestGamesButton();
            }
        });

        listView = findViewById(R.id.lv_games_list);

    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    @Override
    public void showGameList(List<Game> games) {

        List<String> gamesArray = new ArrayList<>();

        for (Game game : games) {
            gamesArray.add(game.getName());

        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, gamesArray);
        listView.setAdapter(adapter);
        }

    }

    @Override
    public void showError(int idMessage) {
        Toast.makeText(this, getString(idMessage), Toast.LENGTH_LONG).show();
    }
}