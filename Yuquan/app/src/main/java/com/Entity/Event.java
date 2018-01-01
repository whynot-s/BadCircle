package com.Entity;

import org.json.JSONObject;

public class Event {

	public Integer EventId;
	public String EventName;
	public String EventTime;
	public String EventPlace;
	public String Description;
	public String PostDate;
	public String StartDate;
	public String JoinDeadline;
	public Integer Capacity;
	public Integer HostClub;
	public Integer HostAdmin;
	public Integer Joined;
	public String ClubName;
	public String AdminName;
	public Integer Confirmed;
	public String ConfirmedDate;
	public Integer Participated;
	
	public Event toEventListItem() {
		Event e = new Event();
		e.EventId = this.EventId;
		e.EventName = this.EventName;
		e.EventTime = this.EventTime;
		e.Capacity = this.Capacity;
		e.Joined = this.Joined;
		return e;
	}
	
	public Event toUserEventListItem() {
		Event e = this.toEventListItem();
		e.Confirmed = this.Confirmed;
		e.ConfirmedDate = this.ConfirmedDate;
		e.Participated = this.Participated;
		return e;
	}

	public Event(){}


	public Event(String json){
		//{"EventId":6,"EventName":"LD羽毛球活动","EventTime":"2018-12-31 10:00:00.0","Capacity":100,"Joined":1}
		try {
			JSONObject eventJson = new JSONObject(json);
			this.EventId = eventJson.getInt("EventId");
			this.EventName = eventJson.getString("EventName");
			this.EventTime = eventJson.getString("EventTime");
			this.Capacity = eventJson.getInt("Capacity");
			this.Joined = eventJson.getInt("Joined");
			this.Description = eventJson.getString("Description");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public Event(JSONObject eventJson){
		//{"EventId":6,"EventName":"LD羽毛球活动","EventTime":"2018-12-31 10:00:00.0","Capacity":100,"Joined":1}
		try {
			this.EventId = eventJson.getInt("EventId");
			this.EventName = eventJson.getString("EventName");
			this.EventTime = eventJson.getString("EventTime");
			this.Capacity = eventJson.getInt("Capacity");
			this.Joined = eventJson.getInt("Joined");
			this.Description = eventJson.getString("Description");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public String toString(){
		return String.format("this.EventId=%d this.EventName=%s this.EventTime=%s this.Capacity=%s this.Joined=%d ",EventId,EventName,EventTime,Capacity,Joined);
	}
}
