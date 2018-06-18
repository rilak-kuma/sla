package kr.maxerve.admin.reserve.vo;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.maxerve.dto.TourApplyDTO;

public class ReqParkTourVO extends TourApplyDTO{

	private String creDttmOne;			//신청일 조회조건1
	private String creDttmTwo;			//신청일 조회조건2
	private String tourDttmOne;			//투어일 조회조건1
	private String tourDttmTwo;			//투어일 조회조건2

	private String sortType;			//목록 sort
	private String pageIndex;			//페이지 index
	private PaginationInfo pageInfo;	//페이지정보
	
	public String getCreDttmOne() {
		return creDttmOne;
	}
	public void setCreDttmOne(String creDttmOne) {
		this.creDttmOne = creDttmOne;
	}
	public String getCreDttmTwo() {
		return creDttmTwo;
	}
	public void setCreDttmTwo(String creDttmTwo) {
		this.creDttmTwo = creDttmTwo;
	}
	public String getTourDttmOne() {
		return tourDttmOne;
	}
	public void setTourDttmOne(String tourDttmOne) {
		this.tourDttmOne = tourDttmOne;
	}
	public String getTourDttmTwo() {
		return tourDttmTwo;
	}
	public void setTourDttmTwo(String tourDttmTwo) {
		this.tourDttmTwo = tourDttmTwo;
	}
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	public String getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}
	public PaginationInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PaginationInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
}
