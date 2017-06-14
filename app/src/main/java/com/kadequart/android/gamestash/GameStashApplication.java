package com.kadequart.android.gamestash;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by jeetkunedo on 14/06/2017.
 */

public class GameStashApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    Realm.init(this);

    if (BuildConfig.DEBUG) {
      Realm.setDefaultConfiguration(new RealmConfiguration.Builder()
          .deleteRealmIfMigrationNeeded()
          .build());

      return;
    }

    Realm.setDefaultConfiguration(new RealmConfiguration.Builder()
        .build());
  }
}
