package kr.maxerve.admin.activity.vo;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.maxerve.dto.ReferenceRoomDTO;

public class ReqReferenceVO extends ReferenceRoomDTO{
	
	private String sortType;			//목록 sort
	private String creDttmOne;			//등록일시 검색조건1
	private String creDttmTwo;			//등록일시 검색조건2
	private String ctgrIdx;				//자료실카테고리코드
	private String ctgrTypCd;			//카테고리 유형코드
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
	public String getCtgrIdx() {
		return ctgrIdx;
	}
	public void setCtgrIdx(String ctgrIdx) {
		this.ctgrIdx = ctgrIdx;
	}
	public String getCtgrTypCd() {
		return ctgrTypCd;
	}
	public void setCtgrTypCd(String ctgrTypCd) {
		this.ctgrTypCd = ctgrTypCd;
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
