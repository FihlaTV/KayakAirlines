package com.oguzbabaoglu.kayakairlines.features.airlines.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oguzbabaoglu.kayakairlines.R;
import com.oguzbabaoglu.kayakairlines.domain.Airline;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AirlineListAdapter extends RecyclerView.Adapter<AirlineListAdapter.AirlineHolder> {

  interface OnItemClickListener {
    void onItemClicked(int position);
  }

  private final OnItemClickListener listener;

  private List<Airline> airlines;

  public AirlineListAdapter(OnItemClickListener listener) {
    this.listener = listener;
  }

  public void setAirlines(List<Airline> airlines) {
    this.airlines = airlines;
  }

  @Override public AirlineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_airline, parent, false);
    return new AirlineHolder(item, listener);
  }

  @Override public void onBindViewHolder(AirlineHolder holder, int position) {
    Airline airline = airlines.get(position);
    Context context = holder.airlineTextView.getContext();
    String text = context.getString(R.string.airline_list_item_text, airline.displayName(), airline.code());
    holder.airlineTextView.setText(text);
    holder.airlineTextView.setCompoundDrawablesWithIntrinsicBounds(
        0, 0, airline.isStarred() ? R.drawable.ic_star : 0, 0
    );
    int logoSize = context.getResources().getDimensionPixelSize(R.dimen.airline_logo_size);
    Picasso.with(context).load(airline.logoUrl()).resize(logoSize, logoSize).into(holder.airlineLogoImageView);
  }

  @Override public int getItemCount() {
    return airlines.size();
  }

  static class AirlineHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    OnItemClickListener listener;

    @BindView(R.id.airline_item_text) TextView airlineTextView;
    @BindView(R.id.airline_item_image) ImageView airlineLogoImageView;

    public AirlineHolder(View itemView, OnItemClickListener listener) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      this.listener = listener;
    }

    @Override public void onClick(View v) {
      int position = getAdapterPosition();
      listener.onItemClicked(position);
    }
  }
}
