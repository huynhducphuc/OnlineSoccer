package com.example.ooosu.quanlysanbong.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ooosu.quanlysanbong.R;

/**
 * Created by oOosu on 4/26/2016.
 */
public class ChangePasswordFragment extends Fragment {
    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.change_password_layout,container,false);
        return myView;
    }
}
