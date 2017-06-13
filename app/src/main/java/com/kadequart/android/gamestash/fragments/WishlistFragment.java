package com.kadequart.android.gamestash.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kadequart.android.gamestash.R;

public class WishlistFragment extends Fragment {

  public WishlistFragment() {
    // Required empty public constructor
  }

  public static WishlistFragment newInstance() {
    WishlistFragment fragment = new WishlistFragment();
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_wishlist, container, false);
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
  }
}
