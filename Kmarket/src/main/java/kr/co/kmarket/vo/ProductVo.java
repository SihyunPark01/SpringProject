package kr.co.kmarket.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductVo {

	private int productCode;
	private int cate1;
	private int cate2;
	private String name;
	private String descript;
	private String company;
	private String seller;
	private int price;
	private int discount;
	private int point;
	private int stock;
	private int sold;
	private int delivery;
	private int hit;
	private int score;
	private int review;
	private String thumb1;
	private String thumb2;
	private String thumb3;
	private String detail;
	private String status;
	private String dutyFree;
	private String receipt;
	private String bizClass;
	private String origin;
	private String ip;
	private String rdate;
	private int etc1;
	private int etc2;
	private String etc3;
	private String etc4;
	private String etc5;
	
	//추가필드 1
	private int salePrice;
	private String tit1;
	private String tit2;
	private int order = 1; //order의 기본값은 int라 0이므로 1로 초기화하는 작업이 필요함. 
	
	// 페이지 처리 관련 추가필드
	private int start;
	
	//추가필드 2
	private MultipartFile thumbFile1; //파일업로드를 위한 인터페이스 MultipartFile !
	private MultipartFile thumbFile2;
	private MultipartFile thumbFile3;
	private MultipartFile detailFile4;
	
	//추가필드 3
	
}
