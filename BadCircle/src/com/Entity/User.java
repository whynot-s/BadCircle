package com.Entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.DBtools.DBUtil;
import com.google.gson.Gson;

public class User {

	final public static String INSERT_INTO_users = "INSERT INTO BadCircle.users(userName, phoneNumber, pwd, gender, birthday, access, province, city, district)"
			+ " VALUES(\'%s\', \'%s\', \'%s\', \'%s\', \'%s\', %d, \'%s\', \'%s\', \'%s\')";
	final public static String UPDATE_users = "UPDATE BadCircle.users SET userName=\'%s\', phoneNumber=\'%s\', gender=\'%s\', birthday=\'%s\', province=\'%s\', city=\'%s\', district=\'%s\'"
			+ " WHERE userId=%d";
	
	private Integer userId;
	private String userName;
	private String phoneNumber;
	private String gender;
	private String birthday;
	private Integer access;
	private String province;
	private String city;
	private String district;
	
	public User(Integer userId, String userName, String phoneNumber, String gender, String birthday, Integer access, String province, String city, String district) {
		this.userId = userId;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.birthday = birthday;
		this.access = access;
		this.city = city;
		this.province = province;
		this.district = district;
	}
	
	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	public static int registerNotValid(String userName, String pwd, String repwd, String phoneNumber) {
		if(!userName.matches("[A-Za-z0-9_]{6,18}"))return -1;
		try {
			Connection con=DBUtil.getConnection();
			Statement stmt=con.createStatement();
			String sql = "SELECT userName, phoneNumber from BadCircle.users";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				if(rs.getString("userName").equals(userName)) {
					return -5;
				}
				if(rs.getString("phoneNumber").equals(phoneNumber)) {
					return -7;
				}
			}
			DBUtil.Close();
		}catch(Exception ex){
    			ex.printStackTrace();
    			DBUtil.Close();
    			return -6;
		}
		if(!pwd.matches(".{8,18}"))return -2;
		if(!pwd.equals(repwd))return -3;
		if(!phoneNumber.matches("\\d{11}")) return -4;
		return 0;
	}
	
	public static int modifyNotValid(Integer userId, String userName, String pwd, String repwd, String phoneNumber) {
		if(!userName.matches("[A-Za-z0-9_]{6,18}"))return -1;
		try {
			Connection con=DBUtil.getConnection();
			Statement stmt=con.createStatement();
			String sql = "SELECT userId, userName, phoneNumber from BadCircle.users";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				if(rs.getInt("userId") == userId) {
					continue;
				}
				if(rs.getString("userName").equals(userName)) {
					return -5;
				}
				if(rs.getString("phoneNumber").equals(phoneNumber)) {
					return -7;
				}
			}
			DBUtil.Close();
		}catch(Exception ex){
    			ex.printStackTrace();
    			DBUtil.Close();
    			return -6;
		}
		if(!pwd.matches(".{8,18}"))return -2;
		if(!pwd.equals(repwd))return -3;
		if(!phoneNumber.matches("\\d{11}")) return -4;
		return 0;
	}
	
}
