package com.example.sep4androidapp.fragments.accountFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4androidapp.R;
import com.example.sep4androidapp.ViewModels.AccountViewModel;

public class AccountFragment extends Fragment {

    private EditText nameEditText, surnameEditText, nickEditText, emailEditText, passEditText;
    private AccountViewModel viewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        nameEditText = view.findViewById(R.id.nameView);
        surnameEditText = view.findViewById(R.id.surnameView);
        nickEditText = view.findViewById(R.id.nickView);
        emailEditText = view.findViewById(R.id.emailView);
        passEditText = view.findViewById(R.id.passwordView);

        //viewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        //viewModel.getUserCredentials().observe(getViewLifecycleOwner(), user)

        return view;
    }

}
