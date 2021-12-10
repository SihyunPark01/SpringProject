package kr.co.kmarket.vo;

import lombok.Data;

@Data
public class SearchVo {

	private String keyword;
	private int chk1 = 1;   //체크안하면 0, 체크하면 1
	private int chk2;	//체크안하면 0, 체크하면 1
	private int chk3;	//체크안하면 0, 체크하면 1
	private int min;
	private int max;
}
