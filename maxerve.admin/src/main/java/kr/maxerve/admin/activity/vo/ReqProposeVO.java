package kr.maxerve.admin.activity.vo;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.maxerve.dto.ProposeDTO;

public class ReqProposeVO extends ProposeDTO{

	private String cmmtLocCd;			//소재지 공통코드
	private String creDttmOne;			//등록일시 검색조건1
	private String creDttmTwo;			//등록일시 검색조건2

	private String pageIndex;			//페이지 index
	private PaginationInfo pageInfo;	//페이지정보
	
	public String getCmmtLocCd() {
		return cmmtLocCd;
	}
	public void setCmmtLocCd(String cmmtLocCd) {
		this.cmmtLocCd = cmmtLocCd;
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
