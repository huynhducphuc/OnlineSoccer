package com.example.ooosu.quanlysanbong.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ooosu.quanlysanbong.R;
import com.example.ooosu.quanlysanbong.model.bean.Field;
import com.example.ooosu.quanlysanbong.model.bean.Match;
import com.example.ooosu.quanlysanbong.model.bean.User;
import com.example.ooosu.quanlysanbong.service.FieldService;
import com.example.ooosu.quanlysanbong.service.MatchService;
import com.example.ooosu.quanlysanbong.service.SlotService;
import com.example.ooosu.quanlysanbong.service.UserService;
import com.example.ooosu.quanlysanbong.utils.DateUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by oOosu on 4/28/2016.
 */
public class MatchDetailOfMeActivity extends AppCompatActivity {
    ImageButton imgGoToMapAndroid;
    private TextView tv_detail_fieldname,tv_detail_district,tv_detail_hostuser,tv_detail_maxplayers,tv_detail_price,tv_detail_starttime,tv_detail_endtime,tv_detail_created;
    private EditText txt_detail_number;
    private Button btnCancel_detail_ofme;
    private Bundle bundle;
    private int chooise,match_id,user_id;
    private float latitude,longitude;
    private MatchService matchService;
    private Match match;
    private String address,fieldName;

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
        btnCancel_detail_ofme = (Button) findViewById(R.id.btnCancel_detail_ofme);

        bundle = getIntent().getExtras();
        match_id = bundle.getInt("match_id");
        user_id = bundle.getInt("user_id");
        //Lay du lieu cho form
        matchService = new MatchService(this);
        match = matchService.getMatch(match_id);
        Log.d("detail", "" + match.toString());
        List<Field> fieldList = new FieldService(this).getAllFields();
        if(fieldList!=null) {
            for (Field field : fieldList) {
                Log.d("detail2", ""+field.toString());
                if (field.getId() == match.getFieldId()) {
                    fieldName = field.getName();
                    tv_detail_fieldname.setText(fieldName);
                    address = field.getAddress();
                    tv_detail_district.setText(address);
                    latitude = field.getLatitude();
                    longitude = field.getLongitude();
                }
            }
        }
        List<User> userList = new UserService(getApplication().getApplicationContext()).getAllUsers();
        if(userList!=null){
            for (User user: userList){
                if (user.getId()==match.getHostId())
                    tv_detail_hostuser.setText(user.getUsername());
            }
        }
        tv_detail_maxplayers.setText(match.getMaxPlayers()+"");
        tv_detail_price.setText(match.getPrice()+" VND");
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
        if(match.getStartTime().before(DateUtils.convertToTimestamp(new Date()))){
            btnCancel_detail_ofme.setVisibility(View.INVISIBLE);
        }
        btnCancel_detail_ofme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(MatchDetailOfMeActivity.this);
                builder.setTitle("Message");
                builder.setMessage("Do you want to cancel this match?");
                builder.setIcon(android.R.drawable.ic_menu_help);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SlotService slotService = new SlotService(getApplicationContext());
                        slotService.deleteSlot(match_id);
                        matchService.deleteMatch(match);
                        Toast.makeText(getApplicationContext(), "Canceled successfully !", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        Bundle bundle2 = new Bundle();
                        bundle2.putInt("user_id",user_id);
                        bundle2.putString("mymatch","mymatch");
                        intent.putExtras(bundle2);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detail, menu);

        //share
        //  Set up ShareActionProvider's default share intent
        MenuItem shareItem = menu.findItem(R.id.action_share_1);
        ShareActionProvider myShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        Intent myIntent = new Intent();
        myIntent.setAction(Intent.ACTION_SEND);
        myIntent.putExtra(Intent.EXTRA_TEXT,
                fieldName+"\n"+tv_detail_starttime.getText().toString());
        myIntent.setType("text/plain");
        myShareActionProvider.setShareIntent(myIntent);

        return true;
    }
}
