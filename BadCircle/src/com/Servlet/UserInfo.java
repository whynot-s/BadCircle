package com.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DBtools.DBUtil;
import com.Entity.User;

/**
 * Servlet implementation class userInfo
 */
@WebServlet("/userInfo")
public class UserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
        ServletOutputStream out = response.getOutputStream();
		response.setCharacterEncoding("UTF-8");
		String tmp = request.getParameter("userId");
		Integer userId = null;
		try {
			userId = Integer.parseInt(tmp);
		}catch(NumberFormatException e) {
			out.write("Invalid userId".getBytes("UTF-8"));
		}
		ResultSet rs = null;
		try {
			Connection con=DBUtil.getConnection();
			Statement stmt=con.createStatement();
			String sql= String.format("SELECT userId, userName, phoneNumber, gender, birthday, access, province, city, district From BadCircle.users WHERE userID=%d", userId);
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				User user = new User(Integer.parseInt(rs.getString("userId")), rs.getString("userName"), rs.getString("phoneNumber"), rs.getString("gender"),
						rs.getString("birthday"), Integer.parseInt(rs.getString("access")), rs.getString("province"), rs.getString("city"), rs.getString("district"));
				out.write(user.toJson().getBytes("UTF-8"));
				break;
			}
		}catch(Exception ex){
    			ex.printStackTrace();
    			out.write("DB Error".getBytes("UTF-8"));
		}
	}

}
