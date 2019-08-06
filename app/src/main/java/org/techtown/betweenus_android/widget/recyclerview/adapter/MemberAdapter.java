package org.techtown.betweenus_android.widget.recyclerview.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.techtown.betweenus_android.R;
import org.techtown.betweenus_android.model.Member;
import org.techtown.betweenus_android.model.Study;
import org.techtown.betweenus_android.view.activity.StudyManageActivity;
import org.techtown.betweenus_android.widget.recyclerview.viewholder.MemberViewHolder;
import org.techtown.betweenus_android.widget.recyclerview.viewholder.StudyListViewHolder;

import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Member> members;
    private Context context;
    private AppCompatActivity view;

    public MemberAdapter(List<Member> members, Context context, AppCompatActivity view) {
        this.members = members;
        this.context = context;
        this.view = view;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MemberViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.member_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        populateItemRows((MemberViewHolder) holder, members.get(position));

    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    private void populateItemRows(MemberViewHolder viewHolder, Member member) {
        viewHolder.binding.nameText.setText(member.getName());
        viewHolder.binding.gradeClassText.setText(member.getGrade() + "학년 " + member.getSchoolClass() + "반");
        if (member.getprofileImg() != null) {
            Glide.with(view).load(member.getprofileImg()).into(viewHolder.binding.studyImageview);
        }
    }
}
