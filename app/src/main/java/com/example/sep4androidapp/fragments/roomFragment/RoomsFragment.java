package com.example.sep4androidapp.fragments.roomFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sep4androidapp.Adapter.RoomsAdapter;
import com.example.sep4androidapp.Entities.Device;
import com.example.sep4androidapp.Entities.NewDeviceModel;
import com.example.sep4androidapp.R;
import com.example.sep4androidapp.ViewModels.PreferencesViewModel;
import com.example.sep4androidapp.ViewModels.RoomsViewModel;
import com.example.sep4androidapp.fragments.setUpDeviceFragment.SetUpDeviceFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class RoomsFragment extends Fragment {
    private RoomsAdapter adapter;
    private RoomsViewModel viewModel;
    private FloatingActionButton leadToSetUpButton;
    private PreferencesViewModel preferencesViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rooms, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        leadToSetUpButton = view.findViewById(R.id.leadToSetUpButton);
        leadToSetUpButton.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, new SetUpDeviceFragment()).commit();
        });

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
                String name = adapter.getDeviceAt(viewHolder.getAdapterPosition()).getName();
                viewModel.deleteDevice(id);
                //preferencesViewModel.deleteAllDevices();
                //preferencesViewModel.deleteAllPreferences();

                NewDeviceModel saveDevice = new NewDeviceModel(id, name);

                Snackbar.make(recyclerView, id, Snackbar.LENGTH_LONG)
                        .setAction("Undo", v -> {
                            viewModel.postDevice(saveDevice);
                        }).show();
            }
        }).attachToRecyclerView(recyclerView);


        viewModel.updateRooms();
        return view;
    }
}
