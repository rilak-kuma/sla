package kr.maxerve.admin.reserve.vo;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.maxerve.dto.FacilitiesMasterDTO;

/**
* ReqFacilitiesMasterVO
* @author LEEC.J
* @since 2018.06.4
* @version 1.0
* @see
*
* <pre>
* 시설예약관리
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.04     LEEC.J        최초 생성
* </pre>
*/
public class ReqFacilitiesMasterVO extends FacilitiesMasterDTO {
	private String page = "";	// 페이지
	private String countPerPage = "";	 // 페이지당 수
	private String sortType = "";	// 정렬타입
	private PaginationInfo pageInfo;	// 페이징

	private String year = "";	// 년도
	private String month = "";	// 월
	private String srtDay = "";	// 일
	private String srtDt = "";	// 시작일자
	private String sumDay = ""; //다음 또는 이전(7 or -7)
	private String fctType = "";	//회의세미나에서 모두모임방 구분
	private String srtHm = "";		//시작시간
	private String endHm = "";		//종료시간
	
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public PaginationInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PaginationInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	public String getCountPerPage() {
		return countPerPage;
	}
	public void setCountPerPage(String countPerPage) {
		this.countPerPage = countPerPage;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getSrtDay() {
		return srtDay;
	}
	public void setSrtDay(String srtDay) {
		this.srtDay = srtDay;
	}
	public String getSrtDt() {
		return srtDt;
	}
	public void setSrtDt(String srtDt) {
		this.srtDt = srtDt;
	}
	public String getSumDay() {
		return sumDay;
	}
	public void setSumDay(String sumDay) {
		this.sumDay = sumDay;
	}
	public String getFctType() {
		return fctType;
	}
	public void setFctType(String fctType) {
		this.fctType = fctType;
	}
	public String getSrtHm() {
		return srtHm;
	}
	public void setSrtHm(String srtHm) {
		this.srtHm = srtHm;
	}
	public String getEndHm() {
		return endHm;
	}
	public void setEndHm(String endHm) {
		this.endHm = endHm;
	}
}
