package kr.maxerve.dto;

public class SurveyApplyDTO {
	private String mbrIdx		  = ""; 	// INT(11) NOT NULL COMMENT '회원 인덱스',
	private String svyQstnIdx    = ""; 	// INT(11) NOT NULL COMMENT '설문문제 인덱스',
	private String svyIdx		  = ""; 	// INT(11) NOT NULL COMMENT '설문인덱스',
	private String ans			  = ""; 	// VARCHAR(1000) NOT NULL COMMENT '주관식답변',
	private String creDttm	      = ""; 	// DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시',

	public String getMbrIdx() {
		return mbrIdx;
	}
	public void setMbrIdx(String mbrIdx) {
		this.mbrIdx = mbrIdx;
	}
	public String getSvyQstnIdx() {
		return svyQstnIdx;
	}
	public void setSvyQstnIdx(String svyQstnIdx) {
		this.svyQstnIdx = svyQstnIdx;
	}
	public String getSvyIdx() {
		return svyIdx;
	}
	public void setSvyIdx(String svyIdx) {
		this.svyIdx = svyIdx;
	}
	public String getAns() {
		return ans;
	}
	public void setAns(String ans) {
		this.ans = ans;
	}
	public String getCreDttm() {
		return creDttm;
	}
	public void setCreDttm(String creDttm) {
		this.creDttm = creDttm;
	}
}
