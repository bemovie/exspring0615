package com.exam.myapp.student;

import java.util.List;

public interface StudentService {

	// ~ StuListServlet ~
	List<StudentVo> selectStudentList();

	// ~ StuAddServlet ~
	int addStudent(StudentVo vo);

	// ~ StuDelServlet ~
	int delStudent(String stuNo);
	
	StudentVo selectStudent(String stuNo);

	int updateStudent(StudentVo vo);

	StudentVo selectLogin(StudentVo vo);

}