package org.techtown.betweenus_android.widget.recyclerview.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.betweenus_android.databinding.MemberItemBinding;
import org.techtown.betweenus_android.databinding.QrItemBinding;

public class MemberViewHolder extends RecyclerView.ViewHolder {

    public MemberItemBinding binding;

    public MemberViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }
}
