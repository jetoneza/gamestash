package com.kadequart.android.gamestash.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kadequart.android.gamestash.R;
import com.kadequart.android.gamestash.models.Game;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import io.realm.RealmList;

/**
 * Created by jeetkunedo on 13/06/2017.
 */

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {

  private RealmList<Game> data;
  private Context parentContext;

  public static class ViewHolder extends RecyclerView.ViewHolder {
    public View view;
    public ViewHolder(View view) {
      super(view);

      this.view = view;
    }
  }


  public GameAdapter(RealmList<Game> data) {
    this.data = data;
  }

  @Override
  public GameAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    parentContext = parent.getContext();
    View view = LayoutInflater.from(parentContext).inflate(R.layout.games_list_item, parent, false);

    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    View rowView = holder.view;

    Game game = data.get(position);

    TextView titleTextView = (TextView) rowView.findViewById(R.id.text_view_title);
    titleTextView.setText(game.getTitle());

    TextView priceTextView = (TextView) rowView.findViewById(R.id.text_view_price);

    String priceString = "FREE";

    double price = game.getPrice();

    if (price > 0) {
      NumberFormat numberFormat = new DecimalFormat("#0.00");

      priceString = "Php " + numberFormat.format(price);
    }

    priceTextView.setText(priceString);

    RelativeLayout photoRelativeLayout = (RelativeLayout) rowView.findViewById(R.id.relative_layout_photo);
    ImageView photoImageView = (ImageView) rowView.findViewById(R.id.image_view_photo);

    photoRelativeLayout.setVisibility(View.VISIBLE);
    photoImageView.setVisibility(View.GONE);

    String photoUriString = game.getPhotoUriString();

    if (photoUriString != null) {
      photoRelativeLayout.setVisibility(View.GONE);
      photoImageView.setVisibility(View.VISIBLE);

      Picasso.with(parentContext).load(photoUriString).fit().centerCrop().into(photoImageView);
    }
  }

  @Override
  public int getItemCount() {
    return data.size();
  }
}
