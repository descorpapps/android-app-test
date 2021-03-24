package com.testapp.sg.app.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.testapp.sg.app.App;
import com.testapp.sg.app.Interface.IOnItemClickListener;
import com.testapp.sg.app.Models.ProjectModel;
import com.testapp.sg.app.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.Holder> {

    private IOnItemClickListener onItemClickListener;
    private ArrayList<ProjectModel> mProjects = new ArrayList<>();

    public CompanyAdapter(List<ProjectModel> projects, IOnItemClickListener _onItemClickListener) {
        mProjects.addAll(projects);
        onItemClickListener = _onItemClickListener;
    }

    public void addProjects(List<ProjectModel> _projects, boolean refresh) {
        if (mProjects != null) {
            if (refresh) {
                mProjects.clear();
            }
            mProjects.addAll(mProjects.size(), _projects);
        }
    }

    @NonNull
    @Override
    public CompanyAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.project_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final CompanyAdapter.Holder holder, final int position) {
        holder.tvName.setText(mProjects.get(position).getName());
        Glide.with(App.getContext()).load(mProjects.get(position).getLogo_url()).placeholder(R.drawable.default_project_avatar).centerCrop().into(holder.ivUserAvatar);
        holder.cardView.setOnClickListener(v -> onItemClickListener.onItemClickListener(position));
        if (mProjects.get(position).getUsers().size() > 0) {
            holder.workersCounter.setText(mProjects.get(position).getUsers().size() + " Workers");
        }
    }

    @Override
    public int getItemCount() {
        return mProjects.size();
    }


    class Holder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView workersCounter;
        CircleImageView ivUserAvatar;
        CardView cardView;

        Holder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.project_name);
            ivUserAvatar = itemView.findViewById(R.id.project_avatar);
            cardView = itemView.findViewById(R.id.cardView);
            workersCounter = itemView.findViewById(R.id.workers_count);
        }
    }
}

