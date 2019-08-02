package org.techtown.betweenus_android.widget.recyclerview.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.betweenus_android.databinding.StudyItemBinding;

public class MainListViewHolder extends RecyclerView.ViewHolder {
    public StudyItemBinding binding;

    public MainListViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }
}
