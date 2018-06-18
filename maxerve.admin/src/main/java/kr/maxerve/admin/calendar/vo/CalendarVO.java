package kr.maxerve.admin.calendar.vo;

/**
* CalendarVO
* @author LEEC.J
* @since 2018.06.07
* @version 1.0
* @see
*
* <pre>
* 달력검색
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.07     LEEC.J        최초 생성
* </pre>
*/
public class CalendarVO {
	private String srtDt = "";	// 검색시작
	private String endDt = "";	// 검색끝

	public String getSrtDt() {
		return srtDt;
	}
	public void setSrtDt(String srtDt) {
		this.srtDt = srtDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
}
