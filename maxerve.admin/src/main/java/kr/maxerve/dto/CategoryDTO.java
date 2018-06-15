package kr.maxerve.dto;

/**
* CategoryDTO.java
* @author LEEC.J
* @since 2018.06.15
* @version 1.0
* @see
*
* <pre>
* 파일
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.15      LEEC.J         최초 생성
* </pre>
*/
public class CategoryDTO {
	private String ctgrIdx = "";		// INT(11) NOT NULL AUTO_INCREMENT COMMENT '카테고리 인덱스'
	private String ctgrTypCd = "";	// VARCHAR(50) NOT NULL COMMENT '카테고리 종류 공통코드(009)'
	private String ctgrNm = "";		// 카테고리명
	private String ctgrImg = "";		// VARCHAR(200) NOT NULL COMMENT '카테고리 이미지'
	private String ctgrOrd = "";		// TINYINT(4) NOT NULL COMMENT '카테고리 순서'
	private String refTypCd = "";		// VARCHAR(50) NOT NULL DEFAULT '000' COMMENT '자료유형 공통코드(004)',
	private String spoYn = "";			// VARCHAR(1) NOT NULL DEFAULT 'N' COMMENT '후원여부',
	private String delYn = "";		// VARCHAR(1) NOT NULL COMMENT '삭제여부'
	private String creDttm = "";		// DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시'
	private String modDttm = "";		// DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시'

	public String getCtgrIdx() {
		return ctgrIdx;
	}
	public void setCtgrIdx(String ctgrIdx) {
		this.ctgrIdx = ctgrIdx;
	}
	public String getCtgrTypCd() {
		return ctgrTypCd;
	}
	public void setCtgrTypCd(String ctgrTypCd) {
		this.ctgrTypCd = ctgrTypCd;
	}
	public String getCtgrImg() {
		return ctgrImg;
	}
	public void setCtgrImg(String ctgrImg) {
		this.ctgrImg = ctgrImg;
	}
	public String getCtgrOrd() {
		return ctgrOrd;
	}
	public void setCtgrOrd(String ctgrOrd) {
		this.ctgrOrd = ctgrOrd;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public String getCreDttm() {
		return creDttm;
	}
	public void setCreDttm(String creDttm) {
		this.creDttm = creDttm;
	}
	public String getModDttm() {
		return modDttm;
	}
	public void setModDttm(String modDttm) {
		this.modDttm = modDttm;
	}
	public String getCtgrNm() {
		return ctgrNm;
	}
	public void setCtgrNm(String ctgrNm) {
		this.ctgrNm = ctgrNm;
	}
	public String getRefTypCd() {
		return refTypCd;
	}
	public void setRefTypCd(String refTypCd) {
		this.refTypCd = refTypCd;
	}
	public String getSpoYn() {
		return spoYn;
	}
	public void setSpoYn(String spoYn) {
		this.spoYn = spoYn;
	}
}
