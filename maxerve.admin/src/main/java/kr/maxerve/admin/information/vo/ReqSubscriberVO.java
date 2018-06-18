package kr.maxerve.admin.information.vo;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.maxerve.dto.NewsLetterSubscribeDTO;

public class ReqSubscriberVO extends NewsLetterSubscribeDTO{
	
	private String creDttmOne;			//신청일 검색조건1
	private String creDttmTwo;			//신청일 검색조건2
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
