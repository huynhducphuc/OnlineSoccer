package com.example.ooosu.quanlysanbong.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ooosu.quanlysanbong.R;
import com.example.ooosu.quanlysanbong.activities.MainActivity;
import com.example.ooosu.quanlysanbong.activities.MatchDetailActivity;
import com.example.ooosu.quanlysanbong.model.bean.Field;
import com.example.ooosu.quanlysanbong.model.bean.Match;
import com.example.ooosu.quanlysanbong.service.FieldService;
import com.example.ooosu.quanlysanbong.service.SlotService;
import com.example.ooosu.quanlysanbong.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oOosu on 4/26/2016.
 */
public class MatchAdapter extends ArrayAdapter<Match> {
    private Activity mContext = null;
    private int idLayout;
    private String fieldName = "Field Name";
    List<Match> matchesList = null;
    List<Field> fieldList = null;
    private SlotService slotService = null;
    private List<Long> seatsList = null;

    public MatchAdapter(Activity mContext, int idLayout, List<Match> matchesList) {
        super(mContext, idLayout, matchesList);
        this.mContext = mContext;
        this.idLayout = idLayout;
        this.matchesList = matchesList;
        this.fieldList = new FieldService(mContext).getAllFields();
        this.slotService = new SlotService(mContext);
        seatsList = new ArrayList<Long>();
        for(Match match : matchesList){
            seatsList.add(match.getMaxPlayers()-slotService.countSlots(match.getId())-1);
        }
    }
    public MatchAdapter(Context context,int resource){
        super(context,resource);
    }
    static class ViewHolder{
        TextView txtFieldName;
        TextView txtStartTime;
        TextView txtPrice;
        TextView txtSeats;

    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(view!=null){
            viewHolder = (ViewHolder) view.getTag();
        }else{
            viewHolder = new ViewHolder();
            LayoutInflater layout = mContext.getLayoutInflater();
            view = layout.inflate(idLayout,null);
            viewHolder.txtFieldName = (TextView) view.findViewById(R.id.txtFieldName);
            viewHolder.txtStartTime = (TextView) view.findViewById(R.id.txtStartTime);
            viewHolder.txtPrice = (TextView) view.findViewById(R.id.txtPrice);
            viewHolder.txtSeats = (TextView) view.findViewById(R.id.txtSeats);
            view.setTag(viewHolder);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Match matches = matchesList.get(position);
                bundle.putInt("match_id", matches.getId());
                bundle.putInt("chooise", ((MainActivity) mContext).chooise);
                Intent intent = new Intent(mContext, MatchDetailActivity.class);
                intent.putExtras(bundle);
                mContext.startActivityForResult(intent, 100);
            }
        });

        Match match = matchesList.get(position);
        if(fieldList!=null){
            for(Field field : fieldList)
                if(field.getId()==match.getFieldId())
                    fieldName = field.getName();
        }
        viewHolder.txtFieldName.setText(fieldName);
        viewHolder.txtStartTime.setText(DateUtils.formatDatetime(match.getStartTime(),DateUtils.FOR_SCREEN));
        viewHolder.txtPrice.setText(match.getPrice()+" VND");
        viewHolder.txtSeats.setText(seatsList.get(position)+"");
        notifyDataSetChanged();
        return view;
    }
}
