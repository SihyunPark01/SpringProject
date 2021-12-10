package kr.co.kmarket.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCartVo {
	
	private int cartId;
	private String uid;
	private int productCode;
	private int count;
	private int price;
	private int discount;
	private int point;
	private int delivery;
	private int total;
	private String rdate;
	
	
	//추가필드 - 보통 뷰페이지에서 쓰이고 쿼리문에서 날릴때 필요하군...
	private int cate1;
	private int cate2;
	private String name;
	private String thumb1;
		
}
