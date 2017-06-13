package com.kadequart.android.gamestash;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kadequart.android.gamestash.adapters.GamesPagerAdapter;

public class MainActivity extends AppCompatActivity {

  private ViewPager viewPager;
  private GamesPagerAdapter pagerAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    viewPager = (ViewPager) findViewById(R.id.view_pager);

    pagerAdapter = new GamesPagerAdapter(getSupportFragmentManager());

    viewPager.setAdapter(pagerAdapter);
  }
}
