package kr.maxerve.dto;

public class NewsLetterSubscribeDTO {
	private String emil = "";			// varchar(200) NOT NULL COMMENT '이메일'
	private String scbNm = "";			// Varchar(50) NOT NULL COMMENT '구독자명'
	private String scbLocCd = "";		// VARCHAR(50) NOT NULL COMMENT '구독신청경로 공통코드(032)',
	private String dsdYn = "";			// VARCHAR(1) NOT NULL DEFAULT 'N' COMMENT '인증여부',
	private String creDttm = "";			// datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시'

	public String getEmil() {
		return emil;
	}
	public void setEmil(String emil) {
		this.emil = emil;
	}
	public String getScbNm() {
		return scbNm;
	}
	public void setScbNm(String scbNm) {
		this.scbNm = scbNm;
	}
	public String getCreDttm() {
		return creDttm;
	}
	public void setCreDttm(String creDttm) {
		this.creDttm = creDttm;
	}
	public String getScbLocCd() {
		return scbLocCd;
	}
	public void setScbLocCd(String scbLocCd) {
		this.scbLocCd = scbLocCd;
	}
	public String getDsdYn() {
		return dsdYn;
	}
	public void setDsdYn(String dsdYn) {
		this.dsdYn = dsdYn;
	}
}
