package com.exam.myapp.bbs;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttachDao {

	// ~ StuListServlet ~
//	List<BbsVo> selectBbsList();

	// ~ StuAddServlet ~
	int insertAttach(AttachVo vo);

	AttachVo selectAttach(int attNo);

	int deleteAttach(int attNo);

	// ~ StuDelServlet ~
//	int delBbs(int bbsNo);

//	BbsVo selectBbs(int bbsNo);

//	int updateBbs(BbsVo vo);

//	BbsVo selectLogin(BbsVo vo);

}