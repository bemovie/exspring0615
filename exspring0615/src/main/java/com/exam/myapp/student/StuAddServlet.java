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

//@WebServlet("/student/add.do")
public class StuAddServlet extends HttpServlet{
	
//	String url="jdbc:oracle:thin:@localhost:1521:xe";
//	String user="web";
//	String password="web01";
	
//	private StudentDao studentDao = new StuDaoBatis();
//	private StudentService studentService = new StudentServiceImpl();
	private StudentService studentService = StudentServiceImpl.getInstance();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/student/stuAdd.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//	}
//	
//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		req.getRequestDispatcher("/student/list.do").forward(req, resp);
		resp.sendRedirect(req.getContextPath() + "/student/list.do");
		
		resp.setContentType("text/html"); //Charset=UTF-8
		resp.setCharacterEncoding("UTF-8");
//		req.setCharacterEncoding("UTF-8");
		
		StudentVo vo = new StudentVo();
		
		vo.setStuNo(req.getParameter("stuNo"));
		vo.setStuName(req.getParameter("stuName"));
		vo.setStuScore(Integer.parseInt(req.getParameter("stuScore")));
		
//		String stu_No = req.getParameter("stuNo");
//		String stu_Name = req.getParameter("stuName");
//		int stu_Score = Integer.parseInt(req.getParameter("stuScore")); 
		
		
//		try {
//			Class.forName("oracle.jdbc.OracleDriver");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		int n = studentService.addStudent(vo);
	
	System.out.println(n + "명의 학생 추가 성공");
		
//	PrintWriter out = resp.getWriter();
//	
//
//	out.println("<!DOCTYPE html>");
//	out.println("<html>");
//	out.println("<head>");
//	out.println("<meta charset=\"UTF-8\">");
//	out.println("<title>학생 추가</title>");
//	out.println("</head>");
//	out.println("<body>");
//	out.println("<h1>" + n + "명의 학생 추가 성공 </h1>");
//	out.println("<a href=\"" + req.getContextPath() + "/student/list.do\">학생목록으로 이동</a>");
//	out.println("</body>");
//	out.println("</html>");
	
	
	
	}

//	public int addStudent(StudentVo vo) {
//		
//		int n = 0;
//		
//		String sql = " INSERT INTO student "
//				+ "( stu_no, stu_name, stu_score ) "
//				+ "VALUES "
//				+ "(?, ?, ? )";
//		
//	try {
//		
//		Connection conn = DriverManager.getConnection(url, user, password);
//
//		PreparedStatement pstmt = conn.prepareStatement(sql);
//		pstmt.setString(1, vo.getStuNo());
//		pstmt.setString(2, vo.getStuName());
//		pstmt.setInt(3, vo.getStuScore());
//		
//		n = pstmt.executeUpdate();
////		System.out.println(n + "명의 학생 추가 성공");
//		
//		
//	} catch (SQLException e) {
//		
//		e.printStackTrace();
//	}
//		return n;
//	}

	
	
}
