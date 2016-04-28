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
import com.example.ooosu.quanlysanbong.activities.MatchDetailOfMeActivity;
import com.example.ooosu.quanlysanbong.activities.MatchDetailParticipationActivity;
import com.example.ooosu.quanlysanbong.model.bean.ViewMatch;
import com.example.ooosu.quanlysanbong.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by oOosu on 4/26/2016.
 */
public class MatchAdapter extends ArrayAdapter<ViewMatch> {
    private Activity mContext = null;
    private int idLayout;
    List<ViewMatch> viewMatchList = null;
    List<ViewMatch> viewMatchList2 = null;

    public MatchAdapter(Activity mContext, int idLayout, List<ViewMatch> viewMatchList) {
        super(mContext, idLayout, viewMatchList);
        this.mContext = mContext;
        this.idLayout = idLayout;
        this.viewMatchList = viewMatchList;
        viewMatchList2 = new ArrayList<ViewMatch>();
        for(ViewMatch viewMatch: viewMatchList){
            viewMatchList2.add(viewMatch);
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
                ViewMatch viewMatch = viewMatchList.get(position);
                bundle.putInt("match_id", viewMatch.getId());
                bundle.putInt("user_id", SessionManager.getSessionManager(mContext).getUser().getId());
                int chooise = ((MainActivity) mContext).chooise;
                if (chooise == R.id.nav_matcheslist_layout || chooise == -1) {
                    Intent intent1 = new Intent(mContext, MatchDetailActivity.class);
                    intent1.putExtras(bundle);
                    mContext.startActivityForResult(intent1, 100);
                } else if (chooise == R.id.nav_mymatches_layout) {
                    Intent intent2 = new Intent(mContext, MatchDetailOfMeActivity.class);
                    intent2.putExtras(bundle);
                    mContext.startActivityForResult(intent2, 110);
                } else if (chooise == R.id.nav_myparticipation_layout) {
                    Intent intent2 = new Intent(mContext, MatchDetailParticipationActivity.class);
                    intent2.putExtras(bundle);
                    mContext.startActivityForResult(intent2, 120);
                }
            }
        });

        ViewMatch viewMatch = viewMatchList.get(position);

        viewHolder.txtFieldName.setText(viewMatch.getFieldName());
        viewHolder.txtStartTime.setText(viewMatch.getStartTime());
        viewHolder.txtPrice.setText(viewMatch.getPrice()+" VND");
        viewHolder.txtSeats.setText(viewMatch.getSeats()+"");
        notifyDataSetChanged();
        return view;
    }
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        viewMatchList.clear();
        if (charText.length() == 0) {
            viewMatchList.addAll(viewMatchList2);
        }
        else
        {
            for (ViewMatch vm : viewMatchList2)
            {
                if (vm.getFieldName().toLowerCase(Locale.getDefault()).contains(charText)||vm.getStartTime().toLowerCase(Locale.getDefault()).contains(charText)||
                        String.valueOf(vm.getPrice()).contains(charText)||String.valueOf(vm.getSeats()).contains(charText))
                {
                    viewMatchList.add(vm);
                }
            }
        }
        notifyDataSetChanged();
    }
}
