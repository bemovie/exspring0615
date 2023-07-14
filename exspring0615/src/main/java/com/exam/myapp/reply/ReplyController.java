package com.exam.myapp.reply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.exam.myapp.student.StudentVo;

//@Controller
@RestController
public class ReplyController {
	
	@Autowired 
	private ReplyService replyService;
	
	@GetMapping("/reply/list.do") 
//	@ResponseBody //메서드의 반환값을 그대로 응답메시지 내용으로 전송
	public List<ReplyVo> list(int repBbsNo) {
		
		List<ReplyVo> repList = replyService.selectReplyList(repBbsNo);
		return repList;
		
	}
	
	@PostMapping("/reply/add.do") 
//	@ResponseBody
	public Map<String, Object> add(ReplyVo vo 
//		,HttpSession session
		,@SessionAttribute("loginUser") StudentVo svo
		) {
		
//		StudentVo svo = (StudentVo) session.getAttribute("loginUser");
		vo.setRepWriter(svo.getStuNo());
		int num = replyService.insertReply(vo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ok", true);
		map.put("result", num);
		
//		return "redirect:/bbs/edit.do?bbsNo=" + vo.getRepBbsNo();
//		return num+"개의 댓글 저장";
//		return num+"reply add";
		return map;
	}
	
	@GetMapping("/reply/del.do") 
//	@ResponseBody
	public Map<String, Object> del(ReplyVo vo 
//		,HttpSession session
		,@SessionAttribute("loginUser") StudentVo svo
		) {
		
//		StudentVo svo = (StudentVo) session.getAttribute("loginUser");
		vo.setRepWriter(svo.getStuNo());
		int num = replyService.deleteReply(vo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ok", true);
		map.put("result", num);
		
//		return "redirect:/bbs/edit.do?bbsNo=" + vo.getRepBbsNo();
//		return num+"개의 댓글 저장";
//		return num+"reply add";
		return map;
	}
	
	/*
	@GetMapping("/reply/del.do")
	public String del(int repNo, @SessionAttribute("loginUser") StudentVo svo) {
		
		ReplyVo rvo = replyService.selectReply(repNo);
		
		if(svo.getStuNo().equals(rvo.getRepWriter())) {
			int n = replyService.deleteReply(repNo);
			System.out.println("댓글 삭제 성공");
		}
		
		return "redirect:/bbs/edit.do?bbsNo=" + rvo.getRepBbsNo();
	}
	*/
}
