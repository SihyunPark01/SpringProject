package kr.co.farmstory.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ArticleVo {

	private int seq;
	private int parent;
	private int comment;
	private String cate;
	private String title;
	private String content;
	private int file;
	private int hit;
	private String uid;
	private String regip;
	private String rdate;
	
	//추가필드
	private MultipartFile fname;
	private String nick;
	private FileVo fileVo;
	private String group;
	
}
