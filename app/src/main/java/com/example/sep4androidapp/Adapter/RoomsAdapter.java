package com.example.sep4androidapp.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sep4androidapp.Entities.Device;
import com.example.sep4androidapp.R;
import com.example.sep4androidapp.ViewModels.StartStopViewModel;

import java.util.ArrayList;
import java.util.List;

public class RoomsAdapter extends ListAdapter<Device, RoomsAdapter.RoomHolder> {

    private List<Device> devices = new ArrayList<>();
    private StartStopViewModel startStopViewModel = new StartStopViewModel();

    public RoomsAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Device> DIFF_CALLBACK = new DiffUtil.ItemCallback<Device>() {
        @Override
        public boolean areItemsTheSame(@NonNull Device oldItem, @NonNull Device newItem) {
            return oldItem.getDeviceId().equals(newItem.getDeviceId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Device oldItem, @NonNull Device newItem) {
            return oldItem.getDeviceId().equals(newItem.getDeviceId()) &&
                    oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getUserId().equals(newItem.getUserId());
        }
    };

    @NonNull
    @Override
    public RoomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.room_item, parent, false);
        return new RoomHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomHolder holder, int position) {
        Device currentDevice = devices.get(position);
        holder.roomLabelTextView.setText(currentDevice.getName());

        startStopViewModel.receiveStatus(devices.get(position).getDeviceId());

        holder.roomsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    startStopViewModel.start(devices.get(position).getDeviceId());
                }else{
                    startStopViewModel.stop(devices.get(position).getDeviceId());
                }
            }
        });
    }

    public void setDevices(List<Device> devices)
    {
        this.devices = devices;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    class RoomHolder extends RecyclerView.ViewHolder {
        private TextView roomLabelTextView;
        private Switch roomsSwitch;

        RoomHolder(View itemView) {
            super(itemView);
            roomLabelTextView = itemView.findViewById(R.id.roomLabelTextView);
            roomsSwitch = itemView.findViewById(R.id.roomsSwitch);
        }
    }
}
