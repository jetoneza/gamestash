package com.kadequart.android.gamestash.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kadequart.android.gamestash.R;
import com.kadequart.android.gamestash.models.Game;

import java.util.ArrayList;

/**
 * Created by jeetkunedo on 13/06/2017.
 */

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {

  private ArrayList<Game> data;

  public static class ViewHolder extends RecyclerView.ViewHolder {
    public View view;
    public ViewHolder(View view) {
      super(view);

      this.view = view;
    }
  }


  public GameAdapter(ArrayList<Game> data) {
    this.data = data;
  }

  @Override
  public GameAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.games_list_item, parent, false);

    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    View rowView = holder.view;

    Game game = data.get(position);

    TextView titleTextView = (TextView) rowView.findViewById(R.id.text_view_title);
    titleTextView.setText(game.getTitle());

    TextView priceTextView = (TextView) rowView.findViewById(R.id.text_view_price);
    priceTextView.setText("Php " + game.getPrice());
  }

  @Override
  public int getItemCount() {
    return data.size();
  }
}
