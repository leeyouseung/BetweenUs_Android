package org.techtown.betweenus_android.widget.recyclerview.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.betweenus_android.R;
import org.techtown.betweenus_android.widget.recyclerview.viewholder.PlaceViewHolder;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> places;
    Context context;

    public PlaceAdapter(List<String> places, Context context) {
        this.places = places;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlaceViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.place_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d("LogPosition", "position = " + position);

        populateItemRows((PlaceViewHolder) holder, position);

    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    private void populateItemRows(PlaceViewHolder viewHolder, int position) {

    }
}
