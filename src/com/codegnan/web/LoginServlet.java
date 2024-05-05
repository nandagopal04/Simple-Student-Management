package com.codegnan.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.codegnan.dao.UserDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// call show login form
		showLoginForm(response, false);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("un");
		String password = request.getParameter("pw");
		try {
			if( validate(username, password) ) {
				// display another secured page to the user
				HttpSession session = request.getSession(true);
				session.setAttribute("username", username);
				session.setMaxInactiveInterval(1 * 60);
				RequestDispatcher rd = request.getRequestDispatcher("success");
				rd.forward(request, response);
			}
			else {
				showLoginForm(response, true);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void showLoginForm(HttpServletResponse response, boolean error) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>login</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("	<h2>Welcome to Codegnan IT Solutions</h2>");
		out.println("	<h3>Login Form</h3>");
		if(error) {
			out.println("	<div style=\"color:red\">Invalid credentials</div>");
		}
		
		out.println("   <form action=\"login\" method=\"post\">");
		out.println("	Username : <input type=\"text\" name=\"un\"><br><br>");
		out.println("	Password : <input type=\"password\" name=\"pw\"><br><br>");
		out.println("	<input type=\"submit\" value=\"Login\">");
		out.println("	<input type=\"reset\" value=\"Cancel\">");
		out.println("   </form><br><br>");
		out.println("<a href=\"help\">Help</a><br>");
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}
	
	private boolean validate(String username, String password) throws ClassNotFoundException, SQLException {
		return UserDao.validate(username, password);
	}

}
