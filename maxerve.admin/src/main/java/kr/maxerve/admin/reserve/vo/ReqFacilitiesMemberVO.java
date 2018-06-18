package kr.maxerve.admin.reserve.vo;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.maxerve.dto.FacilitiesMemberDTO;

public class ReqFacilitiesMemberVO extends FacilitiesMemberDTO{

	private String sortType;			//목록 sort
	private String pageIndex;			//페이지 index
	private PaginationInfo pageInfo;	//페이지정보
	
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
