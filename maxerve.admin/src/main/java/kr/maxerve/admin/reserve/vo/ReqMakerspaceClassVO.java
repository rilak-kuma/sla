package kr.maxerve.admin.reserve.vo;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.maxerve.dto.MakerspaceClassDTO;

/**
* ReqMakerspaceClassVO
* @author LEEC.J
* @since 2018.06.02
* @version 1.0
* @see
*
* <pre>
* 메이커스페이스 클래스
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.02     LEEC.J        최초 생성
* </pre>
*/
public class ReqMakerspaceClassVO extends MakerspaceClassDTO {
	private String page = "";	// 페이지
	private String countPerPage = "";	// 페이지당수
	private String sortType = "";	// 정렬타입
	private PaginationInfo pageInfo = null;	// 페이징

	private String fctMstIdx;		// int(11) NOT NULL COMMENT '시설 인덱스'
	private String rsvtPlcCd;		// VARCHAR(50) NOT NULL COMMENT '예약장소 공통코드(002)'
	private String mkspClsRsvCnt;	// 메이커스페이스 예약인원수
	private String srtTm;			// 교육시작시간

	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	public PaginationInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PaginationInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	public String getCountPerPage() {
		return countPerPage;
	}
	public void setCountPerPage(String countPerPage) {
		this.countPerPage = countPerPage;
	}
	public String getRsvtPlcCd() {
		return rsvtPlcCd;
	}
	public void setRsvtPlcCd(String rsvtPlcCd) {
		this.rsvtPlcCd = rsvtPlcCd;
	}
	public String getFctMstIdx() {
		return fctMstIdx;
	}
	public void setFctMstIdx(String fctMstIdx) {
		this.fctMstIdx = fctMstIdx;
	}
	public String getMkspClsRsvCnt() {
		return mkspClsRsvCnt;
	}
	public void setMkspClsRsvCnt(String mkspClsRsvCnt) {
		this.mkspClsRsvCnt = mkspClsRsvCnt;
	}
	public String getSrtTm() {
		return srtTm;
	}
	public void setSrtTm(String srtTm) {
		this.srtTm = srtTm;
	}
}
