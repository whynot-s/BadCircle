package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DBtools.DBUtil;
import com.Entity.Event;
import com.Entity.User;

/**
 * Servlet implementation class CreateEvent
 */
@WebServlet("/CreateEvent")
public class CreateEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateEvent() {
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
		String eventTitle = request.getParameter("eventTitle");
		String eventDescription = request.getParameter("eventDescription");
		String eventDate = request.getParameter("eventDate");
		String eventStatus = request.getParameter("eventStatus");
		String eventCapacity_tmp = request.getParameter("eventCapacity");
		String eventLocation = request.getParameter("eventLocation");
        try{
        		Integer eventCapacity = Integer.parseInt(eventCapacity_tmp);
        		Connection con=DBUtil.getConnection();
        		Statement stmt=con.createStatement();
        		String sql= String.format(Event.INSERT_INTO_events, eventTitle, eventDescription, eventDate, eventStatus, eventCapacity, eventLocation);
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
