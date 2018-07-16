package kr.maxerve.dto;

/**
* SurveyDTO
* @author LEEC.J
* @since 2018.06.01
* @version 1.0
* @see
*
* <pre>
* 설문
* TBL_SVY
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.01     LEEC.J        최초 생성
* </pre>
*/
public class SurveyDTO {
	private String svyIdx   = "";		// 	INT(11) NOT NULL AUTO_INCREMENT COMMENT '설문인덱스',
	private String locTypCd = "";		// 	VARCHAR(50) NOT NULL COMMENT '소재지 공통코드(016)',
	private String locIdx	 = "";		// 	INT(11) NOT NULL COMMENT '소재지 인덱스',
	private String srtDttm	 = "";		// 	DATETIME NOT NULL COMMENT '설문시작기간',
	private String endDttm	 = "";		// 	DATETIME NOT NULL COMMENT '설문종료기간',
	private String svyTitl	 = "";		// 	VARCHAR(50) NOT NULL COMMENT '설문제목',
	private String emil	 = "";		// 	VARCHAR(100) NOT NULL COMMENT '발신메일',
	private String creDttm	 = "";		// 	DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시',
	private String modDttm	 = "";		// 	DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',

	public String getSvyIdx() {
		return svyIdx;
	}
	public void setSvyIdx(String svyIdx) {
		this.svyIdx = svyIdx;
	}
	public String getLocTypCd() {
		return locTypCd;
	}
	public void setLocTypCd(String locTypCd) {
		this.locTypCd = locTypCd;
	}
	public String getLocIdx() {
		return locIdx;
	}
	public void setLocIdx(String locIdx) {
		this.locIdx = locIdx;
	}
	public String getSrtDttm() {
		return srtDttm;
	}
	public void setSrtDttm(String srtDttm) {
		this.srtDttm = srtDttm;
	}
	public String getEndDttm() {
		return endDttm;
	}
	public void setEndDttm(String endDttm) {
		this.endDttm = endDttm;
	}
	public String getSvyTitl() {
		return svyTitl;
	}
	public void setSvyTitl(String svyTitl) {
		this.svyTitl = svyTitl;
	}
	public String getEmil() {
		return emil;
	}
	public void setEmil(String emil) {
		this.emil = emil;
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
