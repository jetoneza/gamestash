package com.kadequart.android.gamestash.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kadequart.android.gamestash.R;

public class PageFragment extends Fragment {

  public static final String ARG_PAGE = "ARG_PAGE";

  public static final int WISHLIST_PAGE = 0;
  public static final int LIBRARY_PAGE = 1;

  private int mPage;

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
    mPage = getArguments().getInt(ARG_PAGE);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_page, container, false);

    TextView textView = (TextView) view.findViewById(R.id.text_view_title);
    textView.setText(getTitle(mPage));

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
}
