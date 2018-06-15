package kr.maxerve.dto;

/**
* LogDTO.java
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
public class LogDTO {
	private String logIdx;		// INT(11) NOT NULL AUTO_INCREMENT COMMENT '로그 인덱스'
	private String logUrl;		// VARCHAR(1000) NOT NULL COMMENT 'URL'
	private String logPrmt;		// VARCHAR(1000) NOT NULL COMMENT '파라미터'
	private String mbrIdx;		// INT(11) NOT NULL COMMENT '회원 인덱스(비회원0)'
	private String locCd;		// VARCHAR(50) NOT NULL COMMENT '로그위치 공통코드(021)'
	private String rmtIp;		// VARCHAR(50) NOT NULL COMMENT '원격지 아이피'
	private String creDttm;		// DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시'

	public String getLogIdx() {
		return logIdx;
	}
	public void setLogIdx(String logIdx) {
		this.logIdx = logIdx;
	}
	public String getLogUrl() {
		return logUrl;
	}
	public void setLogUrl(String logUrl) {
		this.logUrl = logUrl;
	}
	public String getMbrIdx() {
		return mbrIdx;
	}
	public void setMbrIdx(String mbrIdx) {
		this.mbrIdx = mbrIdx;
	}
	public String getLocCd() {
		return locCd;
	}
	public void setLocCd(String locCd) {
		this.locCd = locCd;
	}
	public String getRmtIp() {
		return rmtIp;
	}
	public void setRmtIp(String rmtIp) {
		this.rmtIp = rmtIp;
	}
	public String getCreDttm() {
		return creDttm;
	}
	public void setCreDttm(String creDttm) {
		this.creDttm = creDttm;
	}
	public String getLogPrmt() {
		return logPrmt;
	}
	public void setLogPrmt(String logPrmt) {
		this.logPrmt = logPrmt;
	}


}
