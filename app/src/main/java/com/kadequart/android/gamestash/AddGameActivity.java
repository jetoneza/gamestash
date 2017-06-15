package com.kadequart.android.gamestash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kadequart.android.gamestash.models.Game;
import com.kadequart.android.gamestash.utils.RealmUtils;

import io.realm.Realm;

public class AddGameActivity extends AppCompatActivity {

  private Realm realm;

  private Button submitButton;
  private EditText titleEditText;
  private EditText priceEditText;
  private EditText platformEditText;
  private EditText genreEditText;
  private LinearLayout photoLinearLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_game);

    realm = Realm.getDefaultInstance();

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setTitle("Add Game");

    initializeViews();
    initializeListeners();
  }

  public void initializeViews () {
    submitButton = (Button) findViewById(R.id.button_submit);
    titleEditText = (EditText) findViewById(R.id.edit_text_title);
    priceEditText = (EditText) findViewById(R.id.edit_text_price);
    platformEditText = (EditText) findViewById(R.id.edit_text_platform);
    genreEditText = (EditText) findViewById(R.id.edit_text_genre);
    photoLinearLayout = (LinearLayout) findViewById(R.id.linear_layout_photo);
  }

  public void initializeListeners () {
    submitButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View view) {
        addGame();
      }
    });
    photoLinearLayout.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View view) {
        Toast.makeText(AddGameActivity.this, "Not yet implemented!", Toast.LENGTH_SHORT).show();
      }
    });
  }

  public void addGame () {
    String title = titleEditText.getText().toString();
    String priceText = priceEditText.getText().toString();
    String platform = platformEditText.getText().toString();
    String genre = genreEditText.getText().toString();

    double price = Double.parseDouble(priceText);

    //TODO: add validations

    realm.beginTransaction();

    Game game = realm.createObject(Game.class);
    game.setId(RealmUtils.getNextId(realm, Game.class));
    game.setTitle(title);
    game.setPlatform(platform);
    game.setGenre(genre);
    game.setPrice(price);

    realm.commitTransaction();

    Toast.makeText(this, "Game added to wishlist!", Toast.LENGTH_SHORT).show();

    onBackPressed();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      onBackPressed();

      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
