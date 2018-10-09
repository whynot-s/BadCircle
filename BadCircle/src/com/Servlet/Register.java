package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DBtools.DBUtil;
import com.Entity.User;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
        PrintWriter out = response.getWriter();
		response.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName");
		String phoneNumber = request.getParameter("phoneNumber");
		String pwd = request.getParameter("pwd");
		String repwd = request.getParameter("repwd");
		String gender = request.getParameter("gender");
		String birthday = request.getParameter("birthday");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String district = request.getParameter("district");
		switch(User.registerNotValid(userName, pwd, repwd, phoneNumber)) {
		case -1: out.write("Only English Letters, Arabic Numbers and _ allowed for userName.\nLength is limited to 6 - 18.");return;
		case -2: out.write("Length Of Password is limited to 8 - 18.");return;
		case -3: out.write("Second Password doesn\'t match the first.");return;
		case -4: out.write("PhoneNumber is Wrong.");return;
		case -5: out.write("userName registered! Plz use another one.");return;
		case -7: out.write("PhoneNumber is registered.");return;
		}
        try{
        		Connection con=DBUtil.getConnection();
        		Statement stmt=con.createStatement();
        		String sql= String.format(User.INSERT_INTO_users, userName, phoneNumber, pwd, gender, birthday, 0, province, city, district);
			stmt.execute(sql);
			response.getWriter().write("Success!");
        }catch(Exception ex){
        		ex.printStackTrace();
        		response.getWriter().write(ex.getMessage());
        }finally{
    			DBUtil.Close();
    			out.flush();
    			out.close();
        }
	}

}
