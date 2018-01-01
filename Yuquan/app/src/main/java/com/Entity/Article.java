package com.Entity;

public class Article {
	
	public Integer ArticleId;
	public String Title;
	public String PostDate;
	public String Content;
	public Integer ReadTime;

	public Article toArticleListItem() {
		Article article = new Article();
		article.ArticleId = this.ArticleId;
		article.Title = this.Title;
		article.PostDate = this.PostDate;
		article.ReadTime = this.ReadTime;
		return article;
	}
	
}
