package com.example.sep4androidapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.sep4androidapp.R;

import java.util.ArrayList;
import java.util.List;

public class RoomsAdapter extends RecyclerView.Adapter<RoomsAdapter.RoomsHolder> {
    private List<Room> rooms = new ArrayList<>();

    @NonNull
    @Override
    public RoomsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_item, parent, false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RoomsHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class RoomsHolder extends RecyclerView.ViewHolder{
        private TextView roomLabelTextView;
        private Switch roomsSwitch;

        public RoomsHolder(@NonNull View itemView) {
            super(itemView);
            roomLabelTextView = itemView.findViewById(R.id.roomLabelTextView);
            roomsSwitch = itemView.findViewById(R.id.roomsSwitch);
        }
    }
}
