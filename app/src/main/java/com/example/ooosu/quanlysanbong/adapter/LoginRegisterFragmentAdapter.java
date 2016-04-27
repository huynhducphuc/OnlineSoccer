package com.example.ooosu.quanlysanbong.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.ooosu.quanlysanbong.fragments.LoginFragment;
import com.example.ooosu.quanlysanbong.fragments.RegisterFragment;

/**
 * Created by oOosu on 4/25/2016.
 */
public class LoginRegisterFragmentAdapter extends FragmentPagerAdapter {
    public LoginRegisterFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                LoginFragment loginFragment = new LoginFragment();
                return loginFragment;
            case 1:
                return new RegisterFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
