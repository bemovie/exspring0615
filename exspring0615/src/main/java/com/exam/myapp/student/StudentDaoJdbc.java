package com.exam.myapp.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoJdbc implements StudentDao {
	
	String url="jdbc:oracle:thin:@localhost:1521:xe";
	String user="web";
	String password="web01";
	
	
	// ~ StuListServlet ~
	@Override
	public List<StudentVo> selectStudentList() {
			
			List<StudentVo> list = new ArrayList<StudentVo>();
			
			String sql = "SELECT stu_no, stu_name, stu_score FROM student";
			
			try {
				
				Connection conn = DriverManager.getConnection(url, user, password);
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
	
				ResultSet rs = pstmt.executeQuery();			
				
				while (rs.next()) {
					
					StudentVo vo = new StudentVo();
					vo.setStuNo(rs.getString("stu_no"));
					vo.setStuName(rs.getString("stu_name"));
					vo.setStuScore(rs.getInt("stu_score"));
					list.add(vo);
					
	//			String stuNo = rs.getString("stu_no");
	//			String stuName = rs.getString("stu_name");
	//			int stuScore = rs.getInt("stu_score");
	//			System.out.println(stuNo + ":" + stuName + ":" + stuScore);
	//			
	//			out.println("<p>");
	//			out.printf("%5s : %5s : %5s", stuNo, stuName, stuScore);			
	//			out.println("<a href=\"" + req.getContextPath() + "/student/del.do?stuNo=" + stuNo + "\"><button type=\"button\">삭제</button></a>");
	//			out.println("</p>");
					
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return list;
		}
	
	
	// ~ StuAddServlet ~
	@Override
	public int addStudent(StudentVo vo) {
			
			int n = 0;
			
			String sql = " INSERT INTO student "
					+ "( stu_no, stu_name, stu_score ) "
					+ "VALUES "
					+ "(?, ?, ? )";
			
		try {
			
			Connection conn = DriverManager.getConnection(url, user, password);
	
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getStuNo());
			pstmt.setString(2, vo.getStuName());
			pstmt.setInt(3, vo.getStuScore());
			
			n = pstmt.executeUpdate();
	//		System.out.println(n + "명의 학생 추가 성공");
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			return n;
		}
	
	
	// ~ StuDelServlet ~
	@Override
	public int delStudent(StudentVo vo) {
		int n = 0;
		
		String sql = "DELETE FROM student WHERE stu_no = ?";
		
	try {

		Connection conn = DriverManager.getConnection(url, user, password);

		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, vo.getStuNo());
		
		n = pstmt.executeUpdate();
		
//		System.out.println(n + "명의 학생 삭제 성공");
		
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
		return n;
	}

}
