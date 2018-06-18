package kr.maxerve.admin.adjust.vo;

import kr.maxerve.dto.PaymentDTO;

/**
* ReqPaymentVO
* @author LEEC.J
* @since 2018.06.14
* @version 1.0
* @see
*
* <pre>
* 결제
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.14     LEEC.J        최초 생성
* </pre>
*/
public class ReqPaymentVO extends PaymentDTO {
	private String srchLocTyp = "";		// 결제항목
	private String srchStatus = "";		// 상태
	private String page = "1";	// 페이지
	private int countPerPage;
	private int limitIndex;
	private String srtDt = "";		// 결제시작일
	private String endDt = "";		// 결제종료일
	private String oztnNm = "";		// 업체명
	private String oztnLocTypCd = "";	// 정산업체구분코드
	private String oztnLocIdx = "";		// 정산업체인덱스

	public String getSrchLocTyp() {
		return srchLocTyp;
	}
	public void setSrchLocTyp(String srchLocTyp) {
		this.srchLocTyp = srchLocTyp;
	}
	public String getSrchStatus() {
		return srchStatus;
	}
	public void setSrchStatus(String srchStatus) {
		this.srchStatus = srchStatus;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
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
	public String getOztnNm() {
		return oztnNm;
	}
	public void setOztnNm(String oztnNm) {
		this.oztnNm = oztnNm;
	}
	public String getOztnLocTypCd() {
		return oztnLocTypCd;
	}
	public void setOztnLocTypCd(String oztnLocTypCd) {
		this.oztnLocTypCd = oztnLocTypCd;
	}
	public String getOztnLocIdx() {
		return oztnLocIdx;
	}
	public void setOztnLocIdx(String oztnLocIdx) {
		this.oztnLocIdx = oztnLocIdx;
	}
}
