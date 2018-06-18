package kr.maxerve.dto;

/**
* SurveyApplyDTO
* @author LEEC.J
* @since 2018.06.01
* @version 1.0
* @see
*
* <pre>
* 설문응답
* TBL_SVY_APLY
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.01     LEEC.J        최초 생성
* </pre>
*/
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
