package com.exam.myapp.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/student/del.do")
public class StuDelServlet extends HttpServlet{
	
//	String url="jdbc:oracle:thin:@localhost:1521:xe";
//	String user="web";
//	String password="web01";
	
//	StudentDao studentDao = new StuDaoBatis();
//	private StudentService studentService = new StudentServiceImpl();
	private StudentService studentService = StudentServiceImpl.getInstance();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.sendRedirect(req.getContextPath() + "/student/list.do");
		
		resp.setContentType("text/html"); //Charset=UTF-8
		resp.setCharacterEncoding("UTF-8");
//		req.setCharacterEncoding("UTF-8");
		
//		StudentVo vo = new StudentVo();
//		
//		vo.setStuNo(req.getParameter("stuNo"));
		
//		String stu_No = req.getParameter("stuNo");
		String stuNo = req.getParameter("stuNo");
		
		
//		try {
//			Class.forName("oracle.jdbc.OracleDriver");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		int n = studentService.delStudent(stuNo);
		
		System.out.println(n + "명의 학생 삭제 성공");
		
//	PrintWriter out = resp.getWriter();
//	
//
//	out.println("<!DOCTYPE html>");
//	out.println("<html>");
//	out.println("<head>");
//	out.println("<meta charset=\"UTF-8\">");
//	out.println("<title>학생 삭제</title>");
//	out.println("</head>");
//	out.println("<body>");
//	out.println("<h1>" + n + "명의 학생 삭제 성공 </h1>");
//	out.println("<a href=\"" + req.getContextPath() + "/student/list.do\">학생목록으로 이동</a>");
//	out.println("</body>");
//	out.println("</html>");
	
	
	
	}

//	public int delStudent(StudentVo vo) {
//		int n = 0;
//		
//		String sql = "DELETE FROM student WHERE stu_no = ?";
//		
//	try {
//
//		Connection conn = DriverManager.getConnection(url, user, password);
//
//		PreparedStatement pstmt = conn.prepareStatement(sql);
//		
//		pstmt.setString(1, vo.getStuNo());
//		
//		n = pstmt.executeUpdate();
//		
////		System.out.println(n + "명의 학생 삭제 성공");
//		
//		
//	} catch (SQLException e) {
//		
//		e.printStackTrace();
//	}
//		return n;
//	}

	
	
}
