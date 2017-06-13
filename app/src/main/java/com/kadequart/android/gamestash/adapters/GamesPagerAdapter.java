package com.kadequart.android.gamestash.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.kadequart.android.gamestash.fragments.PageFragment;

/**
 * Created by jeetkunedo on 13/06/2017.
 */

public class GamesPagerAdapter extends SmartFragmentStatePagerAdapter {

  private static int PAGE_COUNT = 2;

  public GamesPagerAdapter(FragmentManager fragmentManager) {
    super(fragmentManager);
  }

  @Override
  public Fragment getItem(int position) {
    return PageFragment.newInstance(position);
  }

  @Override
  public int getCount() {
    return PAGE_COUNT;
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return PageFragment.getTitle(position);
  }
}
