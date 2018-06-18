package kr.maxerve.admin.adjust.vo;

/**
* ReqSponsorAdjustVO
* @author LEEC.J
* @since 2018.06.14
* @version 1.0
* @see
*
* <pre>
* 후원정산
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.14     LEEC.J        최초 생성
* </pre>
*/
public class ReqSponsorAdjustVO {
	private String page = "1";	// 페이지
	private int countPerPage;
	private int limitIndex;
	private String srchDt = "";		// 검색 정산일
	private String dt = "";	// 정산일
	private String pjtIdx = "";		// 프로젝트 인덱스
	private String detailPage = "1";	// 상세페이지

	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public int getCountPerPage() {
		return countPerPage;
	}
	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
	}
	public int getLimitIndex() {
		return limitIndex;
	}
	public void setLimitIndex(int limitIndex) {
		this.limitIndex = limitIndex;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getSrchDt() {
		return srchDt;
	}
	public void setSrchDt(String srchDt) {
		this.srchDt = srchDt;
	}
	public String getDetailPage() {
		return detailPage;
	}
	public void setDetailPage(String detailPage) {
		this.detailPage = detailPage;
	}
	public String getPjtIdx() {
		return pjtIdx;
	}
	public void setPjtIdx(String pjtIdx) {
		this.pjtIdx = pjtIdx;
	}
}
