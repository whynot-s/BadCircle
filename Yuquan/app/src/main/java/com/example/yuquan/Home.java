package com.example.yuquan;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.Entity.Event;
import com.Entity.EventList;
import com.example.yuquan.util.ActAdapter;

import java.util.ArrayList;

/**
 * @author allin
 *
 */
public class Home extends ListActivity {


    public ArrayList<Event> eventList;
    private ImageButton r_button;
    private TextView user_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        r_button = (ImageButton) findViewById(R.id.refresh);
        user_name = (TextView) findViewById(R.id.user_name);
        init();

        /*r_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });*/
    }

    private void init() {

        this.eventList = EventList.getAll();
        ActAdapter adapter = new ActAdapter(Home.this, eventList);
        setListAdapter(adapter);
    }

    private void refresh() {

    }
}