package kr.co.kmarket.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberPointVo {
	
	private int pointId;
	private String uid;
	private int productCode;
	private int price;
	private int point;
	private String rdate;
}
