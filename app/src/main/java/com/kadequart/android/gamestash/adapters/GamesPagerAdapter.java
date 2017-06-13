package com.kadequart.android.gamestash.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.kadequart.android.gamestash.fragments.LibraryFragment;
import com.kadequart.android.gamestash.fragments.WishlistFragment;

/**
 * Created by jeetkunedo on 13/06/2017.
 */

public class GamesPagerAdapter extends FragmentPagerAdapter {

  private static int ITEMS_SIZE = 2;

  public GamesPagerAdapter(FragmentManager fragmentManager) {
    super(fragmentManager);
  }

  @Override
  public Fragment getItem(int position) {
    switch (position) {
      case 0:
        return WishlistFragment.newInstance();
      case 1:
        return LibraryFragment.newInstance();
      default:
        return null;
    }
  }

  @Override
  public int getCount() {
    return ITEMS_SIZE;
  }

  @Override
  public CharSequence getPageTitle(int position) {
    switch (position) {
      case 0:
        return "Wishlist";
      case 1:
        return "Library";
      default:
        return "Games List";
    }
  }
}
