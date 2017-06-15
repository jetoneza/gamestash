package com.kadequart.android.gamestash.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kadequart.android.gamestash.R;
import com.kadequart.android.gamestash.ViewGameActivity;
import com.kadequart.android.gamestash.adapters.GameAdapter;
import com.kadequart.android.gamestash.models.Game;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.Sort;

public class PageFragment extends Fragment {

  public static final String ARG_PAGE = "ARG_PAGE";

  public static final int WISHLIST_PAGE = 0;
  public static final int LIBRARY_PAGE = 1;

  public static final String[] PAGES = { Game.WISHLIST, Game.LIBRARY };

  private Realm realm;

  private RecyclerView.Adapter adapter;
  private RecyclerView recyclerView;
  private TextView textViewEmpty;

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

  private View.OnClickListener onClickListener = new View.OnClickListener() {

    @Override
    public void onClick(View view) {
      RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(view);

      Game game = games.get(holder.getAdapterPosition());

      Intent intent = new Intent(getActivity(), ViewGameActivity.class);
      intent.putExtra(Game.MODEL, game.getId());

      startActivity(intent);
    }
  };

  private RecyclerView.OnChildAttachStateChangeListener attachListener = new RecyclerView.OnChildAttachStateChangeListener() {

    @Override
    public void onChildViewAttachedToWindow(View view) {
      view.setOnClickListener(onClickListener);
    }

    @Override
    public void onChildViewDetachedFromWindow(View view) {}
  };

  private void setupViews(View view) {
    Activity parentActivity = getActivity();

    recyclerView = (RecyclerView) view.findViewById(R.id.games_recycler_view);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(parentActivity));
    recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
    recyclerView.addOnChildAttachStateChangeListener(attachListener);

    textViewEmpty = (TextView) view.findViewById(R.id.text_view_empty);
    textViewEmpty.setVisibility(View.GONE);
  }

  private void initializeAdapter() {
    adapter = new GameAdapter(games);

    recyclerView.setAdapter(adapter);
  }

  private void loadGames() {
    games.clear();

    games.addAll(realm.where(Game.class).contains("type", PAGES[page]).findAllSorted("price", Sort.ASCENDING));

    if (games.size() == 0) {
      textViewEmpty.setVisibility(View.VISIBLE);
    } else {
      textViewEmpty.setVisibility(View.GONE);
    }

    adapter.notifyDataSetChanged();
  }
}
