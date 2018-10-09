package com.Entity;

import com.google.gson.Gson;

public class Event {
	
	final public static String INSERT_INTO_events = "INSERT INTO BadCircle.events(eventTitle, eventDescription, eventDate, eventStatus, eventCapacity, eventLocation, eventNumber)"
			+ " VALUES(\'%s\', \'%s\', \'%s\', \'%s\', %d, \'%s\', 0)";

	private Integer eventId;
	private String eventTitle;
	private String eventDescription;
	private String eventDate;
	private String eventStatus;
	private Integer eventCapacity;
	private String eventLocation;
	private Integer eventNumber;
	
	public Event(Integer eventId, String eventTitle, String eventDescription, String eventDate, String eventStatus,
			Integer eventCapacity, String eventLocation) {
		this.eventId = eventId;
		this.eventTitle = eventTitle;
		this.eventDescription = eventDescription;
		this.eventDate = eventDate;
		this.eventStatus = eventStatus;
		this.eventCapacity = eventCapacity;
		this.eventLocation = eventLocation;
		this.eventNumber = 0;
	}
	
	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
