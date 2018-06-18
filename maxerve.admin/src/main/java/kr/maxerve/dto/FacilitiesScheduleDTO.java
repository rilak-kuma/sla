package kr.maxerve.dto;

/**
* FacilitiesScheduleDTO
* @author LEEC.J
* @since 2018.06.07
* @version 1.0
* @see
*
* <pre>
* 시설예약일정
* TBL_FCT_SCH
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.07     LEEC.J        최초 생성
* </pre>
*/
public class FacilitiesScheduleDTO {
	private String fctMstIdx;  			// INT(11) NOT NULL COMMENT '시설 인덱스'
	private String fctDow;	    		// TINYINT(4) NOT NULL COMMENT '요일(공휴일 0)'
	private String srtTm;				// VARCHAR(5) NOT NULL COMMENT '시작시분'
	private String endTm;				// VARCHAR(5) NOT NULL COMMENT '종료시분'
	public String getFctMstIdx() {
		return fctMstIdx;
	}
	public void setFctMstIdx(String fctMstIdx) {
		this.fctMstIdx = fctMstIdx;
	}
	public String getFctDow() {
		return fctDow;
	}
	public void setFctDow(String fctDow) {
		this.fctDow = fctDow;
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
}
