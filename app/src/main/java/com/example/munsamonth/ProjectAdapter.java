package com.example.munsamonth;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {

    private ArrayList<FinanceProject> projects;

    public ProjectAdapter(ArrayList<FinanceProject> projects) {
        this.projects = projects;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameText, descText;
        public ViewHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.projectNameText);
            descText = itemView.findViewById(R.id.projectDescText);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FinanceProject project = projects.get(position);
        holder.nameText.setText(project.getName());
        double income = project.calculateIncome();
        double expense = -project.calculateExpenses();
        String description = "MONTHLY INCOME: $" + income + ". EXPENSES: $" + expense + ". GROSS: $" + (income + expense) + ".";
        holder.descText.setText(description);
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    // Optional helper to add items dynamically
    public void addProject(FinanceProject project) {
        projects.add(project);
        notifyItemInserted(projects.size() - 1);
    }
}
