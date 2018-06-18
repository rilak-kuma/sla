package kr.maxerve.admin.member.vo;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.maxerve.dto.MemberDTO;
/**
 * 온라인멤버관리 조회
 * extends MemberDTO
 */
public class ReqMemberVO extends MemberDTO {

	private String depOne;				//공통코드 dep1
	
	private String creDttmOne;			//가입일 조회조건1
	private String creDttmTwo;			//가입일 조회조건2
	private String mvinGrpCd;			//입주그룹 공통코드
	private String aplyPgrCd;			//입주신청상태 공통코드(022)
	
	private String sortType;			//목록 sort
	private String pageIndex;			//페이지 index
	private PaginationInfo pageInfo;	//페이지정보
	
	public String getDepOne() {
		return depOne;
	}
	public void setDepOne(String depOne) {
		this.depOne = depOne;
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
	public String getMvinGrpCd() {
		return mvinGrpCd;
	}
	public void setMvinGrpCd(String mvinGrpCd) {
		this.mvinGrpCd = mvinGrpCd;
	}
	public String getAplyPgrCd() {
		return aplyPgrCd;
	}
	public void setAplyPgrCd(String aplyPgrCd) {
		this.aplyPgrCd = aplyPgrCd;
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
