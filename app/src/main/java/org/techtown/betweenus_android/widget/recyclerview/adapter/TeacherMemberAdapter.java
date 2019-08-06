package org.techtown.betweenus_android.widget.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.techtown.betweenus_android.R;
import org.techtown.betweenus_android.model.Member;
import org.techtown.betweenus_android.model.Study;
import org.techtown.betweenus_android.view.activity.TeacherActivity;
import org.techtown.betweenus_android.view.activity.TeacherMemberActivity;
import org.techtown.betweenus_android.widget.recyclerview.viewholder.TeacherListViewHolder;
import org.techtown.betweenus_android.widget.recyclerview.viewholder.TeacherMemberListViewHolder;

import java.util.List;

public class TeacherMemberAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Member> members;
    private Context context;
    TeacherMemberActivity view;

    public TeacherMemberAdapter(List<Member> members, Context context, TeacherMemberActivity view) {
        this.members = members;
        this.context = context;
        this.view = view;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TeacherMemberListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_member_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Member member = members.get(position);

        populateItemRows((TeacherMemberListViewHolder) holder, member);
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    private void populateItemRows(TeacherMemberListViewHolder viewHolder, Member member) {
        viewHolder.binding.teacherMemberNameText.setText(member.getName());
        viewHolder.binding.teacherGradeClassText.setText(member.getGrade() + "학년 " + member.getSchoolClass() + "반");
        if (member.getprofileImg() != null) {
            Glide.with(view).load(member.getprofileImg()).into(viewHolder.binding.teacherStudyImageview);
        }
        viewHolder.binding.teacherMemberStudyStatus.setText(Integer.toString(member.getStatus()));
        if (member.getStatus() == 1) {
            viewHolder.binding.teacherMemberStatusLight.setImageResource(R.drawable.loading_light);
            viewHolder.binding.teacherMemberStudyStatus.setText("수업 참석");
        }
        else {
            viewHolder.binding.teacherMemberStatusLight.setImageResource(R.drawable.join_light);
            viewHolder.binding.teacherMemberStudyStatus.setText("수업 불참");
        }
    }
}
