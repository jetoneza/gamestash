package com.kadequart.android.gamestash.models;

/**
 * Created by jeetkunedo on 13/06/2017.
 */

public class Game {
  private String title;
  private String platform;
  private String genre;
  private double price;

  public Game (String title, String platform, String genre, double price) {
    this.title = title;
    this.platform = platform;
    this.genre = genre;
    this.price = price;
  }

  /**
   * Setters
   */
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
}