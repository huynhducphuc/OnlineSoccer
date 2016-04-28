package com.example.ooosu.quanlysanbong.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ooosu.quanlysanbong.R;
import com.example.ooosu.quanlysanbong.model.bean.Field;
import com.example.ooosu.quanlysanbong.model.bean.Match;
import com.example.ooosu.quanlysanbong.service.FieldService;
import com.example.ooosu.quanlysanbong.service.MatchService;
import com.example.ooosu.quanlysanbong.utils.DateUtils;

import java.util.List;

/**
 * Created by oOosu on 4/28/2016.
 */
public class MatchDetailActivityOfMe extends AppCompatActivity {
    ImageButton imgGoToMapAndroid;
    private TextView tv_detail_fieldname,tv_detail_district,tv_detail_hostuser,tv_detail_maxplayers,tv_detail_price,tv_detail_starttime,tv_detail_endtime,tv_detail_created;
    private EditText txt_detail_number;
    private Button btnJoinMatch;
    private Bundle bundle;
    private int chooise;
    private float latitude,longitude;
    private String address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_detail_ofme_layout);
        //set actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4CAF50")));
        actionBar.setTitle("Match Detail");

        tv_detail_fieldname = (TextView) findViewById(R.id.tv_detail_fieldname);
        tv_detail_district = (TextView) findViewById(R.id.tv_detail_district);
        tv_detail_hostuser = (TextView) findViewById(R.id.tv_detail_hostuser);
        tv_detail_maxplayers = (TextView) findViewById(R.id.tv_detail_maxplayers);
        tv_detail_price = (TextView) findViewById(R.id.tv_detail_price);
        tv_detail_starttime = (TextView) findViewById(R.id.tv_detail_starttime);
        tv_detail_endtime = (TextView) findViewById(R.id.tv_detail_endtime);
        tv_detail_created = (TextView) findViewById(R.id.tv_detail_created);

        txt_detail_number = (EditText) findViewById(R.id.txt_detail_number);
        btnJoinMatch = (Button) findViewById(R.id.btnJoinMatch);

        bundle = getIntent().getExtras();
        int match_id = bundle.getInt("match_id");
        //Lay du lieu cho form
        Match match = new MatchService(this).getMatch(match_id);
        Log.d("detail", "" + match.toString());
        List<Field> fieldList = new FieldService(this).getAllFields();
        if(fieldList!=null) {
            for (Field field : fieldList) {
                Log.d("detail2", ""+field.toString());
                if (field.getId() == match.getFieldId()) {
                    tv_detail_fieldname.setText(field.getName());
                    address = field.getAddress();
                    tv_detail_district.setText(address);
                    latitude = field.getLatitude();
                    longitude = field.getLongitude();
                }
            }
        }
        if(match.getHostId()==0) tv_detail_hostuser.setText("Admin");
        else tv_detail_hostuser.setText("User");
        tv_detail_maxplayers.setText(match.getMaxPlayers()+"");
        tv_detail_price.setText(match.getPrice()+"");
        tv_detail_starttime.setText(DateUtils.formatDatetime(match.getStartTime(), DateUtils.FOR_SCREEN));
        tv_detail_endtime.setText(DateUtils.formatDatetime(match.getEndTime(),DateUtils.FOR_SCREEN));
        tv_detail_created.setText(DateUtils.formatDatetime(match.getCreatedDate(),DateUtils.FOR_SCREEN));
        imgGoToMapAndroid = (ImageButton)findViewById(R.id.imgGoToMapAndroid);
        imgGoToMapAndroid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle2 = new Bundle();
                bundle2.putFloat("latitude", latitude);
                bundle2.putFloat("longitude", longitude);
                bundle2.putString("address", address);
                Intent intent = new Intent(getApplicationContext(), GoogleMapActivity.class);
                intent.putExtras(bundle2);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Bundle bundleSend = new Bundle();
                bundleSend.putInt("chooise2",2);
                getIntent().putExtras(bundleSend);
                setResult(111, getIntent());
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
