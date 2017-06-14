package com.kadequart.android.gamestash.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kadequart.android.gamestash.R;
import com.kadequart.android.gamestash.adapters.GameAdapter;
import com.kadequart.android.gamestash.models.Game;

import java.util.ArrayList;

public class PageFragment extends Fragment {

  public static final String ARG_PAGE = "ARG_PAGE";

  public static final int WISHLIST_PAGE = 0;
  public static final int LIBRARY_PAGE = 1;

  private RecyclerView recyclerView;

  private int page;

  public PageFragment() {
    // Required empty public constructor
  }

  public static PageFragment newInstance(int page) {
    Bundle args = new Bundle();
    args.putInt(ARG_PAGE, page);
    PageFragment fragment = new PageFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    page = getArguments().getInt(ARG_PAGE);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_page, container, false);

    setupViews(view);
    loadGames();

    return view;
  }

  public static String getTitle (int page) {
    switch (page) {
      case WISHLIST_PAGE:
        return "Wishlist";
      case LIBRARY_PAGE:
        return "Library";
      default:
        return "Games List";
    }
  }

  public void setupViews(View view) {
    Activity parentActivity = getActivity();

    recyclerView = (RecyclerView) view.findViewById(R.id.games_recycler_view);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(parentActivity));
    recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
  }

  private void loadGames() {

  }
}
