package kr.co.farmstory.service;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.farmstory.dao.BoardDao;
import kr.co.farmstory.vo.ArticleVo;
import kr.co.farmstory.vo.FileVo;


@Service
public class BoardService {

	@Autowired
	private BoardDao dao;
	
	@Value("${file.upload-dir}")
	String uploadFileDir;

	public int insertArticle(ArticleVo vo) {
		dao.insertArticle(vo);
		return vo.getSeq();
	}
	
	public void insertFile(FileVo vo){
		dao.insertFile(vo);
	}
	
	public void insertComment(ArticleVo vo){
		dao.insertComment(vo);
	}
	
	public ArticleVo selectArticle(int seq) {
		return dao.selectArticle(seq);
	}
	public List<ArticleVo> selectArticles(int start , String cate){
		return dao.selectArticles(start, cate);
	}
	
	public FileVo selectFile(int fseq) {
		return dao.selectFile(fseq);
	}
	
	public ArticleVo selectComment(int seq) {
		return dao.selectComment(seq);
	}
	
	public List<ArticleVo> selectComments(int seq) {
		return dao.selectComments(seq);
	}
	
	public int selectCountTotal(String cate) {
		return dao.selectCountTotal(cate);
	}
	
	public void updateArticle(ArticleVo vo){
		dao.updateArticle(vo);
	}
	public void updateFileDownload(int fseq) {
        dao.updateFileDownload(fseq);
    }
	public void updateArticleHit(int seq) {
    	dao.updateArticleHit(seq);
    }
	
	public int updateComment(int seq, String content){
		return dao.updateComment(seq, content);
	}
	public void deleteArticle(int seq){
		dao.deleteArticle(seq);
	}
	public void deleteComment(int seq){
		dao.deleteComment(seq);
	}
	
	
	
	
	
/////////////////////비즈니스 처리 로직 구현 메서드////////////////////////
	
	//파일 업로드 처리
	public FileVo fileUpload(MultipartFile fname, int seq){
	//파일 객체 생성 3줄은 자동화시켜라
	
	
	//중복 되지 않게 파일 이름 구하기 (oriName)
	String name = fname.getOriginalFilename();
	//이름에서 확장자 따로 분리하기
	String ext = name.substring(name.lastIndexOf("."));
	//이번엔 날짜와 시간이 아닌 유니버셜 네임(16자리정도)을 만들자 (newName)
	String uName = UUID.randomUUID().toString()+ext;
	
	FileVo fvo = null;
	
	String path = new File(uploadFileDir).getAbsolutePath();

	File file = new File(path);
	//저장할 위치의 디렉토리가 존재하지 않는 경우
	if(!file.exists()) {
		file.mkdirs();
	}
	try {
		//첨부파일 저장
		fname.transferTo(new File(path+"/"+uName));
		
		//첨부파일 정보객체 생성
		fvo = new FileVo();
		fvo.setParent(seq);
		fvo.setOriName(name);
		fvo.setNewName(uName);
		
	} catch (Exception e) {
		e.printStackTrace();
	}		
		return fvo;
	}	
	
	 // 파일 다운로드
    public void fileDownload(HttpServletResponse resp, FileVo fileVo) {
    	
    	
    	String path = new File(uploadFileDir).getAbsolutePath();
    	        
        try {
        	File file = new File(path + "/" + fileVo.getNewName());
            byte[] fileByte = Files.readAllBytes(file.toPath());;

            // 파일 다운로드 response 헤더수정
            resp.setContentType("application/octet-stream");
            resp.setHeader("Content-Disposition",
                    "attachment; filename=" + URLEncoder.encode(fileVo.getOriName(), "utf-8"));
            resp.setHeader("Content-Transfer-Encoding", "binary");
            resp.setHeader("Pragma", "no-cache");
            resp.setHeader("Cache-Control", "private");

            resp.getOutputStream().write(fileByte);
            resp.getOutputStream().flush();
            resp.getOutputStream().close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
//////페이지 처리를 위한 로직 메서드 5개 (이건 걍 외우자)
	
	//페이지 리스트 시작번호 
	public int getPageStartNum(int total, int start) {
		return total - start;
	}
	//페이지 현재 그룹번호(이전, 다음 계산 위함)
	public int[] getPageGroup(int currentPage, int lastPageNum) {
		int groupCurrent = (int) Math.ceil(currentPage / 10.0); //ceil은 올림함수잖아
		int groupStart = (groupCurrent - 1) * 10 + 1;
		int groupEnd = groupCurrent * 10;
		
		if(groupEnd > lastPageNum) {
			groupEnd = lastPageNum;
		}
		int[] groups = {groupStart, groupEnd};
		
		return groups;
	}
	
	//현재 리스트 페이지 번호
	public int getCurrentPage(String pg) {
		int currentPage = 1;
		
		if(pg != null) {
			currentPage = Integer.parseInt(pg);
		}
		return currentPage;
	}
	
	//현재 리스트 SQL start번호 (쿼리문에 사용될 start번호)
	public int getLimitStart(int currentPage) {
		return (currentPage - 1) * 10;
	}
	
	//리스트 마지막 페이지 번호
	public int getLastPageNum(int total) {
		int lastPageNum = 0;
		if(total % 10 == 0) {
			lastPageNum = total / 10;
		}else {
			lastPageNum = total / 10 + 1;
		}
		return lastPageNum;
	}
	
}
