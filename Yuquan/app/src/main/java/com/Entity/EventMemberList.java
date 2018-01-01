package com.Entity;

public class EventMemberList {

	public Integer counter;
	public Member[] members;

	public EventMemberList(int n) {
		members = new Member[n];
		counter = 0;
	}
	
	public void add(Member member) {
		members[counter++] = member;
	}
	
}
