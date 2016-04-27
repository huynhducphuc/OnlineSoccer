package com.example.ooosu.quanlysanbong.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ooosu.quanlysanbong.R;

/**
 * Created by oOosu on 4/26/2016.
 */
public class MatchDetailActivity extends AppCompatActivity {
    ImageButton imgGoToMapAndroid;
    private Bundle bundle;
    private int chooise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bundle = getIntent().getExtras();
        int match_id = bundle.getInt("match_id");
        chooise = bundle.getInt("chooise");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4CAF50")));
        actionBar.setTitle("Match Detail");

        if(chooise==R.id.nav_matcheslist_layout||chooise==-1){
            setContentView(R.layout.match_detail_layout);
            imgGoToMapAndroid = (ImageButton)findViewById(R.id.imgGoToMapAndroid);
            imgGoToMapAndroid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), GoogleMapActivity.class);
                    startActivity(intent);
                }
            });
        }else{
            setContentView(R.layout.match_detail_ofme_layout);
            imgGoToMapAndroid = (ImageButton)findViewById(R.id.imgGoToMapAndroid);
            imgGoToMapAndroid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), GoogleMapActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Bundle bundleSend = new Bundle();
                bundleSend.putInt("chooise2",chooise);
                getIntent().putExtras(bundleSend);
                setResult(101, getIntent());
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
