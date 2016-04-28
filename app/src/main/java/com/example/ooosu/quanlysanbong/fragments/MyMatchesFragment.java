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
import com.example.ooosu.quanlysanbong.model.bean.Field;
import com.example.ooosu.quanlysanbong.model.bean.Match;
import com.example.ooosu.quanlysanbong.model.bean.ViewMatch;
import com.example.ooosu.quanlysanbong.service.FieldService;
import com.example.ooosu.quanlysanbong.service.MatchService;
import com.example.ooosu.quanlysanbong.service.SlotService;
import com.example.ooosu.quanlysanbong.utils.DateUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by oOosu on 4/26/2016.
 */
public class MyMatchesFragment extends Fragment {
    View myView;
    int id,price;
    String fieldName,startTime;
    long seats;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.matches_list_layout, container, false);

        List<ViewMatch> viewMatchList = new ArrayList<ViewMatch>();
        // lay id match
        MatchService matchService = new MatchService(getActivity().getApplicationContext());
        List<Match> matchesList = matchService.getAllMatches(((MainActivity) getActivity()).user_id);
        // lay field name
        List<Field> fieldList = new FieldService(getActivity().getApplicationContext()).getAllFields();
        //lay slot
        SlotService slotService = new SlotService(getActivity().getApplicationContext());
        //day du lieu vao viewMatchList
        for(Match match : matchesList){
            id = match.getId();
            if(fieldList!=null){
                for(Field field : fieldList)
                    if(field.getId()==match.getFieldId())
                        fieldName = field.getName();
            }
            price = match.getPrice();
            startTime = DateUtils.formatDatetime(match.getStartTime(), DateUtils.FOR_SCREEN);
            seats = match.getMaxPlayers()-slotService.countSlots(match.getId())-1;

            viewMatchList.add(new ViewMatch(id,fieldName,price,startTime,seats));
        }

        //nap adapter
        ListView listView = (ListView) myView.findViewById(R.id.matches_listview);
        MatchAdapter matchAdapter = new MatchAdapter((MainActivity)getActivity(),R.layout.item_match_listview,viewMatchList);
        listView.setAdapter(matchAdapter);
        ((MainActivity)getActivity()).matchAdapter = matchAdapter;
        return myView;
    }
}
