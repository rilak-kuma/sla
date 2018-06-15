package kr.maxerve.dto;

public class TagDTO {
	private String tagNm;			// varchar(50) NOT NULL COMMENT '태그명'
	private String tagCnt;			// int(11) NOT NULL COMMENT '태그 카운트'
	private String creDttm;			// datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일'

	public String getTagNm() {
		return tagNm;
	}
	public void setTagNm(String tagNm) {
		this.tagNm = tagNm;
	}
	public String getTagCnt() {
		return tagCnt;
	}
	public void setTagCnt(String tagCnt) {
		this.tagCnt = tagCnt;
	}
	public String getCreDttm() {
		return creDttm;
	}
	public void setCreDttm(String creDttm) {
		this.creDttm = creDttm;
	}
}
