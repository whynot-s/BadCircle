package com.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DBtools.DBUtil;
import com.Entity.User;

/**
 * Servlet implementation class ModifyUserInfo
 */
@WebServlet("/ModifyUserInfo")
public class ModifyUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyUserInfo() {
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
		String tmp = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String phoneNumber = request.getParameter("phoneNumber");
		String gender = request.getParameter("gender");
		String birthday = request.getParameter("birthday");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String district = request.getParameter("district");
		Integer userId = null;
		try {
			userId = Integer.parseInt(tmp);
		}catch(NumberFormatException e) {
			out.write("Invalid userId".getBytes("UTF-8"));
		}
		switch(User.modifyNotValid(userId, userName, "00000000", "00000000", phoneNumber)) {
		case -1: out.write("Only English Letters, Arabic Numbers and _ allowed for userName.\nLength is limited to 6 - 18.".getBytes("UTF-8"));return;
		case -2: out.write("Length Of Password is limited to 8 - 18.".getBytes("UTF-8"));return;
		case -3: out.write("Second Password doesn\'t match the first.".getBytes("UTF-8"));return;
		case -4: out.write("PhoneNumber invalid!".getBytes("UTF-8"));return;
		case -5: out.write("userName registered! Plz use another one.".getBytes("UTF-8"));return;
		case -7: out.write("PhoneNumber registered! Plz use another one.".getBytes("UTF-8"));return;
		}
		try {
			Connection con=DBUtil.getConnection();
			Statement stmt=con.createStatement();
    			String sql= String.format(User.UPDATE_users, userName, phoneNumber, gender, birthday, province, city, district, userId);
			stmt.execute(sql);
			out.write("Success!".getBytes("UTF-8"));
		}catch(Exception ex){
    			ex.printStackTrace();
    			out.write("DB Error".getBytes("UTF-8"));
		}
	}

}
