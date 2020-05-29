package com.example.sep4androidapp.fragments.factFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.sep4androidapp.R;

import java.util.Objects;

public class FactFragmentDialog extends AppCompatDialogFragment {
    private TextView sourceDialog, urlDialog;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_fact_chosen, null);

        sourceDialog = view.findViewById(R.id.sourceDialog);
        urlDialog = view.findViewById(R.id.urlDialog);


        if(getArguments() != null)
        {
            sourceDialog.setText(getArguments().getString("source"));
            urlDialog.setText(getArguments().getString("url"));
        }

        builder.setView(view);
        return builder.create();
    }
}
