package com.kadequart.android.gamestash.models;

import io.realm.RealmObject;

/**
 * Created by jeetkunedo on 13/06/2017.
 */

public class Game extends RealmObject {
  private int id;
  private String title;
  private String platform;
  private String genre;
  private double price;

  public Game() {}

  public Game (String title, String platform, String genre, double price) {
    this.title = title;
    this.platform = platform;
    this.genre = genre;
    this.price = price;
  }

  /**
   * Setters
   */
  public void setId(int id) {
    this.id = id;
  }

  public void setTitle (String title) {
    this.title = title;
  }

  public void setPlatform (String platform) {
    this.platform = platform;
  }

  public void setGenre (String genre) {
    this.genre = genre;
  }

  public void setPrice (double price) {
    this.price = price;
  }

  /**
   * Getters
   */
  public String getTitle () {
    return title;
  }

  public String getPlatform () {
    return platform;
  }

  public String getGenre () {
    return genre;
  }

  public double getPrice () {
    return price;
  }

  public String toString() {
    String game = "Title: " + title + "\n";

    game += "Price: " + price + "\n";
    game += "Platform: " + platform + "\n";
    game += "Genre: " + genre + "\n";

    return game;
  }
}
