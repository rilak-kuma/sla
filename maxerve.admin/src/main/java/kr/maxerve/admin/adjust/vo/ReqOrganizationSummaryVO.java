package kr.maxerve.admin.adjust.vo;

/**
* ReqOrganizationSummaryVO
* @author LEEC.J
* @since 2018.06.14
* @version 1.0
* @see
*
* <pre>
* 단체별정산
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.14     LEEC.J        최초 생성
* </pre>
*/
public class ReqOrganizationSummaryVO {
	private String page = "1";	// 페이지
	private int countPerPage;
	private int limitIndex;
	private String srtDt = "";		// 결제시작일
	private String endDt = "";		// 결제종료일
	private String dt = "";		// 상세일
	private String organizationPage = "1";		// 단체페이지
	private String oztnNm = "";		// 단체명
	private String srchStatus = "";		// 상태
	private String paymentPage = "1";	// 결제페이지
	private String ajmYn = "";		// 정산여부

	private String locTypCd = "";	// 단체유형코드
	private String locIdx = "";		// 단체인덱스

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
	public String getSrtDt() {
		return srtDt;
	}
	public void setSrtDt(String srtDt) {
		this.srtDt = srtDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getOrganizationPage() {
		return organizationPage;
	}
	public void setOrganizationPage(String organizationPage) {
		this.organizationPage = organizationPage;
	}
	public String getOztnNm() {
		return oztnNm;
	}
	public void setOztnNm(String oztnNm) {
		this.oztnNm = oztnNm;
	}
	public String getSrchStatus() {
		return srchStatus;
	}
	public void setSrchStatus(String srchStatus) {
		this.srchStatus = srchStatus;
	}
	public String getPaymentPage() {
		return paymentPage;
	}
	public void setPaymentPage(String paymentPage) {
		this.paymentPage = paymentPage;
	}
	public String getAjmYn() {
		return ajmYn;
	}
	public void setAjmYn(String ajmYn) {
		this.ajmYn = ajmYn;
	}
	public String getLocTypCd() {
		return locTypCd;
	}
	public void setLocTypCd(String locTypCd) {
		this.locTypCd = locTypCd;
	}
	public String getLocIdx() {
		return locIdx;
	}
	public void setLocIdx(String locIdx) {
		this.locIdx = locIdx;
	}
}
