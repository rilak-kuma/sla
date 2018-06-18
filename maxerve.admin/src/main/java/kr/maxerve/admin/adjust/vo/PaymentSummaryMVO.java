package kr.maxerve.admin.adjust.vo;

/**
* PaymentSummaryMVO
* @author LEEC.J
* @since 2018.06.14
* @version 1.0
* @see
*
* <pre>
* 정산결제요약
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.14     LEEC.J        최초 생성
* </pre>
*/
public class PaymentSummaryMVO {
	private String apprCardCount = "";		// 신용카드완료건수
	private String apprCardPrc = "";		// 신용카드완료금액
	private String apprBankCount = "";		// 계좌인체완료건수
	private String apprBankPrc = "";		// 계좌이체완료금액
	private String apprKakaopayCount = "";		// 카카오페이완료건수
	private String apprKakaopayPrc = "";		// 카카오페이완료금액

	private String cancelCardCount = "";	// 신용카드취소건수
	private String cancelCardPrc = "";		// 신용카드취소금액
	private String cancelBankCount = "";	// 계좌이체취소건수
	private String cancelBankPrc = "";		// 계좌이체취소금액
	private String cancelKakaopayCount = "";	// 카카오페이취소건수
	private String cancelKakaopayPrc = "";		// 카카오페이취소금액

	private String errorCardCount = "";		// 신용카드오류건수
	private String errorCardPrc = "";		// 신용카드오류금액
	private String errorBankCount = "";		// 계좌이체오류건수
	private String errorBankPrc = "";		// 계좌이체오류금액
	private String errorKakaopayCount = "";		// 카카오페이오류건수
	private String errorKakaopayPrc = "";		// 카카오페이오류금액

	public String getApprCardCount() {
		return apprCardCount;
	}
	public void setApprCardCount(String apprCardCount) {
		this.apprCardCount = apprCardCount;
	}
	public String getApprCardPrc() {
		return apprCardPrc;
	}
	public void setApprCardPrc(String apprCardPrc) {
		this.apprCardPrc = apprCardPrc;
	}
	public String getApprBankCount() {
		return apprBankCount;
	}
	public void setApprBankCount(String apprBankCount) {
		this.apprBankCount = apprBankCount;
	}
	public String getApprBankPrc() {
		return apprBankPrc;
	}
	public void setApprBankPrc(String apprBankPrc) {
		this.apprBankPrc = apprBankPrc;
	}
	public String getApprKakaopayCount() {
		return apprKakaopayCount;
	}
	public void setApprKakaopayCount(String apprKakaopayCount) {
		this.apprKakaopayCount = apprKakaopayCount;
	}
	public String getApprKakaopayPrc() {
		return apprKakaopayPrc;
	}
	public void setApprKakaopayPrc(String apprKakaopayPrc) {
		this.apprKakaopayPrc = apprKakaopayPrc;
	}
	public String getCancelCardCount() {
		return cancelCardCount;
	}
	public void setCancelCardCount(String cancelCardCount) {
		this.cancelCardCount = cancelCardCount;
	}
	public String getCancelCardPrc() {
		return cancelCardPrc;
	}
	public void setCancelCardPrc(String cancelCardPrc) {
		this.cancelCardPrc = cancelCardPrc;
	}
	public String getCancelBankCount() {
		return cancelBankCount;
	}
	public void setCancelBankCount(String cancelBankCount) {
		this.cancelBankCount = cancelBankCount;
	}
	public String getCancelBankPrc() {
		return cancelBankPrc;
	}
	public void setCancelBankPrc(String cancelBankPrc) {
		this.cancelBankPrc = cancelBankPrc;
	}
	public String getCancelKakaopayCount() {
		return cancelKakaopayCount;
	}
	public void setCancelKakaopayCount(String cancelKakaopayCount) {
		this.cancelKakaopayCount = cancelKakaopayCount;
	}
	public String getCancelKakaopayPrc() {
		return cancelKakaopayPrc;
	}
	public void setCancelKakaopayPrc(String cancelKakaopayPrc) {
		this.cancelKakaopayPrc = cancelKakaopayPrc;
	}
	public String getErrorCardCount() {
		return errorCardCount;
	}
	public void setErrorCardCount(String errorCardCount) {
		this.errorCardCount = errorCardCount;
	}
	public String getErrorCardPrc() {
		return errorCardPrc;
	}
	public void setErrorCardPrc(String errorCardPrc) {
		this.errorCardPrc = errorCardPrc;
	}
	public String getErrorBankCount() {
		return errorBankCount;
	}
	public void setErrorBankCount(String errorBankCount) {
		this.errorBankCount = errorBankCount;
	}
	public String getErrorBankPrc() {
		return errorBankPrc;
	}
	public void setErrorBankPrc(String errorBankPrc) {
		this.errorBankPrc = errorBankPrc;
	}
	public String getErrorKakaopayCount() {
		return errorKakaopayCount;
	}
	public void setErrorKakaopayCount(String errorKakaopayCount) {
		this.errorKakaopayCount = errorKakaopayCount;
	}
	public String getErrorKakaopayPrc() {
		return errorKakaopayPrc;
	}
	public void setErrorKakaopayPrc(String errorKakaopayPrc) {
		this.errorKakaopayPrc = errorKakaopayPrc;
	}
}
