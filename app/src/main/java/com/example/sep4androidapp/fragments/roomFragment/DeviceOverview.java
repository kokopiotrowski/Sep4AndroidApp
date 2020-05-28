package com.example.sep4androidapp.fragments.roomFragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.sep4androidapp.R;

import java.util.Objects;

public class DeviceOverview extends AppCompatDialogFragment {
    private String deviceId, name;
    private TextView deviceIdOverview;
    private TextView nameOverview;

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_setupdevice_overview, null);
        deviceIdOverview = view.findViewById(R.id.deviceIdOverview);
        nameOverview = view.findViewById(R.id.nameOverview);

        if (getArguments() != null) {
            deviceIdOverview.setText(getArguments().getString("deviceId"));
            nameOverview.setText(getArguments().getString("name"));
        }else{
            deviceIdOverview.setText("Retrieving error");
            nameOverview.setText("Retrieving error");
        }

//        Log.i("TAG", "Received args: " + deviceId + " " + name);

        builder.setView(view)
                .setPositiveButton("OK",
                        (dialog, whichButton) -> {}
                )
                .setNeutralButton("Delete room",
                        (dialog, which) -> {

                        });

        return builder.create();
    }
}
