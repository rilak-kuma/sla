package kr.maxerve.admin.adjust.vo;

import kr.maxerve.dto.SponsorDTO;

/**
* SponsorMVO
* @author LEEC.J
* 2018.06.22
* @version 1.0
* @see
*
* <pre>
* 후원
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.22     LEEC.J        최초 생성
* </pre>
*/
public class SponsorMVO extends SponsorDTO {
	private String pjtTitl = "";		// 프로젝트
	private String payMthd = "";		// 결제방식
	private String payIdx = "";			// 결제인덱스

	private String oztnNm = "";		// 단체명
	private String srtDttm = "";	// 후원시작일시
	private String endDttm = "";	// 후원종료일시
	private String count = "";		// 전체건수
	private String fees = "";		// 수수료

	private String cardAjmCount = "";	// 카드정산수
	private String cardAjmPrc = "";		// 카드정산금
	private String bankAjmCount = "";	// 계좌이체정산수
	private String bankAjmPrc = "";		// 계좌이체정산금
	private String kakaopayAjmCount = "";	// 카카오페이정산수
	private String kakaopayAjmPrc = "";		// 카카오페이정산금
	private String cellphoneAjmCount = "";	// 휴대폰정산수
	private String cellphoneAjmPrc = "";	// 휴대폰정산금

	public String getPjtTitl() {
		return pjtTitl;
	}

	public void setPjtTitl(String pjtTitl) {
		this.pjtTitl = pjtTitl;
	}

	public String getPayMthd() {
		return payMthd;
	}

	public void setPayMthd(String payMthd) {
		this.payMthd = payMthd;
	}

	public String getPayIdx() {
		return payIdx;
	}

	public void setPayIdx(String payIdx) {
		this.payIdx = payIdx;
	}

	public String getOztnNm() {
		return oztnNm;
	}

	public void setOztnNm(String oztnNm) {
		this.oztnNm = oztnNm;
	}

	public String getSrtDttm() {
		return srtDttm;
	}

	public void setSrtDttm(String srtDttm) {
		this.srtDttm = srtDttm;
	}

	public String getEndDttm() {
		return endDttm;
	}

	public void setEndDttm(String endDttm) {
		this.endDttm = endDttm;
	}

	public String getFees() {
		return fees;
	}

	public void setFees(String fees) {
		this.fees = fees;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getCardAjmCount() {
		return cardAjmCount;
	}

	public void setCardAjmCount(String cardAjmCount) {
		this.cardAjmCount = cardAjmCount;
	}

	public String getCardAjmPrc() {
		return cardAjmPrc;
	}

	public void setCardAjmPrc(String cardAjmPrc) {
		this.cardAjmPrc = cardAjmPrc;
	}

	public String getBankAjmCount() {
		return bankAjmCount;
	}

	public void setBankAjmCount(String bankAjmCount) {
		this.bankAjmCount = bankAjmCount;
	}

	public String getBankAjmPrc() {
		return bankAjmPrc;
	}

	public void setBankAjmPrc(String bankAjmPrc) {
		this.bankAjmPrc = bankAjmPrc;
	}

	public String getKakaopayAjmCount() {
		return kakaopayAjmCount;
	}

	public void setKakaopayAjmCount(String kakaopayAjmCount) {
		this.kakaopayAjmCount = kakaopayAjmCount;
	}

	public String getKakaopayAjmPrc() {
		return kakaopayAjmPrc;
	}

	public void setKakaopayAjmPrc(String kakaopayAjmPrc) {
		this.kakaopayAjmPrc = kakaopayAjmPrc;
	}

	public String getCellphoneAjmCount() {
		return cellphoneAjmCount;
	}

	public void setCellphoneAjmCount(String cellphoneAjmCount) {
		this.cellphoneAjmCount = cellphoneAjmCount;
	}

	public String getCellphoneAjmPrc() {
		return cellphoneAjmPrc;
	}

	public void setCellphoneAjmPrc(String cellphoneAjmPrc) {
		this.cellphoneAjmPrc = cellphoneAjmPrc;
	}
}
