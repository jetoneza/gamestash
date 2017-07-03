package com.kadequart.android.gamestash;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kadequart.android.gamestash.models.Game;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import io.realm.Realm;

public class ViewGameActivity extends AppCompatActivity {

  Realm realm;

  private TextView titleTextView;
  private TextView priceTextView;
  private TextView platformTextView;
  private TextView genreTextView;
  private Button actionButton;
  private ImageView photoImageView;
  private LinearLayout photoLinearLayout;

  private Game game;

  private int gameId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_game);

    gameId = getIntent().getIntExtra(Game.MODEL, 1);

    realm = Realm.getDefaultInstance();

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    initializeViews();
    loadGame();
    initializeListeners();
  }

  @Override
  protected void onResume() {
    super.onResume();

    // TODO: Use better implementation
    // Load game only after edit
    loadGame();
  }

  public void initializeViews () {
    titleTextView = (TextView) findViewById(R.id.text_view_title);
    priceTextView = (TextView) findViewById(R.id.text_view_price);
    platformTextView = (TextView) findViewById(R.id.text_view_platform);
    genreTextView = (TextView) findViewById(R.id.text_view_genre);
    actionButton = (Button) findViewById(R.id.button_action);
    photoLinearLayout = (LinearLayout) findViewById(R.id.linear_layout_photo);
    photoImageView = (ImageView) findViewById(R.id.image_view_photo);

    photoLinearLayout.setVisibility(View.VISIBLE);
    photoImageView.setVisibility(View.GONE);
  }

  public void initializeListeners () {
    actionButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View view) {
        changeGameListType();

        String toastText = "Game";

        toastText += game.getType().equals(Game.WISHLIST) ? " removed from " : " added to ";
        toastText += "library.";

        Toast.makeText(ViewGameActivity.this, toastText, Toast.LENGTH_SHORT).show();

        onBackPressed();
      }
    });
  }

  public void changeGameListType () {
    realm.beginTransaction();

    game.changeListType();

    realm.commitTransaction();
  }

  public void loadGame () {
    game = realm.where(Game.class).equalTo("id", gameId).findFirst();

    titleTextView.setText(game.getTitle());
    platformTextView.setText(game.getPlatform());
    genreTextView.setText(game.getGenre());

    NumberFormat numberFormat = new DecimalFormat("#0.00");

    priceTextView.setText("Php " + numberFormat.format(game.getPrice()));

    String photoUriString = game.getPhotoUriString();

    if (photoUriString != null) {
      photoLinearLayout.setVisibility(View.GONE);
      photoImageView.setVisibility(View.VISIBLE);

      Picasso.with(this).load(photoUriString).fit().centerCrop().into(photoImageView);
    }

    String buttonText = game.getType().equals(Game.WISHLIST) ? "Add to Library" : "Remove from Library";

    actionButton.setText(buttonText);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.view_game_menu, menu);

    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      onBackPressed();

      return true;
    }

    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
      case R.id.action_edit:
        startActivity(AddGameActivity.getIntent(this, game.getId()));

        return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
