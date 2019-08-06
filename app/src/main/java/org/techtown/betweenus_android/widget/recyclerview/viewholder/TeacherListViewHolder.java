package org.techtown.betweenus_android.widget.recyclerview.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.betweenus_android.databinding.TeacherStudyItemBinding;

public class TeacherListViewHolder extends RecyclerView.ViewHolder {

    public TeacherStudyItemBinding binding;

    public TeacherListViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);

        binding.teacherStudyTime.setSelected(true);
    }
}
