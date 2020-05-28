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
import com.example.sep4androidapp.connection.ApiCallBack;

import java.util.ArrayList;
import java.util.List;

public class RoomsAdapter extends ListAdapter<Device, RoomsAdapter.RoomHolder> {

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
        Device currentDevice = getItem(position);
        holder.roomLabelTextView.setText(currentDevice.getName());

        startStopViewModel.receiveStatus(getItem(position).getDeviceId(), success -> {
           Log.i("StartStopRepo",  "Result is: " + success);
           holder.roomsSwitch.setChecked(success);
        });

        holder.roomsSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked)
            {
                startStopViewModel.start(getItem(position).getDeviceId());
            }else{
                startStopViewModel.stop(getItem(position).getDeviceId());
            }
        });
    }

    public Device getDeviceAt(int position)
    {
        return getItem(position);
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
