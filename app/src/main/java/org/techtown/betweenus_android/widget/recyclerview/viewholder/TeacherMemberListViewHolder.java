package org.techtown.betweenus_android.widget.recyclerview.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.betweenus_android.databinding.TeacherMemberItemBinding;
import org.techtown.betweenus_android.databinding.TeacherStudyItemBinding;

public class TeacherMemberListViewHolder extends RecyclerView.ViewHolder {

    public TeacherMemberItemBinding binding;

    public TeacherMemberListViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }
}
