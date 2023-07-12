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
import javax.servlet.http.HttpSession;

//@WebServlet("/student/login.do")
public class LoginServlet extends HttpServlet{
	
//	String url="jdbc:oracle:thin:@localhost:1521:xe";
//	String user="web";
//	String password="web01";
	
//	private StudentDao studentDao = new StuDaoBatis();
//	private StudentService studentService = new StudentServiceImpl();
	private StudentService studentService = StudentServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String stuNo = req.getParameter("stuNo");
//		StudentVo vo = studentDao.selectStudent(stuNo);
//		req.setAttribute("svo", vo);	
		
		req.getRequestDispatcher("/WEB-INF/views/student/login.jsp").forward(req, resp);
	}

@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//	}
//	
//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		req.getRequestDispatcher("/student/list.do").forward(req, resp);
	
//		resp.sendRedirect(req.getContextPath() + "/student/list.do");
//		
//		resp.setContentType("text/html"); //Charset=UTF-8
//		resp.setCharacterEncoding("UTF-8");
	
//		req.setCharacterEncoding("UTF-8");
		
		StudentVo vo = new StudentVo();
		
		vo.setStuNo(req.getParameter("stuNo"));
		vo.setStuName(req.getParameter("stuName"));
//		vo.setStuScore(Integer.parseInt(req.getParameter("stuScore")));
		
//		String stu_No = req.getParameter("stuNo");
//		String stu_Name = req.getParameter("stuName");
//		int stu_Score = Integer.parseInt(req.getParameter("stuScore")); 
		
		
//		try {
//			Class.forName("oracle.jdbc.OracleDriver");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		int n = studentDao.updateStudent(vo);
		StudentVo svo = studentService.selectLogin(vo);
		
		//로그인 성공
		//로그인 실패
		if (svo==null) { //로그인 실패시, 로그인 화면으로 이동
			resp.sendRedirect(req.getContextPath() + "/student/login.do"); // 로그인 화면으로 이동
			
		}else { //로그인 성공
			HttpSession session = req.getSession();
			session.setAttribute("loginUser", svo); //세션에 로그인한 사용자정보 저장
			//id만 가지고 있으면 잃어버림, 사용자정보 자체(mvo)를 가지고 있어야함
			
//			요청객체(하나의 요청을 처리하는 얘들끼리 공유할때, 요청 다시 보내면 없어짐), 
//			세션객체(사용자가 접속을 유지할때까지 유지, 사용자 별로 구분), => 세션객체 사용 
//			서블릿컨텍스트객체(서버가 종료될 때까지 계속 유지, 모든 사용자가 공유 => 한사람만 로그인하면 모든 사용자가 로그인했다고 나옴)
			resp.sendRedirect(req.getContextPath() + "/student/list.do"); // 회원 목록 화면으로 이동
			
		}
		
//	System.out.println(n + "명의 학생 변경");
	
//	resp.sendRedirect(req.getContextPath() + "/student/list.do"); // 목록 화면으로 이동
		
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
