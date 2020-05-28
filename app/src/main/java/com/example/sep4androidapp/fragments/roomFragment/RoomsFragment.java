package com.example.sep4androidapp.fragments.roomFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sep4androidapp.Adapter.RoomsAdapter;
import com.example.sep4androidapp.Entities.Device;
import com.example.sep4androidapp.R;
import com.example.sep4androidapp.ViewModels.RoomsViewModel;

import java.util.List;
import java.util.Objects;

public class RoomsFragment extends Fragment {
    private RoomsAdapter adapter;
    private RoomsViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rooms, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        adapter = new RoomsAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(RoomsViewModel.class);
        viewModel.getDevices().observe(getViewLifecycleOwner(), devices -> adapter.submitList(devices));

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                String id = adapter.getDeviceAt(viewHolder.getAdapterPosition()).getDeviceId();
                Log.i("TAG", "Device to delete: " + id);
                viewModel.deleteDevice(id);

            }
        }).attachToRecyclerView(recyclerView);

        viewModel.updateRooms();
        return view;
    }
}
