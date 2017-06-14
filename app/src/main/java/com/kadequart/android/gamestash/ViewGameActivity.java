package com.kadequart.android.gamestash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.kadequart.android.gamestash.models.Game;

public class ViewGameActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_game);

    int id = getIntent().getIntExtra(Game.MODEL, 1);

    Log.d("GAME ID:", id + "");
  }
}
