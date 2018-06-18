package kr.maxerve.admin.adjust.vo;

/**
* ReqAdjustVO
* @author LEEC.J
* @since 2018.06.14
* @version 1.0
* @see
*
* <pre>
* 정산
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.14     LEEC.J        최초 생성
* </pre>
*/
public class ReqAdjustVO {
	private String adjustType;	// 정산타입(1: 일별정산, 2: 월별정산, 3: 년도별정산)
	private String page = "1";	// 페이지
	private int countPerPage;
	private int limitIndex;
	private String srtDt = "";		// 결제시작일
	private String endDt = "";		// 결제종료일
	private String dt = "";		// 상세일
	private String paymentPage = "1";		// 결제목록페이지
	private String srchLocTyp = "";		// 결제항목
	private String payMthd = "";		// 결제수단
	private String srchStatus = "";		// 상태

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
	public String getAdjustType() {
		return adjustType;
	}
	public void setAdjustType(String adjustType) {
		this.adjustType = adjustType;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getPaymentPage() {
		return paymentPage;
	}
	public void setPaymentPage(String paymentPage) {
		this.paymentPage = paymentPage;
	}
	public String getSrchLocTyp() {
		return srchLocTyp;
	}
	public void setSrchLocTyp(String srchLocTyp) {
		this.srchLocTyp = srchLocTyp;
	}
	public String getPayMthd() {
		return payMthd;
	}
	public void setPayMthd(String payMthd) {
		this.payMthd = payMthd;
	}
	public String getSrchStatus() {
		return srchStatus;
	}
	public void setSrchStatus(String srchStatus) {
		this.srchStatus = srchStatus;
	}
}
