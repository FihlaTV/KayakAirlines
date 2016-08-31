package com.oguzbabaoglu.kayakairlines.features.airlines.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oguzbabaoglu.kayakairlines.R;
import com.oguzbabaoglu.kayakairlines.domain.Airline;

import java.util.List;

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
  }

  @Override public int getItemCount() {
    return airlines.size();
  }

  static class AirlineHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    OnItemClickListener listener;
    TextView airlineTextView;

    public AirlineHolder(View itemView, OnItemClickListener listener) {
      super(itemView);
      this.airlineTextView = (TextView) itemView;
      this.listener = listener;
    }

    @Override public void onClick(View v) {
      int position = getAdapterPosition();
      listener.onItemClicked(position);
    }
  }
}
