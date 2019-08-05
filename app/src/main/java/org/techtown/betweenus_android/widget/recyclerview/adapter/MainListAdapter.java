package org.techtown.betweenus_android.widget.recyclerview.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.techtown.betweenus_android.R;
import org.techtown.betweenus_android.model.Study;
import org.techtown.betweenus_android.view.activity.MainActivity;
import org.techtown.betweenus_android.view.activity.StudyActivity;
import org.techtown.betweenus_android.widget.recyclerview.viewholder.LoadingViewHolder;
import org.techtown.betweenus_android.widget.recyclerview.viewholder.MainListViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Study> studies = new ArrayList<>();
    Context context;
    MainActivity view;

    public MainListAdapter(List<Study> studies, Context context, MainActivity view) {
        this.studies = studies;
        this.context = context;
        this.view = view;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.study_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d("LogPosition", "position = " + position);

        Study study = studies.get(position);

        populateItemRows((MainListViewHolder) holder, study);
    }

    @Override
    public int getItemCount() {
        return studies.size();
    }

    private void populateItemRows(MainListViewHolder viewHolder, Study study) {
        viewHolder.binding.studyTitle.setText(study.getTitle());
        viewHolder.binding.currentPerson.setText(study.getCurrentPerson().toString() + " / ");
        viewHolder.binding.personnel.setText(study.getPersonnel().toString());
        viewHolder.binding.studyPlace.setText(study.getLocation());
        viewHolder.binding.studyTime.setText(study.getStartTerm().split(" ")[0] + " ~ " + study.getEndTerm().split(" ")[0]);
        if (!study.getImgs().isEmpty()) {
            Glide.with(view).load(study.getImgs().get(0)).into(viewHolder.binding.studyImageview);
        }

        viewHolder.binding.studyCardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, StudyActivity.class);
            intent.putExtra("study",study);
            context.startActivity(intent);
        });
    }
}
