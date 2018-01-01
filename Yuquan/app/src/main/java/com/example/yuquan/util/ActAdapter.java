package com.example.yuquan.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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
    public ArrayList<Event> actList = null;

    public ActAdapter(Home home, ArrayList<Event> actList) {
        this.home = home;
        this.actList = actList;
    }

    @Override
    public int getCount() {
        return this.actList.size();
    }

    @Override
    public long getItemId(int position) {
        return this.actList.get(position);
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //记载活动的每条需要显示在什么布局上的布局对象
        convertView = LayoutInflater.from(this.home.getApplicationContext()).inflate(R.layout.act_frame, null);
        //创建一个层次对应组件的类
        ActHolder ah = null;
        if (convertView == null) {
            //创建一个层次对应组件的类
            ah = new ActHolder();
            //将对应的组件和对象进行关联，提高效率
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

        ah.evname.setText((String)actList.get(position).getevname());
        ah.evtime.setText((String)actList.get(position).getevtime());
        ah.evtext.setText((String)actList.get(position).getevtext());
        ah.joined.setText((String)actList.get(position).getjoined());
        ah.capa.setText((String)actList.get(position).getcapa());
        return convertView;
    }
}
