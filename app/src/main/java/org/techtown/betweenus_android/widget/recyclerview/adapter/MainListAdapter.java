package org.techtown.betweenus_android.widget.recyclerview.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.betweenus_android.R;
import org.techtown.betweenus_android.model.Study;
import org.techtown.betweenus_android.widget.recyclerview.viewholder.LoadingViewHolder;
import org.techtown.betweenus_android.widget.recyclerview.viewholder.MainListViewHolder;

import java.util.List;

public class MainListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private List<Study> studies;
    Context context;

    public MainListAdapter(List<Study> studies, Context context) {
        this.studies = studies;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            return new MainListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.study_item, parent, false));
        } else {
            return new LoadingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.study_loading, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d("LogPosition", "position = " + position);

        Study study = studies.get(position);

        if (holder instanceof MainListViewHolder) {
            populateItemRows((MainListViewHolder) holder, position, study);
        } else if (holder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) holder, position);
        }

    }

    @Override
    public int getItemCount() {
        return studies.size();
    }

    @Override
    public int getItemViewType(int position) {
        return studies.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    private void populateItemRows(MainListViewHolder viewHolder, int position, Study study) {
        viewHolder.binding.studyTitle.setText(study.getTitle());
        viewHolder.binding.currentPerson.setText(study.getCurrentPerson());
        viewHolder.binding.personnel.setText(study.getPersonnel());
        viewHolder.binding.studyPlace.setText(study.getLocation());

        //todo 사진 추가
    }

    private void showLoadingView(LoadingViewHolder viewHolder, int position) {

    }
}
