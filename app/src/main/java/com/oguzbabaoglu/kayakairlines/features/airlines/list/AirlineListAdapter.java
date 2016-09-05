package com.oguzbabaoglu.kayakairlines.features.airlines.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.oguzbabaoglu.kayakairlines.R;
import com.oguzbabaoglu.kayakairlines.domain.Airline;
import com.oguzbabaoglu.kayakairlines.features.airlines.starred.StarredAirlineHelper;
import com.oguzbabaoglu.kayakairlines.util.ListUtil;
import com.oguzbabaoglu.kayakairlines.util.RemoveWhiteTransformation;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AirlineListAdapter extends RecyclerView.Adapter<AirlineListAdapter.AirlineHolder>
    implements Filterable {

  interface OnItemClickListener {
    void onItemClicked(Airline airline);
  }

  private final OnItemClickListener listener;
  private final Transformation logoTransformation;
  private final AirlineFilter airlineFilter;
  private final StarredAirlineHelper starredAirlineHelper;
  private final List<Airline> allAirlines;

  private List<Airline> filteredAirlines;

  public AirlineListAdapter(List<Airline> airlines, StarredAirlineHelper starredAirlineHelper,
                            OnItemClickListener listener) {
    this.listener = listener;
    this.allAirlines = airlines;
    this.filteredAirlines = airlines;
    this.airlineFilter = new AirlineFilter();
    this.logoTransformation = new RemoveWhiteTransformation();
    this.starredAirlineHelper = starredAirlineHelper;
  }

  @Override public AirlineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_airline, parent, false);
    return new AirlineHolder(item);
  }

  @Override public void onBindViewHolder(AirlineHolder holder, int position) {
    Airline airline = filteredAirlines.get(position);
    Context context = holder.airlineTextView.getContext();
    String text = context.getString(R.string.airline_list_item_text, airline.name(), airline.code());
    holder.airlineTextView.setText(text);
    holder.airlineStarImageView.setVisibility(
        starredAirlineHelper.isStarred(airline.code()) ? View.VISIBLE : View.INVISIBLE
    );
    int logoWidth = context.getResources().getDimensionPixelSize(R.dimen.airline_logo_width);
    int logoHeight = context.getResources().getDimensionPixelSize(R.dimen.airline_logo_height);
    Picasso.with(context)
        .load(airline.logoUrl())
        .placeholder(R.drawable.airline_logo_placeholder)
        .resize(logoWidth, logoHeight)
        .transform(logoTransformation)
        .into(holder.airlineLogoImageView);
  }

  @Override public int getItemCount() {
    return filteredAirlines.size();
  }

  class AirlineHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.airline_item_text) TextView airlineTextView;
    @BindView(R.id.airline_item_image) ImageView airlineLogoImageView;
    @BindView(R.id.airline_item_star) ImageView airlineStarImageView;

    public AirlineHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      itemView.setOnClickListener(this);
    }

    @Override public void onClick(View v) {
      int position = getAdapterPosition();
      listener.onItemClicked(filteredAirlines.get(position));
    }
  }

  @Override public Filter getFilter() {
    return airlineFilter;
  }

  class AirlineFilter extends Filter {
    @Override protected FilterResults performFiltering(CharSequence constraint) {
      FilterResults results = new FilterResults();
      if (TextUtils.isEmpty(constraint)) {
        results.count = allAirlines.size();
        results.values = allAirlines;
        return results;
      }
      List<Airline> filteredValues = ListUtil.filter(allAirlines, airline -> airline.matches(constraint));
      results.count = filteredValues.size();
      results.values = filteredValues;
      return results;
    }

    @SuppressWarnings("unchecked")
    @Override protected void publishResults(CharSequence constraint, FilterResults results) {
      filteredAirlines = (List<Airline>) results.values;
      notifyDataSetChanged();
    }
  }

  public void notifyAirlineItemChanged(String changedAirlineCode) {
    int size = filteredAirlines.size();
    for (int i = 0; i < size; i++) {
      if (filteredAirlines.get(i).code().equals(changedAirlineCode)) {
        notifyItemChanged(i);
        return;
      }
    }
  }

  public void addAirline(Airline airline, String filter) {
    allAirlines.add(airline);
    ListUtil.sort(allAirlines);
    // Reapply filter
    getFilter().filter(filter);
  }

  public void removeAirline(Airline airline) {
    int index = filteredAirlines.indexOf(airline);
    if (index != -1) {
      filteredAirlines.remove(index);
      notifyItemRemoved(index);
    }
    if (filteredAirlines != allAirlines) {
      allAirlines.remove(airline);
    }
  }

}
