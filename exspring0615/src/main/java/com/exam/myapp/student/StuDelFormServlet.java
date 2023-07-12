package com.exam.myapp.student;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/student/delform.do")
public class StuDelFormServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html"); // Charset=UTF-8
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		PrintWriter out = resp.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>학생 삭제</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>학생삭제</h2>\r\n");
		out.println("	<form action=\"" + req.getContextPath() + "/student/del.do\" method=\"post\">\r\n");
		out.println("		학번 : <input type=\"text\" name=\"stuNo\" value=\"\" /> <br>\r\n");
		out.println("		<input type=\"submit\" />\r\n");
		out.println("	</form>");
		out.println("</body>");
		out.println("</html>");

	}

}
