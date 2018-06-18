package kr.maxerve.admin.reserve.vo;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.maxerve.dto.RentTheAreaDTO;

/**
* ReqRentAreaVO
* @author LEEC.J
* @since 2018.06.07
* @version 1.0
* @see
*
* <pre>
* 대관일정
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.07     LEEC.J        최초 생성
* </pre>
*/
public class ReqRentAreaVO extends RentTheAreaDTO {
	private String page = "";	// 페이지
	private String countPerPage = "";	// 페이지당수
	private String sortType = "";		// 정렬타입

	private PaginationInfo pageInfo; // 페이징

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
	} // 페이징
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
}
