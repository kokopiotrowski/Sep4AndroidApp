package com.example.sep4androidapp.fragments.mainFragment.mainViewFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import com.example.sep4androidapp.Entities.RoomCondition;
import com.example.sep4androidapp.R;
import com.example.sep4androidapp.ViewModels.ReportViewModel;

public class FragmentFirstPage extends Fragment implements View.OnClickListener {

    ReportViewModel viewModel;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    Button button;

    int dummy = 1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_main_firstpage, container, false);
        textView1 = v.findViewById(R.id.textView1);
        textView2 = v.findViewById(R.id.textView2);
        textView3 = v.findViewById(R.id.textView3);
        textView4 = v.findViewById(R.id.textView4);
        textView5 = v.findViewById(R.id.textView5);
        textView6 = v.findViewById(R.id.textView6);
        button = v.findViewById(R.id.buttonTest);
        button.setOnClickListener(this);


        viewModel = new ViewModelProvider(this).get(ReportViewModel.class);
        viewModel.getRoomCondition().observe(getViewLifecycleOwner(), new Observer<RoomCondition>() {
            @Override
            public void onChanged(RoomCondition roomCondition) {
                textView1.setText(roomCondition.getSleepId());
                textView2.setText(roomCondition.getTimeStamp().toString());
                String co2 = Double.toString(roomCondition.getCo2());
                textView3.setText(co2);
                String humidity = Double.toString(roomCondition.getHumidity());
                textView4.setText(humidity);
                String temperature = Double.toString(roomCondition.getTemperature());
                textView4.setText(temperature);
                String sound = Double.toString(roomCondition.getSound());
                textView4.setText(sound);
            }
        });




        return v;



    }
@Override
   public void onClick(View v){

        switch(v.getId()){
            case R.id.buttonTest:
                viewModel.updateRoomCondition(dummy);
            break;
        }

   }

}
