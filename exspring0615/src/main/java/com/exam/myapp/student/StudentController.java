package com.exam.myapp.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.JstlView;

//@SessionAttributes("vo")
@Controller
public class StudentController{
	
//	String url="jdbc:oracle:thin:@localhost:1521:xe";
//	String user="web";
//	String password="web01";

//	private StudentDao studentDao = new StudentDaoJdbc();
//	private StudentDao studentDao = new StuDaoBatis();
//	private StudentService studentService = new StudentServiceImpl();
//	private StudentService studentService = StudentServiceImpl.getInstance();
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value = "/student/list.do", method = RequestMethod.GET)
	public String list(Model model){
		
		List<StudentVo> list = studentService.selectStudentList();
		
		model.addAttribute("studentList", list);
		
		return "student/stuList";
	}
	
	
	@RequestMapping(value = "/student/add.do", method = RequestMethod.GET)
	public String addform(StudentVo vo) {
//		req.getRequestDispatcher("/WEB-INF/views/student/stuAdd.jsp").forward(req, resp);
		return "student/stuAdd";
	}
	
	@RequestMapping(value = "/student/add.do", method = RequestMethod.POST)
	public String add(@Valid StudentVo vo, BindingResult result) {
		
		System.out.println(result.getAllErrors());
		
		if(result.hasErrors()) {
	//		List<FieldError> fieldErrors = result.getFieldErrors();
			for	(FieldError fe : result.getFieldErrors()) {
				System.out.println("** " + fe.getField() );
	//			String[] codes = fe.getCodes();
				for (String c : fe.getCodes()) {
					System.out.println(c);
				}
			}
			return "/student/stuAdd";
		}
		
		
//		req.setCharacterEncoding("UTF-8");
		
//		StudentVo vo = new StudentVo();
//		
//		vo.setStuNo(req.getParameter("stuNo"));
//		vo.setStuName(req.getParameter("stuName"));
//		vo.setStuScore(Integer.parseInt(req.getParameter("stuScore")));
		
		int n = studentService.addStudent(vo);
		
		System.out.println(n + "명의 학생 추가 성공");
			
//		resp.sendRedirect(req.getContextPath() + "/student/list.do");
		return "redirect:/student/list.do";

	}
	
	@RequestMapping(value = "/student/edit.do", method = RequestMethod.GET)
	protected String editform(String stuNo, Model model) {
		
//		String stuNo = req.getParameter("stuNo");
		StudentVo vo = studentService.selectStudent(stuNo);
//		req.setAttribute("svo", vo);	
		model.addAttribute("svo", vo);
		
//		req.getRequestDispatcher("/WEB-INF/views/student/stuEdit.jsp").forward(req, resp);
		return "student/stuEdit";
	}

	@RequestMapping(value = "/student/edit.do", method = RequestMethod.POST)
	protected String edit(StudentVo vo) {
	
//	StudentVo vo = new StudentVo();
//	
//	vo.setStuNo(req.getParameter("stuNo"));
//	vo.setStuName(req.getParameter("stuName"));
//	vo.setStuScore(Integer.parseInt(req.getParameter("stuScore")));	

	int n = studentService.updateStudent(vo);
	
	System.out.println(n + "명의 학생 변경");
	
//	resp.sendRedirect(req.getContextPath() + "/student/list.do"); // 목록 화면으로 이동
	return "redirect:/student/list.do";
	
	}
	
	@RequestMapping(value = "/student/del.do", method = RequestMethod.GET)
	protected String service(String stuNo) {
		
//		String stuNo = req.getParameter("stuNo");
		
		int n = studentService.delStudent(stuNo);
		
		System.out.println(n + "명의 학생 삭제 성공");
		
//		resp.sendRedirect(req.getContextPath() + "/student/list.do");
		return "redirect:/student/list.do";
		
	}
	
	@RequestMapping(value = "/student/login.do", method = RequestMethod.GET)
	protected String loginform() {
		
//		req.getRequestDispatcher("/WEB-INF/views/student/login.jsp").forward(req, resp);
		return "student/login";
	}

	@RequestMapping(value = "/student/login.do", method = RequestMethod.POST)
	protected String login(StudentVo vo, HttpSession session) {

//		StudentVo vo = new StudentVo();
//		
//		vo.setStuNo(req.getParameter("stuNo"));
//		vo.setStuName(req.getParameter("stuName"));
		StudentVo svo = studentService.selectLogin(vo);
		
		if (svo==null) { //로그인 실패시, 로그인 화면으로 이동
//			resp.sendRedirect(req.getContextPath() + "/student/login.do"); // 로그인 화면으로 이동
			return "redirect:/student/login.do";
			
		}else { //로그인 성공
//			HttpSession session = req.getSession();
			session.setAttribute("loginUser", svo); //세션에 로그인한 사용자정보 저장
//			resp.sendRedirect(req.getContextPath() + "/student/list.do"); // 회원 목록 화면으로 이동
			return "redirect:/student/list.do";
		}
	}

}






		
//		resp.setContentType("text/html"); //Charset=UTF-8
//		resp.setCharacterEncoding("UTF-8");
//		req.setCharacterEncoding("UTF-8");
//		
//		
////		try {
////			Class.forName("oracle.jdbc.OracleDriver");
////		} catch (ClassNotFoundException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		
//	
//		PrintWriter out = resp.getWriter();
//		
//		out.println("<!DOCTYPE html>");
//		out.println("<html>");
//		out.println("<head>");
//		out.println("<meta charset=\"UTF-8\">");
//		out.println("<title>학생 목록</title>");	
//		out.println("<style>");
//		out.println("a {text-decoration : none; color : black;}");
//		out.println("</style>");	
//		out.println("</head>");
//		out.println("<body>");
//	
//	
//	for (StudentVo vo : list) {
//		
//		System.out.println(vo.getStuNo() + ":" + vo.getStuName() + ":" + vo.getStuScore());
//		
//		out.println("<p>");
//		out.printf("%5s : %5s : %5s", vo.getStuNo(), vo.getStuName(), vo.getStuScore());			
//		out.println("<a href=\"" + req.getContextPath() + "/student/del.do?stuNo=" + vo.getStuNo() + "\"><button type=\"button\">삭제</button></a>");
//		out.println("</p>");
//		
//	}
//		
//		out.println("<a href=\"" + req.getContextPath() + "/student/addform.do\">학생추가</a>");
//		out.println("<a href=\"" + req.getContextPath() + "/student/delform.do\">학생삭제</a>");
//		out.println("</body>");
//		out.println("</html>");
	
	
	
//	}

//	public List<StudentVo> selectStudentList() {
//		
//		List<StudentVo> list = new ArrayList<StudentVo>();
//		
//		String sql = "SELECT stu_no, stu_name, stu_score FROM student";
//		
//		try {
//			
//			Connection conn = DriverManager.getConnection(url, user, password);
//			
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//
//			ResultSet rs = pstmt.executeQuery();			
//			
//			while (rs.next()) {
//				
//				StudentVo vo = new StudentVo();
//				vo.setStuNo(rs.getString("stu_no"));
//				vo.setStuName(rs.getString("stu_name"));
//				vo.setStuScore(rs.getInt("stu_score"));
//				list.add(vo);
//				
////			String stuNo = rs.getString("stu_no");
////			String stuName = rs.getString("stu_name");
////			int stuScore = rs.getInt("stu_score");
////			System.out.println(stuNo + ":" + stuName + ":" + stuScore);
////			
////			out.println("<p>");
////			out.printf("%5s : %5s : %5s", stuNo, stuName, stuScore);			
////			out.println("<a href=\"" + req.getContextPath() + "/student/del.do?stuNo=" + stuNo + "\"><button type=\"button\">삭제</button></a>");
////			out.println("</p>");
//				
//			}
//			
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		}
//		return list;
//	}

	
	
//}
