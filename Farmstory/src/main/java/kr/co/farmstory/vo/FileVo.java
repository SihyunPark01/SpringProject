package kr.co.farmstory.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileVo {
	private int fseq;
	private int parent;
	private String oriName;
	private String newName;
	private int download;
	private String rdate;
}
