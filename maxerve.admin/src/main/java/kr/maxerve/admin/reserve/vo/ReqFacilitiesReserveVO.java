package kr.maxerve.admin.reserve.vo;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.maxerve.dto.FacilitiesReserveDTO;

/**
* ReqReserveVO
* @author LEEC.J
* @since 2018.06.01
* @version 1.0
* @see
*
* <pre>
* 시설예약
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.01     LEEC.J        최초 생성
* </pre>
*/
public class ReqFacilitiesReserveVO extends FacilitiesReserveDTO {
	private String rsvtPlcCd;			//VARCHAR(50) NOT NULL COMMENT '예약장소 공통코드(002)'
	private String creDttmOne;			//신청일 조회조건1
	private String creDttmTwo;			//신청일 조회조건2
	private String fctNm;				//시설명

	private String sortType;			//목록 sort
	private String pageIndex;			//페이지 index
	private PaginationInfo pageInfo;	//페이지정보
	
	public String getRsvtPlcCd() {
		return rsvtPlcCd;
	}
	public void setRsvtPlcCd(String rsvtPlcCd) {
		this.rsvtPlcCd = rsvtPlcCd;
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
	public String getFctNm() {
		return fctNm;
	}
	public void setFctNm(String fctNm) {
		this.fctNm = fctNm;
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
