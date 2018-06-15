package kr.maxerve.dto;

/**
* CalendarDTO.java
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
public class CalendarDTO {
	private String clndDt	= "";  	// VARCHAR(10) NOT NULL COMMENT '양력',
	private String lnrDt	= "";  	// VARCHAR(10) NOT NULL COMMENT '음력',
	private String yunYn	= "";  	// VARCHAR(1) NOT NULL COMMENT '윤달여부',
	private String ganji	= "";  	// VARCHAR(10) NOT NULL COMMENT '간지',
	private String dow		= "";  	// TINYINT(4) NOT NULL COMMENT '요일',
	private String hdyYn	= "";  	// VARCHAR(1) NOT NULL COMMENT '법정공휴일여부',

	public String getClndDt() {
		return clndDt;
	}
	public void setClndDt(String clndDt) {
		this.clndDt = clndDt;
	}
	public String getLnrDt() {
		return lnrDt;
	}
	public void setLnrDt(String lnrDt) {
		this.lnrDt = lnrDt;
	}
	public String getYunYn() {
		return yunYn;
	}
	public void setYunYn(String yunYn) {
		this.yunYn = yunYn;
	}
	public String getGanji() {
		return ganji;
	}
	public void setGanji(String ganji) {
		this.ganji = ganji;
	}
	public String getDow() {
		return dow;
	}
	public void setDow(String dow) {
		this.dow = dow;
	}
	public String getHdyYn() {
		return hdyYn;
	}
	public void setHdyYn(String hdyYn) {
		this.hdyYn = hdyYn;
	}
}
