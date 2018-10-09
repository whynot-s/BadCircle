package com.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DBtools.DBUtil;
import com.Entity.Article;

/**
 * Servlet implementation class News
 */
@WebServlet("/Articles")
public class Articles extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Articles() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setCharacterEncoding("UTF-8");
        ServletOutputStream out = response.getOutputStream();
		String articleID = request.getParameter("articleId");
        response.setContentType("text/html; charset=UTF-8");
        try{
	        	Connection con=DBUtil.getConnection();
	        	Statement stmt=con.createStatement();
			String sql= String.format("SELECT * FROM BadCircle.articles where articleId = %s", articleID);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Article article = new Article(rs.getString("articleId"), rs.getString("articleTitle"), rs.getString("articleDate"), rs.getString("articleContent"));
				out.write(article.toJson().getBytes("UTF-8"));
				break;
			}
        }
        catch(Exception ex){
        		ex.printStackTrace();
        }
        finally{
        		DBUtil.Close();
        		out.flush();
        		out.close();
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
