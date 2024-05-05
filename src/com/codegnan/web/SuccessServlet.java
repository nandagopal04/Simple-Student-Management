package com.codegnan.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SuccessServlet
 */
@WebServlet("/success")
public class SuccessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if( session != null && session.getAttribute("username")!= null ) {
			String username = (String) session.getAttribute("username");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>success</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("	<h2>Welcome to Codegnan IT Solutions</h2>");
			out.println("	<h3>Success Page</h3>");
			out.println("<br><br>");
			out.println("<a href=\"profile\">Profile</a><br>");
			out.println("<a href=\"help\">Help</a><br>");
			out.println("<a href=\"signout\">Signout</a><br>");
			out.println("<br><br>");
			out.println("Thank you "+username);
			out.println("</body>");
			out.println("</html>");
			out.close();
		}
		else {
			response.sendRedirect("login");
		}
		
	}

}
