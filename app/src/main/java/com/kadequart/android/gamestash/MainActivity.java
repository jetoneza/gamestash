package com.kadequart.android.gamestash;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.kadequart.android.gamestash.adapters.GamesPagerAdapter;

public class MainActivity extends AppCompatActivity {

  private ViewPager viewPager;
  private GamesPagerAdapter pagerAdapter;
  private TabLayout tabLayout;
  private FloatingActionButton addButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    viewPager = (ViewPager) findViewById(R.id.view_pager);
    tabLayout = (TabLayout) findViewById(R.id.page_tabs);
    addButton = (FloatingActionButton) findViewById(R.id.button_add);

    pagerAdapter = new GamesPagerAdapter(getSupportFragmentManager());

    viewPager.setAdapter(pagerAdapter);
    tabLayout.setupWithViewPager(viewPager);

    addButton.setOnClickListener(new ClickListener(this));
  }

  private class ClickListener implements View.OnClickListener {
    Activity parentActivity;

    ClickListener(Activity parentActivity) {
      this.parentActivity = parentActivity;
    }

    @Override
    public void onClick(View view) {
      Intent intent = null;

      switch (view.getId()) {
        case R.id.button_add:
          intent = new Intent(parentActivity, AddGameActivity.class);
          break;
      }

      startActivity(intent);
    }
  }
}
