package org.techtown.betweenus_android.widget.recyclerview.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.techtown.betweenus_android.R;
import org.techtown.betweenus_android.model.Study;
import org.techtown.betweenus_android.view.activity.ApplyStudyActivity;
import org.techtown.betweenus_android.view.activity.StudyActivity;
import org.techtown.betweenus_android.view.activity.StudyManageActivity;
import org.techtown.betweenus_android.widget.recyclerview.viewholder.PlaceViewHolder;
import org.techtown.betweenus_android.widget.recyclerview.viewholder.StudyListViewHolder;

import java.util.List;

public class StudyListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Integer APPLY = 0;
    private final Integer FOUND = 1;

    private List<Study> studyList;
    private Context context;
    private AppCompatActivity view;
    private Integer type;

    public StudyListAdapter(List<Study> studyList, Context context, AppCompatActivity view, Integer type) {
        this.studyList = studyList;
        this.context = context;
        this.view = view;
        this.type = type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudyListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.qr_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d("LogPosition", "position = " + position);

        populateItemRows((StudyListViewHolder) holder, studyList.get(position));

    }

    @Override
    public int getItemCount() {
        return studyList.size();
    }

    private void populateItemRows(StudyListViewHolder viewHolder, Study study) {
        viewHolder.binding.studyTitle.setText(study.getTitle());
        viewHolder.binding.startTimeText.setText(study.getStartTime());
        viewHolder.binding.endTimeText.setText(study.getEndTime());
        viewHolder.binding.placeText.setText(study.getLocation());
        if (!study.getImgs().isEmpty()) {
            Glide.with(view).load(study.getImgs().get(0)).into(viewHolder.binding.studyImageview);
        }

        viewHolder.binding.qrCardView.setOnClickListener(v -> {
            if (type == FOUND) {
                Intent intent = new Intent(context, StudyManageActivity.class);
                intent.putExtra("study",study);
                context.startActivity(intent);
            }
            else if (type == APPLY) {

            }
        });
    }
}
