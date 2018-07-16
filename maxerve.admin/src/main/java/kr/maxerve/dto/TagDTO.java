package kr.maxerve.dto;

/**
* TagDTO
* @author LEEC.J
* 2018.06.22
* @version 1.0
* @see
*
* <pre>
* 태그
* TBL_TAG
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.22     LEEC.J        최초 생성
* </pre>
*/
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
