package kr.maxerve.admin.account.vo;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.maxerve.dto.ManagerMemberDTO;

public class ReqManagerMemberVO extends ManagerMemberDTO {
	/**
	 * 내 정보 업데이트
	 */
	@NotEmpty
	private String mngrMbrPwd = "";		// INT(11) NOT NULL AUTO_INCREMENT COMMENT '관리자계정 인덱스'
	@Email
	private String emil = "";			// VARCHAR(50) NOT NULL COMMENT '이메일'

	private String mngrMbrPwdNew = ""; // 새 비밀번호

	/**
	 * 회원목록
	 */
	private String searchType = "";		// 검색타입
	private String searchText = "";		// 검색어

	private String page = "1";			// 현재페이지
	private PaginationInfo pageInfo;

	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getMngrMbrPwd() {
		return mngrMbrPwd;
	}
	public void setMngrMbrPwd(String mngrMbrPwd) {
		this.mngrMbrPwd = mngrMbrPwd;
	}
	public String getEmil() {
		return emil;
	}
	public void setEmil(String emil) {
		this.emil = emil;
	}
	public String getMngrMbrPwdNew() {
		return mngrMbrPwdNew;
	}
	public void setMngrMbrPwdNew(String mngrMbrPwdNew) {
		this.mngrMbrPwdNew = mngrMbrPwdNew;
	}
	public PaginationInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PaginationInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
}
