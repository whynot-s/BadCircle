package com.Entity;

import android.util.Log;

import com.John.service.Main;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class EventList {

	public int counter;
	public Event[] Events;
	
	public EventList() {
		Events = new Event[10];
		counter = 0;
	}
	
	public void AddEvent(Event e) {
		Events[counter++] = e;
	}

	private ArrayList<Event> toAL(){
		ArrayList<Event> re=new ArrayList<Event>(counter);
		for(int i=0;i<counter;i++){
			re.add(this.Events[i]);
		}
		return re;
	}

	private static ArrayList<Event> re=null;
	public static ArrayList<Event> getAll(){
		new Thread(new Runnable() {
			int counter=-1;
			BufferedReader reader=null;
			HttpURLConnection connection=null;
			public void run() {
				try {
					Log.v("EventList", "begin to newConnect");
					connection = Main.newConnect("GetEventList", "GET", "");
					InputStream in = connection.getInputStream();
					reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
					String result = String.format("%s", reader.readLine());

					Log.v("EventList", String.format("result %s", result));
					// {"counter":1,"Events":[{"EventId":6,"EventName":"LD羽毛球活动","EventTime":"2018-12-31 10:00:00.0","Capacity":100,"Joined":1},null,null,null,null,null,null,null,null,null]}
					JSONObject eventListJson = new JSONObject(result);
					counter = eventListJson.getInt("counter");
					re = new ArrayList<Event>(counter);
					JSONArray eventList = eventListJson.getJSONArray("Events");
					for (int i = 0; i < counter; i++) {
						re.add(new Event(eventList.getJSONObject(i)));
					}
				} catch (
						Exception e)
				{
					Log.v("EventList", "Exception");
					e.printStackTrace();
				} finally

				{
					if (reader != null) {
						try {
							reader.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (connection != null) {
						connection.disconnect();
					}
				}
			}
		}).start();
		System.out.println("");
		if(re!=null) {
			for (Event e : re) {
				Log.v("EventList", e.toString());
			}
		}
		return re;
	}
}
