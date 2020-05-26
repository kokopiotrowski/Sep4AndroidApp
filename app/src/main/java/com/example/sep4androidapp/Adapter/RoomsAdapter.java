package com.example.sep4androidapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sep4androidapp.Entities.Device;
import com.example.sep4androidapp.R;

public class RoomsAdapter extends ListAdapter<Device, RoomsAdapter.RoomHolder> {

    public RoomsAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Device> DIFF_CALLBACK = new DiffUtil.ItemCallback<Device>() {
        @Override
        public boolean areItemsTheSame(@NonNull Device oldItem, @NonNull Device newItem) {
            return oldItem.getDeviceId() == newItem.getDeviceId();
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
        holder.roomsSwitch.setChecked(true);
    }

    public Device getDeviceAt(int position) {
        return getItem(position);
    }

    class RoomHolder extends RecyclerView.ViewHolder {
        private TextView roomLabelTextView;
        private Switch roomsSwitch;

        public RoomHolder(View itemView) {
            super(itemView);
            roomLabelTextView = itemView.findViewById(R.id.roomLabelTextView);
            roomsSwitch = itemView.findViewById(R.id.roomsSwitch);
        }
    }
}
