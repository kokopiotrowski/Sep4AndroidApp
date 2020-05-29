package com.example.sep4androidapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sep4androidapp.Entities.Fact;
import com.example.sep4androidapp.R;

import java.util.ArrayList;
import java.util.List;

public class FactAdapter extends RecyclerView.Adapter< FactAdapter.FactHolder > {
    //private FactViewModel viewModel = new FactViewModel();
    private List< Fact > facts = new ArrayList<>();

    @NonNull
    @Override
    public FactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fact_item, parent, false);
        return new FactHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FactHolder holder, int position) {
        Fact currentFact = facts.get(position);
        holder.textViewTitle.setText(currentFact.getTitle());
        holder.textViewContent.setText(currentFact.getContent());
    }

    @Override
    public int getItemCount() {
        return facts.size();
    }

    public void setFacts(List< Fact > facts) {
        this.facts = facts;
        notifyDataSetChanged();
    }

    class FactHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewContent;

        public FactHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.factTitleTextView);
            textViewContent = itemView.findViewById(R.id.factTextView);
        }
    }
}
