package kr.maxerve.dto;

/**
* WoodparkClassScheduleDTO
* @author LEEC.J
* @since 2018.06.07
* @version 1.0
* @see
*
* <pre>
* 우드파크 클래스 일정
* TBL_WDPK_CLS_SCHEDULE
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.07     LEEC.J        최초 생성
* </pre>
*/
public class WoodparkClassScheduleDTO {
	private String fctMstIdx = "";			// INT(11) NOT NULL COMMENT '시설 인덱스',
	private String clndDt = "";				// VARCHAR(10) NOT NULL COMMENT '날짜',
	private String srtTm = "";				// VARCHAR(5) NOT NULL COMMENT '시작시간',
	private String endTm = "";				// VARCHAR(5) NOT NULL COMMENT '종료시간',
	private String wdpkClsIdx = "";			// INT(11) NOT NULL COMMENT '클래스 인덱스',

	public String getFctMstIdx() {
		return fctMstIdx;
	}
	public void setFctMstIdx(String fctMstIdx) {
		this.fctMstIdx = fctMstIdx;
	}
	public String getClndDt() {
		return clndDt;
	}
	public void setClndDt(String clndDt) {
		this.clndDt = clndDt;
	}
	public String getSrtTm() {
		return srtTm;
	}
	public void setSrtTm(String srtTm) {
		this.srtTm = srtTm;
	}
	public String getEndTm() {
		return endTm;
	}
	public void setEndTm(String endTm) {
		this.endTm = endTm;
	}
	public String getWdpkClsIdx() {
		return wdpkClsIdx;
	}
	public void setWdpkClsIdx(String wdpkClsIdx) {
		this.wdpkClsIdx = wdpkClsIdx;
	}
}
