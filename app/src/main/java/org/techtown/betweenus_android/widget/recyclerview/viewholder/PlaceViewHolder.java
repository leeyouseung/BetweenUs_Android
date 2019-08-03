package org.techtown.betweenus_android.widget.recyclerview.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.betweenus_android.databinding.PlaceItemBinding;
import org.techtown.betweenus_android.databinding.StudyItemBinding;

public class PlaceViewHolder extends RecyclerView.ViewHolder {

    public PlaceItemBinding binding;

    public PlaceViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }
}
