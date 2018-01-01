package com.Entity;

public class ArticleList {

	public Integer counter;
	public Article[] articles;
	
	public ArticleList(int n) {
		articles = new Article[n];
		counter = 0;
	}
	
	public void add(Article a) {
		articles[counter++] = a;
	}

}
