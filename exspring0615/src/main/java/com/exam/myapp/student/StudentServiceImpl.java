package com.exam.myapp.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

////	private StudentDao studentDao = new StuDaoBatis();
//	private StudentDao studentDao = StuDaoBatis.getInstance();
//	
//	private StudentServiceImpl() { } //외부에서 생성자 호출 금지
//	
//	public static StudentService studentService =  new StudentServiceImpl(); //내부에서 객체 생성
//	
//	public static StudentService getInstance() { //외부에서 필요한 서블릿 있으면, getInstance로 가져다가 사용
//		return studentService;
//	}
	
	@Autowired
	private StudentDao studentDao;
	
	@Override
	public List<StudentVo> selectStudentList() {
		return studentDao.selectStudentList();
	}

	@Override
	public int addStudent(StudentVo vo) {
		return studentDao.addStudent(vo);
	}

	@Override
	public int delStudent(String stuNo) {
		return studentDao.delStudent(stuNo);
	}

	@Override
	public StudentVo selectStudent(String stuNo) {
		return studentDao.selectStudent(stuNo);
	}

	@Override
	public int updateStudent(StudentVo vo) {
		return studentDao.updateStudent(vo);
	}

	@Override
	public StudentVo selectLogin(StudentVo vo) {
		return studentDao.selectLogin(vo);
	}

	

}