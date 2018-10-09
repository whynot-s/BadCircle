package com.Entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import com.DBtools.DBUtil;
import com.google.gson.Gson;

public class Article{
	
	private Integer articleId;
	private String title;
	private String date;
	private String content;
	
	public Article(String articleId, String title, String date, String content) {
		this.articleId = Integer.parseInt(articleId);
		this.title = title;
		this.date = date;
		this.content = content;
	}
	
	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	public static String getRandomArticles(int number) throws Exception {
		String Jsons = "[";
        try{
	        	Connection con=DBUtil.getConnection();
	        	Statement stmt=con.createStatement();
			String sql= "SELECT DISTINCT(articleId) FROM BadCircle.articles";
			ArrayList<String> ids = new ArrayList<String>();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				ids.add(rs.getString("articleId"));
			}
			Random random = new Random();
			for(int i = 0; i < number; i++) {
				int nextPos  = random.nextInt(ids.size());
				String nextId = ids.get(nextPos);
				ids.remove(nextPos);
				sql= String.format("SELECT * FROM BadCircle.articles where articleId = %s", nextId);
				rs = stmt.executeQuery(sql);
				while(rs.next()) {
					Article article = new Article(rs.getString("articleId"), rs.getString("articleTitle"), rs.getString("articleDate"), "");
					if(i != 0) {
						Jsons += ",";
					}
					Jsons += article.toJson();
				}
			}
	    }
	    catch(Exception ex){
	    		ex.printStackTrace();
	    		throw ex;
	    }
	    finally{
	    		DBUtil.Close();
	    }
		return Jsons + "\n]";
	}

}