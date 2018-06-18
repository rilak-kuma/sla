package kr.maxerve.admin.information.vo;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.maxerve.dto.NewsLetterDTO;

public class ReqNewsLetterVO extends NewsLetterDTO{
	
	private String sortType;			//목록 sort
	private String creDttmOne;			//등록일시 검색조건1
	private String creDttmTwo;			//등록일시 검색조건2
	
	private String pageIndex;			//페이지 index
	private PaginationInfo pageInfo;	//페이지정보
	
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
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
