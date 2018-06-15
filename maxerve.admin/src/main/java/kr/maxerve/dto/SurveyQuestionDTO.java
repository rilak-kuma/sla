package kr.maxerve.dto;

public class SurveyQuestionDTO {
	private String svyQstnIdx    = ""; 		// INT(11) NOT NULL AUTO_INCREMENT COMMENT '질문인덱스',
	private String grpIdx		  = ""; 		// INT(11) NOT NULL DEFAULT '0' COMMENT '그룹인덱스(질문인덱스와 같으면 질문)',
	private String svyIdx		  = ""; 		// INT(11) NOT NULL COMMENT '설문 인덱스',
	private String ord			  = ""; 		// INT(11) NOT NULL COMMENT '순서',
	private String svyTitl	      = ""; 		// VARCHAR(50) NOT NULL COMMENT '질문및항목',
	private String svyTypCd	  = ""; 		// VARCHAR(50) NOT NULL COMMENT '설문종류 공통코드(013)',
	private String delYn		  = ""; 		// VARCHAR(1) NOT NULL COMMENT '삭제여부',
	private String creDttm	      = ""; 		// DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시',
	private String modDttm	      = ""; 		// DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',

	public String getSvyQstnIdx() {
		return svyQstnIdx;
	}
	public void setSvyQstnIdx(String svyQstnIdx) {
		this.svyQstnIdx = svyQstnIdx;
	}
	public String getGrpIdx() {
		return grpIdx;
	}
	public void setGrpIdx(String grpIdx) {
		this.grpIdx = grpIdx;
	}
	public String getSvyIdx() {
		return svyIdx;
	}
	public void setSvyIdx(String svyIdx) {
		this.svyIdx = svyIdx;
	}
	public String getOrd() {
		return ord;
	}
	public void setOrd(String ord) {
		this.ord = ord;
	}
	public String getSvyTitl() {
		return svyTitl;
	}
	public void setSvyTitl(String svyTitl) {
		this.svyTitl = svyTitl;
	}
	public String getSvyTypCd() {
		return svyTypCd;
	}
	public void setSvyTypCd(String svyTypCd) {
		this.svyTypCd = svyTypCd;
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
}
