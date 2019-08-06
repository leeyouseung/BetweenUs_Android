package org.techtown.betweenus_android.widget.recyclerview.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.techtown.betweenus_android.R;
import org.techtown.betweenus_android.manager.Token;
import org.techtown.betweenus_android.manager.ViewModelFactory;
import org.techtown.betweenus_android.model.Member;
import org.techtown.betweenus_android.model.Study;
import org.techtown.betweenus_android.network.Response;
import org.techtown.betweenus_android.network.api.MemberApi;
import org.techtown.betweenus_android.utils.Utils;
import org.techtown.betweenus_android.view.activity.StudyActivity;
import org.techtown.betweenus_android.view.activity.TeacherActivity;
import org.techtown.betweenus_android.view.activity.TeacherMemberActivity;
import org.techtown.betweenus_android.viewmodel.MemberViewModel;
import org.techtown.betweenus_android.viewmodel.StudyViewModel;
import org.techtown.betweenus_android.widget.recyclerview.viewholder.MainListViewHolder;
import org.techtown.betweenus_android.widget.recyclerview.viewholder.TeacherListViewHolder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class TeacherStudyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Study> studies;
    private Context context;
    TeacherActivity view;

    public TeacherStudyAdapter(List<Study> studies, Context context, TeacherActivity view) {
        this.studies = studies;
        this.context = context;
        this.view = view;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TeacherListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_study_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Log.d("LogPosition", "position = " + position);

        Study study = studies.get(position);

        populateItemRows((TeacherListViewHolder) holder, study);
    }

    @Override
    public int getItemCount() {
        return studies.size();
    }

    private void populateItemRows(TeacherListViewHolder viewHolder, Study study) {
        viewHolder.binding.teacherStudyTitle.setText(study.getTitle());
        viewHolder.binding.teacherCurrentPerson.setText(study.getCurrentPerson().toString() + " / ");
        viewHolder.binding.teacherPersonnel.setText(study.getPersonnel().toString());
        viewHolder.binding.teacherStudyPlace.setText(study.getLocation());
        viewHolder.binding.teacherStudyTime.setText(study.getStartTerm().split(" ")[0] + " ~ " + study.getEndTerm().split(" ")[0]);

        if (!study.getImgs().isEmpty()) {
            Glide.with(view).load(study.getImgs().get(0)).into(viewHolder.binding.teacherStudyImageview);
        }

        viewHolder.binding.teacherStudyCardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, TeacherMemberActivity.class);
            intent.putExtra("study",study);
            context.startActivity(intent);
        });
    }
}
