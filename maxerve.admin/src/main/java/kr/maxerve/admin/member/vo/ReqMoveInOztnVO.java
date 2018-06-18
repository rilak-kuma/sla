package kr.maxerve.admin.member.vo;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.maxerve.dto.MoveInApplyDTO;
/**
 * 입주단체관리 VO
 * extends MoveInApplyDTO
 */
public class ReqMoveInOztnVO extends MoveInApplyDTO{


	private String onlineMb = "";		//온라인멤버여부
	private String mvinSrtDtOne = "";	//입주기간 시작일 조회조건1
	private String mvinSrtDtTwo = "";	//입주기간 시작일 조회조건2

	private String mvinYn = "";			//입주상태(Y:입주중,N:기간만료)
	private String creDttmOne = "";		//입주신청등록일 조회조건1
	private String creDttmTwo = "";		//입주신청등록일 조회조건2
	private String depOne = "";			//공통코드 dep1
	private String mbrTypCd = "";		//혁신멤버 구분 공통코드(015)

	private String sortType = "";			//목록 sort
	private String pageIndex = "";			//페이지 index
	private PaginationInfo pageInfo;	//페이지정보

	public String getOnlineMb() {
		return onlineMb;
	}
	public void setOnlineMb(String onlineMb) {
		this.onlineMb = onlineMb;
	}
	public String getMvinSrtDtOne() {
		return mvinSrtDtOne;
	}
	public void setMvinSrtDtOne(String mvinSrtDtOne) {
		this.mvinSrtDtOne = mvinSrtDtOne;
	}
	public String getMvinSrtDtTwo() {
		return mvinSrtDtTwo;
	}
	public void setMvinSrtDtTwo(String mvinSrtDtTwo) {
		this.mvinSrtDtTwo = mvinSrtDtTwo;
	}
	public String getMvinYn() {
		return mvinYn;
	}
	public void setMvinYn(String mvinYn) {
		this.mvinYn = mvinYn;
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
	public String getDepOne() {
		return depOne;
	}
	public void setDepOne(String depOne) {
		this.depOne = depOne;
	}
	public String getMbrTypCd() {
		return mbrTypCd;
	}
	public void setMbrTypCd(String mbrTypCd) {
		this.mbrTypCd = mbrTypCd;
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
