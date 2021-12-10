package kr.co.kmarket.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductOrderReviewVo {

	private int reviewId;
	private int productCode;
	private String content;
	private String uid;
	private int rating;
	private String ip;
	private String rdate;
	
}
