package com.exam.myapp.student;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentDao {

	// ~ StuListServlet ~
	List<StudentVo> selectStudentList();

	// ~ StuAddServlet ~
	int addStudent(StudentVo vo);

	// ~ StuDelServlet ~
	int delStudent(String stuNo);
	
	StudentVo selectStudent(String stuId);

	int updateStudent(StudentVo vo);

	StudentVo selectLogin(StudentVo vo);

}