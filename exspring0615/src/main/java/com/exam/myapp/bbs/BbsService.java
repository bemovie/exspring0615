package com.exam.myapp.bbs;

import java.io.File;
import java.util.List;

public interface BbsService {

	// ~ StuListServlet ~
	List<BbsVo> selectBbsList(SearchInfo info);

	// ~ StuAddServlet ~
	int addBbs(BbsVo vo);

	// ~ StuDelServlet ~
	int delBbs(BbsVo vo);
	
	BbsVo selectBbs(int bbsNo);

	int updateBbs(BbsVo vo);

	BbsVo selectLogin(BbsVo vo);

	AttachVo selectAttach(int attNo);

	File getAttachFile(AttachVo vo);

	int selectBbsCount(SearchInfo info);

}