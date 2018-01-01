package com.example.yuquan.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.Entity.Event;
import com.example.yuquan.Home;
import com.example.yuquan.R;

import java.util.ArrayList;

/**
 * Created by terry on 2017/12/30.
 */

public class ActAdapter extends BaseAdapter {

    class ActHolder {
        public ImageView usricon;
        public TextView evname, evtime, evtext, joined, capa;
    }
    private Home home = null;
    public ArrayList<Event> eventList = null;

    public ActAdapter(Home home, ArrayList<Event> eventList) {
        this.home = home;
        this.eventList = eventList;
    }

    @Override
    public int getCount() {
        return this.eventList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return this.eventList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //记载活动的每条需要显示在什么布局上的布局对象
        ActHolder ah = null;
        //创建一个层次对应组件的类
        if (convertView == null) {
            convertView = LayoutInflater.from(this.home.getApplicationContext()).inflate(R.layout.act_frame, null);
            //将对应的组件和对象进行关联，提高效率
            ah = new ActHolder();
            //创建一个层次对应组件的类
            ah.usricon = (ImageView) convertView.findViewById(R.id.usricon);
            ah.evname = (TextView) convertView.findViewById(R.id.evname);
            ah.evtime = (TextView) convertView.findViewById(R.id.evtime);
            ah.evtext = (TextView) convertView.findViewById(R.id.evtext);
            ah.joined = (TextView) convertView.findViewById(R.id.joined);
            ah.capa = (TextView) convertView.findViewById(R.id.capa);
            convertView.setTag(ah);
        }else {
            ah = (ActHolder)convertView.getTag();
        }

        ah.evname.setText(eventList.get(position).EventName);
        ah.evtime.setText(eventList.get(position).EventTime);
        ah.evtext.setText(eventList.get(position).Description);
        ah.joined.setText(String.format("%d", eventList.get(position).Joined));
        ah.capa.setText(String.format("%d", eventList.get(position).Capacity));
        return convertView;
    }
}
