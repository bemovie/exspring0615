package com.exam.myapp.bbs;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.exam.myapp.student.StudentVo;

@Transactional
@Service
public class BbsServiceImpl implements BbsService {

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
	private BbsDao studentDao;
	
	@Autowired
	private AttachDao attachDao;
	
	@Value("${bbs.upload.path}")
	private String uploadPath;
//	private String uploadPath = "C:/Temp/upload";
	
	@PostConstruct //스프링이 현재 객체의 초기화 작업이 완료된 후 실행
	public void init() {
//	public BbsServiceImpl() {
		new File(uploadPath).mkdirs();
	}
	
	@Override
	public List<BbsVo> selectBbsList(SearchInfo info) {
		return studentDao.selectBbsList(info);
	}
	
//	@Transactional
	@Override
	public int addBbs(BbsVo vo) {
		List<MultipartFile> bbsFilList = vo.getBbsFile();
		int num = studentDao.addBbs(vo);
		
		for (MultipartFile f : bbsFilList) {
			if (f.getSize() <= 0) continue;
			
			System.out.println("파일명 : " + f.getOriginalFilename());
			System.out.println("파일크기 : " + f.getSize());
			
			String fname;
			File saveFile;
			do {
				fname = UUID.randomUUID().toString();
				saveFile = new File(uploadPath, fname);
			} while ( saveFile.exists() );
			
			try {
				
//				int a = 10/0;
				
				f.transferTo(saveFile);
				
				AttachVo attVo = new AttachVo();
				attVo.setAttBbsNo(vo.getBbsNo());
				attVo.setAttOrgName(f.getOriginalFilename());
				attVo.setAttNewName(fname);
				
				attachDao.insertAttach(attVo);
			} catch (IllegalStateException | IOException e) {
//				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		return num;
	}

	@Override
	public int delBbs(BbsVo vo) {
		BbsVo bbsVo = studentDao.selectBbs(vo.getBbsNo());
		
		if ( !bbsVo.getBbsWriter().equals( vo.getBbsWriter() ) ) return 0;
		
//		StudentVo stuVo;
//		bbsVo.setBbsWriter(stuVo.getStuNo());

		for( AttachVo attVo : bbsVo.getAttachList() ) {
			File file = new File(uploadPath, attVo.getAttNewName());
			file.delete();
			attachDao.deleteAttach( attVo.getAttNo());
		}

		
		return studentDao.delBbs(vo.getBbsNo());
	}

	@Override
	public BbsVo selectBbs(int bbsNo) {
		return studentDao.selectBbs(bbsNo);
	}

	@Override
	public int updateBbs(BbsVo vo) {
		return studentDao.updateBbs(vo);
	}

	@Override
	public BbsVo selectLogin(BbsVo vo) {
		return studentDao.selectLogin(vo);
	}

	@Override
	public AttachVo selectAttach(int attNo) {
		return attachDao.selectAttach(attNo);
	}

	@Override
	public File getAttachFile(AttachVo vo) {
		return new File(uploadPath, vo.getAttNewName());
	}

	

}