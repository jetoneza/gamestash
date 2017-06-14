package com.kadequart.android.gamestash.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kadequart.android.gamestash.R;
import com.kadequart.android.gamestash.adapters.GameAdapter;
import com.kadequart.android.gamestash.models.Game;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.Sort;

public class PageFragment extends Fragment {

  public static final String ARG_PAGE = "ARG_PAGE";

  public static final int WISHLIST_PAGE = 0;
  public static final int LIBRARY_PAGE = 1;

  private Realm realm;

  private RecyclerView.Adapter adapter;
  private RecyclerView recyclerView;

  private int page;

  private RealmList<Game> games = new RealmList<>();

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

    realm = Realm.getDefaultInstance();
  }

  @Override
  public void onResume() {
    super.onResume();

    // TODO: Use better implementation
    // Load games only after creation, deletion, or update success
    if (adapter == null) {
      initializeAdapter();
    }

    loadGames();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_page, container, false);

    setupViews(view);
    initializeAdapter();
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

  private void setupViews(View view) {
    Activity parentActivity = getActivity();

    recyclerView = (RecyclerView) view.findViewById(R.id.games_recycler_view);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(parentActivity));
    recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
  }

  private void initializeAdapter() {
    adapter = new GameAdapter(games);

    recyclerView.setAdapter(adapter);
  }

  private void loadGames() {
    games.clear();

    // TODO: add filter based on page
    games.addAll(realm.where(Game.class).findAllSorted("price", Sort.ASCENDING));

    Log.d("SIZE ===>", games.size() + "");

    adapter.notifyDataSetChanged();
  }
}
