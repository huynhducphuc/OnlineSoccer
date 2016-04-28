package com.example.ooosu.quanlysanbong.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.ooosu.quanlysanbong.adapter.LoginRegisterFragmentAdapter;
import com.example.ooosu.quanlysanbong.R;
import com.example.ooosu.quanlysanbong.utils.SessionManager;
import com.viewpagerindicator.CirclePageIndicator;

/**
 * Created by oOosu on 4/25/2016.
 */
public class LoginActivity extends AppCompatActivity {
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_register_layout);

        SessionManager sessionManager = SessionManager.getSessionManager(this);
        if(sessionManager.isLoggedIn()){
            Bundle bundle = new Bundle();
            bundle.putInt("user_id",sessionManager.getUser().getId());
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        LoginRegisterFragmentAdapter myFragment = new LoginRegisterFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myFragment);

        CirclePageIndicator circlePageIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        circlePageIndicator.setViewPager(viewPager);
    }

    public void changeViewPagePosition(int position){
        viewPager.setCurrentItem(position, true);
    }
}
