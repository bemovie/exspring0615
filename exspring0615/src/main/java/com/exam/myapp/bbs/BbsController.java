package com.exam.myapp.bbs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.exam.myapp.student.StudentVo;

//@SessionAttributes("vo")
@Controller
@RequestMapping("/bbs/")
public class BbsController{
	
//	String url="jdbc:oracle:thin:@localhost:1521:xe";
//	String user="web";
//	String password="web01";

//	private StudentDao studentDao = new StudentDaoJdbc();
//	private StudentDao studentDao = new StuDaoBatis();
//	private StudentService studentService = new StudentServiceImpl();
//	private StudentService studentService = StudentServiceImpl.getInstance();
	
	@Autowired
	private BbsService bbsService;
	
//	@RequestMapping(value = "list.do", method = RequestMethod.GET)
//	@RequestMapping("list.do")
	@GetMapping("list.do")
	public String list(Model model){
		
		List<BbsVo> list = bbsService.selectBbsList();
		
		model.addAttribute("bbsList", list);
		
		return "bbs/bbsList";
	}
	
//	@RequestMapping(value = "add.do", method = RequestMethod.GET)
	@GetMapping("add.do")
	public String addform() {
//		req.getRequestDispatcher("/WEB-INF/views/student/stuAdd.jsp").forward(req, resp);
		return "bbs/bbsAdd";
	}
	
//	@RequestMapping(value = "add.do", method = RequestMethod.POST)
	@PostMapping("add.do")
	public String add(BbsVo vo
			/* , HttpSession session */
			,@SessionAttribute("loginUser") StudentVo svo
			) {
		
//		StudentVo svo = (StudentVo) session.getAttribute("loginUser");
		
//		System.out.println("첨부파일명 : " + vo.getBbsFile().getOriginalFilename());
		
		vo.setBbsWriter(svo.getStuNo());
		
		int n = bbsService.addBbs(vo);
		
		System.out.println(n + "개의 게시글 추가 성공");
			
//		resp.sendRedirect(req.getContextPath() + "/student/list.do");
		return "redirect:/bbs/list.do";

	}
	
	
//	@RequestMapping(value = "edit.do", method = RequestMethod.GET)
	@GetMapping("edit.do")
	protected String editform(int bbsNo, Model model) {
		
//		String stuNo = req.getParameter("stuNo");
		BbsVo vo = bbsService.selectBbs(bbsNo);
//		req.setAttribute("svo", vo);	
		model.addAttribute("bbsVo", vo);
		
//		req.getRequestDispatcher("/WEB-INF/views/student/stuEdit.jsp").forward(req, resp);
		return "bbs/bbsEdit";
	}

//	@RequestMapping(value = "edit.do", method = RequestMethod.POST)
//	@PostMapping(value="edit.do", produces = "application/text; charset=utf8")
	@PostMapping("edit.do")
	@ResponseBody
	protected String edit(BbsVo vo, @SessionAttribute("loginUser") StudentVo svo/*, Model model*/) {
	
	//	StudentVo vo = new StudentVo();
	//	
	//	vo.setStuNo(req.getParameter("stuNo"));
	//	vo.setStuName(req.getParameter("stuName"));
	//	vo.setStuScore(Integer.parseInt(req.getParameter("stuScore")));	
		
	//	if(!svo.getStuNo().equals(vo.getBbsWriter())) {
	//		model.addAttribute("message", "작성자 본인만 수정 가능합니다.");
	//		return "redirect:/bbs/list.do";
	//	}	
		
		vo.setBbsWriter(svo.getStuNo());
			
	//	System.out.println(svo.getStuNo());
		System.out.println(vo.getBbsWriter());
		
	//	BbsVo bvo = bbsService.selectBbs(vo.getBbsNo());
		
	//	if (bvo.getBbsWriter().equals(svo.getStuNo())) {
				
		//	resp.sendRedirect(req.getContextPath() + "/student/list.do"); // 목록 화면으로 이동
	//		return n + "개의 게시글 변경 성공~";
	//	}
			
	//	model.addAttribute("message", "작성자 본인만 수정 가능합니다.");
	//	return "변경 실패~ 작성자 본인만 수정 가능합니다.";
				
		String message = "";
		int n = bbsService.updateBbs(vo);
		System.out.println(n + "개의 게시글 변경 성공");
		
		if (n == 0) {
			//model.addAttribute("message", "작성자 본인만 수정 가능합니다.");
			message = "<script>alert('Error'); location.href='edit.do?bbsNo=" + vo.getBbsNo() +"';</script> ";
		} else {
			message = "<script>alert('Edit Success'); location.href='list.do';</script>";
		}
		
		return message;
//		return "redirect:/bbs/list.do";
	}
	
//	@RequestMapping(value = "del.do", method = RequestMethod.GET)
//	@GetMapping(value="del.do", produces = "application/text; charset=utf8")
	@GetMapping("del.do")
	@ResponseBody
	protected String service(int bbsNo, /*StudentVo vo,*//*String bbsWriter,*//*BbsVo vo,*/ @SessionAttribute("loginUser") StudentVo svo/*, Model model*/) {
		
//		String stuNo = req.getParameter("stuNo");
		
//		bbsWriter = svo.getStuNo();
		
//		vo.setStuNo(svo.getStuNo());
		
		String message = "";
		
		BbsVo bvo = bbsService.selectBbs(bbsNo);
		
		if (!bvo.getBbsWriter().equals(svo.getStuNo())) {
			message = "<script>alert('Error'); location.href='edit.do?bbsNo=" + bbsNo +"';</script> ";
		} else {
			int n = bbsService.delBbs(bbsNo);
			System.out.println(n + "개의 게시글 삭제 성공");
			message = "<script>alert('Delete Success'); location.href='list.do';</script>";
//			return n + "개의 게시글 삭제 성공";
		}
		
		return message;
//		return "오류 발생";
		
		
		
//		model.addAttribute("message", "작성자 본인만 삭제 가능합니다.");
//		return "redirect:/bbs/list.do";
		
//		if (n == 0) {
//			model.addAttribute("message", "작성자 본인만 수정 가능합니다.");
//		}
		
		
//		resp.sendRedirect(req.getContextPath() + "/student/list.do");
//		return "redirect:/bbs/list.do";
		
	}
	
	@GetMapping("down.do")
	protected void download(int attNo/*, AttachVo vo*/ , HttpServletResponse resp) {
		
//		String stuNo = req.getParameter("stuNo");
		
		AttachVo vo = bbsService.selectAttach(attNo);
		
		File f = bbsService.getAttachFile(vo);
		
//		resp.setContentLength( (int) f.length() );
//		resp.setContentType( "application/octet-stream" );
		resp.setContentType( MediaType.APPLICATION_OCTET_STREAM_VALUE );
		
//		try {
//			String fname = URLEncoder.encode(vo.getAttOrgName(), "UTF-8").replace("+", "%20");
//			resp.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + fname);
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		
		String cdv = ContentDisposition.attachment().filename(vo.getAttOrgName(), StandardCharsets.UTF_8).build().toString();
		resp.setHeader(HttpHeaders.CONTENT_DISPOSITION, cdv);
		
		try {
			
			FileCopyUtils.copy( new FileInputStream(f), resp.getOutputStream() );
		} catch (IOException e) {
			e.printStackTrace();
		}
//		resp.sendRedirect(req.getContextPath() + "/student/list.do");
//		return "redirect:/bbs/list.do";
		
	}
	
	
}



	
//	@RequestMapping(value = "/student/login.do", method = RequestMethod.GET)
//	protected String loginform() {
//		
////		req.getRequestDispatcher("/WEB-INF/views/student/login.jsp").forward(req, resp);
//		return "student/login";
//	}
//
//	@RequestMapping(value = "/student/login.do", method = RequestMethod.POST)
//	protected String login(BbsVo vo, HttpSession session) {
//
////		StudentVo vo = new StudentVo();
////		
////		vo.setStuNo(req.getParameter("stuNo"));
////		vo.setStuName(req.getParameter("stuName"));
//		BbsVo svo = studentService.selectLogin(vo);
//		
//		if (svo==null) { //로그인 실패시, 로그인 화면으로 이동
////			resp.sendRedirect(req.getContextPath() + "/student/login.do"); // 로그인 화면으로 이동
//			return "redirect:/student/login.do";
//			
//		}else { //로그인 성공
////			HttpSession session = req.getSession();
//			session.setAttribute("loginUser", svo); //세션에 로그인한 사용자정보 저장
////			resp.sendRedirect(req.getContextPath() + "/student/list.do"); // 회원 목록 화면으로 이동
//			return "redirect:/student/list.do";
//		}
//	}
//
//}






		
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
