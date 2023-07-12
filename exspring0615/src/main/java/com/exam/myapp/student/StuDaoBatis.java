package com.exam.myapp.student;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//import com.exam.comm.MyBatisUtils;

//@Repository
public class StuDaoBatis implements StudentDao {
	
	@Autowired
	private SqlSession session;
	
//	@Autowired
//	private SqlSessionFactory sqlSessionFactory;
	
//	private StuDaoBatis() { } //외부에서 생성자 호출 금지
//	private static StudentDao studentDao = new StuDaoBatis(); //내부에서 객체 생성
//	public static StudentDao getInstance() { //외부에서 필요한 서블릿 있으면, getInstance로 가져다가 사용
//			return studentDao;
//	}
	
//	SqlSessionFactory sqlSessionFactory;
//	
//	{
//		try {
//			String resource = "batis/mybatis-config.xml";
//			InputStream inputStream = Resources.getResourceAsStream(resource);
//			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//		} 
//		catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	
//	}
	
//	private SqlSessionFactory sqlSessionFactory = MyBatisUtils.getSqlSessionFactory();
	
	@Override
	public List<StudentVo> selectStudentList() {
		
//		List<StudentVo> list = null;
		
//		try (SqlSession session = sqlSessionFactory.openSession()) {
//			  list = session.selectList("com.exam.myapp.student.StudentDao.selectStudentList");
//			}
		
//		return list;
		return session.selectList("com.exam.myapp.student.StudentDao.selectStudentList");
	}

	@Override
	public int addStudent(StudentVo vo) {
		
//		int num = 0;
//		try (SqlSession session = sqlSessionFactory.openSession()) {
//			num = session.insert("com.exam.myapp.student.StudentDao.addStudent", vo);
//			session.commit();
//		}
		
		return session.insert("com.exam.myapp.student.StudentDao.addStudent", vo);
	}

	@Override
	public int delStudent(String stuNo) {
		
//		int num = 0;
//		try (SqlSession session = sqlSessionFactory.openSession()) {
//			num = session.delete("com.exam.myapp.student.StudentDao.delStudent", stuNo);
//			session.commit();
//		}
		
		return session.delete("com.exam.myapp.student.StudentDao.delStudent", stuNo);
	}

	@Override
	public StudentVo selectStudent(String stuNo) {
		
//		StudentVo vo = null;
		
//		try (SqlSession session = sqlSessionFactory.openSession()) {
//			  vo = session.selectOne("com.exam.myapp.student.StudentDao.selectStudent", stuNo);
//			}
		
		return session.selectOne("com.exam.myapp.student.StudentDao.selectStudent", stuNo);
		
	}

	@Override
	public int updateStudent(StudentVo vo) {
//		int num = 0;
//		try (SqlSession session = sqlSessionFactory.openSession()) {
//			num = session.update("com.exam.myapp.student.StudentDao.updateStudent", vo);
//			session.commit();
//		}
		
		return session.update("com.exam.myapp.student.StudentDao.updateStudent", vo);
	}

	@Override
	public StudentVo selectLogin(StudentVo svo) {
//		StudentVo vo = null;
		
//		try (SqlSession session = sqlSessionFactory.openSession()) {
//			  vo = session.selectOne("com.exam.myapp.student.StudentDao.selectLogin", svo);
//			}
		
		return session.selectOne("com.exam.myapp.student.StudentDao.selectLogin", svo);
	}	

}
