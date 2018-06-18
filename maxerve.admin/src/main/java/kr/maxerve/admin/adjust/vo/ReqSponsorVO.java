package kr.maxerve.admin.adjust.vo;

/**
* ReqSponsorVO
* @author LEEC.J
* @since 2018.06.14
* @version 1.0
* @see
*
* <pre>
* 후원
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.14     LEEC.J        최초 생성
* </pre>
*/
public class ReqSponsorVO {
	private String page = "1";	// 페이지
	private int countPerPage;
	private int limitIndex;
	private String srtDt = "";		// 후원시작일
	private String endDt = "";		// 후원종료일
	private String payMthd = "";	// 결제방식
	private String srchTyp = "";	// 검색방식
	private String srchWord = "";	// 검색어

	private String ym = "";		// 년월
	private String pjtIdx = "";	// 프로젝트 인덱스

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
	public String getPayMthd() {
		return payMthd;
	}
	public void setPayMthd(String payMthd) {
		this.payMthd = payMthd;
	}
	public String getSrchTyp() {
		return srchTyp;
	}
	public void setSrchTyp(String srchTyp) {
		this.srchTyp = srchTyp;
	}
	public String getSrchWord() {
		return srchWord;
	}
	public void setSrchWord(String srchWord) {
		this.srchWord = srchWord;
	}
	public String getYm() {
		return ym;
	}
	public void setYm(String ym) {
		this.ym = ym;
	}
	public String getPjtIdx() {
		return pjtIdx;
	}
	public void setPjtIdx(String pjtIdx) {
		this.pjtIdx = pjtIdx;
	}
}
