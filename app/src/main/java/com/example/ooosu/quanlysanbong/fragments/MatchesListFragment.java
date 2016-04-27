package com.example.ooosu.quanlysanbong.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ooosu.quanlysanbong.R;
import com.example.ooosu.quanlysanbong.activities.MainActivity;
import com.example.ooosu.quanlysanbong.adapter.MatchAdapter;
import com.example.ooosu.quanlysanbong.model.bean.Match;
import com.example.ooosu.quanlysanbong.service.MatchService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 12/31/15.
 */
public class MatchesListFragment extends Fragment{

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.matches_list_layout, container, false);
        //lay du lieu
        MatchService matchService = new MatchService(getActivity().getApplicationContext());
        List<Match> matchList = matchService.getAllMatches();

        //nap adapter
        ListView listView = (ListView) myView.findViewById(R.id.matches_listview);
        MatchAdapter matchAdapter = new MatchAdapter((MainActivity)getActivity(),R.layout.item_match_listview,matchList);
        listView.setAdapter(matchAdapter);
        return myView;
    }
}
