package com.example.ooosu.quanlysanbong.activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ooosu.quanlysanbong.adapter.MatchAdapter;
import com.example.ooosu.quanlysanbong.fragments.ChangePasswordFragment;
import com.example.ooosu.quanlysanbong.fragments.MatchesListFragment;
import com.example.ooosu.quanlysanbong.R;
import com.example.ooosu.quanlysanbong.fragments.MyParticipationFragment;
import com.example.ooosu.quanlysanbong.fragments.SetupAMatchFragment;
import com.example.ooosu.quanlysanbong.fragments.ProfileInformationFragment;
import com.example.ooosu.quanlysanbong.fragments.MyMatchesFragment;
import com.example.ooosu.quanlysanbong.service.UserService;
import com.example.ooosu.quanlysanbong.utils.SessionManager;

/**
 * Created by oOosu on 4/21/2016.
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    public FragmentManager fragmentManager;
    public Integer user_id = null ;
    public Menu menu;
    public ActionBar actionBar;
    public int chooise=-1;
    public MatchAdapter matchAdapter = null;
    private String fragment_layout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        Bundle bundle = getIntent().getExtras();
        user_id = bundle.getInt("user_id");
        if(bundle.getString("mymatch")!=null)
            fragment_layout = bundle.getString("mymatch");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4CAF50")));
        actionBar.setTitle("Matches List");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getFragmentManager();
        if(fragment_layout!=null&&fragment_layout.equals("mymatch")){
            fragmentManager.beginTransaction().replace(R.id.content_frame, new MyMatchesFragment()).commit();
            actionBar.setTitle("My matches");
        }else {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new MatchesListFragment()).commit();
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actionbar, menu);
        //searchview
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i("log i", "dakicksearch");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(matchAdapter!=null) matchAdapter.filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        chooise = id;
        fragmentManager = getFragmentManager();
        if (id == R.id.nav_matcheslist_layout) {
            actionBar.setTitle("Matches List");
            menu.findItem(R.id.action_search).setVisible(true);
            fragmentManager.beginTransaction().replace(R.id.content_frame, new MatchesListFragment()).commit();
        } else if (id == R.id.nav_mymatches_layout) {
            actionBar.setTitle("My matches");
            menu.findItem(R.id.action_search).setVisible(true);
            fragmentManager.beginTransaction().replace(R.id.content_frame, new MyMatchesFragment()).commit();
        } else if (id == R.id.nav_myparticipation_layout) {
            actionBar.setTitle("My Participation");
            menu.findItem(R.id.action_search).setVisible(true);
            fragmentManager.beginTransaction().replace(R.id.content_frame, new MyParticipationFragment()).commit();
        }else if (id == R.id.nav_creatematch_layout) {
            actionBar.setTitle("Setup A Match");
            menu.findItem(R.id.action_search).setVisible(false);
            fragmentManager.beginTransaction().replace(R.id.content_frame, new SetupAMatchFragment()).commit();
        } else if (id == R.id.nav_viewprofile_layout) {
            actionBar.setTitle("Profile Infomation");
            menu.findItem(R.id.action_search).setVisible(false);
            fragmentManager.beginTransaction().replace(R.id.content_frame, new ProfileInformationFragment()).commit();
        }else if (id == R.id.nav_changepassword_layout) {
            actionBar.setTitle("Change Password");
            menu.findItem(R.id.action_search).setVisible(false);
            fragmentManager.beginTransaction().replace(R.id.content_frame, new ChangePasswordFragment()).commit();
        }else if (id == R.id.nav_logout_layout) {
            SessionManager.getSessionManager(this).clear();
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100){
            if(resultCode==101) {
                Bundle bundle = data.getExtras();
                int chooise2 = bundle.getInt("chooise2");
                if (chooise2 == 1) {
                    actionBar.setTitle("Matches List");
                    menu.findItem(R.id.action_search).setVisible(true);
                    fragmentManager.beginTransaction().replace(R.id.content_frame, new MatchesListFragment()).commit();
                }
            }
        }else if(requestCode==110){
            if(resultCode==111){
                Bundle bundle = data.getExtras();
                int chooise2 = bundle.getInt("chooise2");
                if (chooise2 == 2) {
                    actionBar.setTitle("My matches");
                    menu.findItem(R.id.action_search).setVisible(true);
                    fragmentManager.beginTransaction().replace(R.id.content_frame, new MyMatchesFragment()).commit();
                }
            }
        }else if(requestCode==120){
            if(resultCode==121){
                Bundle bundle = data.getExtras();
                int chooise2 = bundle.getInt("chooise2");
                if (chooise2 == 3) {
                    actionBar.setTitle("My Participation");
                    menu.findItem(R.id.action_search).setVisible(true);
                    fragmentManager.beginTransaction().replace(R.id.content_frame, new MyParticipationFragment()).commit();
                }
            }
        }
    }
}
