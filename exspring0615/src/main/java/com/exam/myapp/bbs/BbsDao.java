package com.exam.myapp.bbs;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BbsDao {

	// ~ StuListServlet ~
	List<BbsVo> selectBbsList(SearchInfo info);

	// ~ StuAddServlet ~
	int addBbs(BbsVo vo);

	// ~ StuDelServlet ~
	int delBbs(int bbsNo);
	
	BbsVo selectBbs(int bbsNo);

	int updateBbs(BbsVo vo);

	BbsVo selectLogin(BbsVo vo);

	int selectBbsCount(SearchInfo info);

}