package com.example.sep4androidapp.fragments.factFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sep4androidapp.Adapter.FactAdapter;
import com.example.sep4androidapp.Adapter.RoomsAdapter;
import com.example.sep4androidapp.R;
import com.example.sep4androidapp.ViewModels.FactViewModel;
import com.example.sep4androidapp.ViewModels.RoomsViewModel;

public class FactFragment extends Fragment {
    private FactAdapter adapter;
    private FactViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fact, container, false);

//        RecyclerView recyclerView = view.findViewById(R.id.factRecyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setHasFixedSize(true);
//
//        adapter = new FactAdapter();
//        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(FactViewModel.class);
        viewModel.getFacts().observe(getViewLifecycleOwner(),facts -> adapter.setFacts(facts));


        viewModel.updateFacts();
        return view;
    }

}
