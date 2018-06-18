package kr.maxerve.admin.reserve.vo;

import kr.maxerve.dto.CalendarDTO;

public class CalenderFacilitiesReserveMVO extends CalendarDTO{

	private String fctRsvtIdx = "";		// int(11) Not Null AutoIncrement Comment '시설예약 인덱스'
	private String tm = "";				// 시간
	private String srtDttm = "";		//시작일자(시:분)
	private String endDttm = "";		//종료일자(시:
	private String oztnNm = "";			//단체명

	public String getFctRsvtIdx() {
		return fctRsvtIdx;
	}
	public void setFctRsvtIdx(String fctRsvtIdx) {
		this.fctRsvtIdx = fctRsvtIdx;
	}
	public String getTm() {
		return tm;
	}
	public void setTm(String tm) {
		this.tm = tm;
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
	public String getOztnNm() {
		return oztnNm;
	}
	public void setOztnNm(String oztnNm) {
		this.oztnNm = oztnNm;
	}
}
