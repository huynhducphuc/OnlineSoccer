package com.example.ooosu.quanlysanbong.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ooosu.quanlysanbong.R;
import com.example.ooosu.quanlysanbong.activities.MainActivity;
import com.example.ooosu.quanlysanbong.activities.MatchDetailActivity;
import com.example.ooosu.quanlysanbong.model.bean.Match;

import java.util.List;

/**
 * Created by oOosu on 4/26/2016.
 */
public class MatchAdapter extends ArrayAdapter<Match> {
    private Activity mContext = null;
    private int idLayout;
    List<Match> matchList = null;

    public MatchAdapter(Activity mContext, int idLayout, List<Match> matchList) {
        super(mContext, idLayout, matchList);
        this.mContext = mContext;
        this.idLayout = idLayout;
        this.matchList = matchList;
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
                Match matches = matchList.get(position);
                bundle.putInt("match_id", matches.getId());
                bundle.putInt("chooise", ((MainActivity) mContext).chooise);
                Intent intent = new Intent(mContext, MatchDetailActivity.class);
                intent.putExtras(bundle);
                mContext.startActivityForResult(intent, 100);
            }
        });

        Match match = matchList.get(position);
        viewHolder.txtFieldName.setText("Nguyễn Chánh FC " + match.getFieldId());
        viewHolder.txtStartTime.setText("0:20-26/04/16 (Còn 2 ngày nữa)");
        viewHolder.txtPrice.setText(match.getPrice()+"");
        viewHolder.txtSeats.setText(match.getMaxPlayers()+"");
        notifyDataSetChanged();
        return view;
    }
}
