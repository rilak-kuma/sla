package kr.maxerve.admin.account.vo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.maxerve.dto.ManagerMenuDTO;

/**
* ReqManagerMemberUVO.java
* @author LEEC.J
* @since 2018.06.15
* @version 1.0
* @see
*
* <pre>
* 파일
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.15      LEEC.J         최초 생성
* </pre>
*/
public class ReqManagerMemberUVO extends ManagerMemberUVO {
	/**
	 * 회원목록
	 */
	private String searchType = "";		// 검색타입
	private String searchText = "";		// 검색어

	private String page = "1";			// 현재페이지
	private PaginationInfo pageInfo;

	@NotEmpty
	private List<ManagerMenuDTO> mngrMnuList = new ArrayList<ManagerMenuDTO>(); // 메뉴권한

	public List<ManagerMenuDTO> getMngrMnuList() {
		return mngrMnuList;
	}

	public void setMngrMnuList(List<ManagerMenuDTO> mngrMnuList) {
		this.mngrMnuList = mngrMnuList;
	}

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

	public PaginationInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PaginationInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
}
