package kr.maxerve.admin.adjust.vo;

/**
* AdjustSummaryMVO
* @author LEEC.J
* @since 2018.06.14
* @version 1.0
* @see
*
* <pre>
* 정산목록
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.14     LEEC.J        최초 생성
* </pre>
*/
public class AdjustSummaryMVO {
	private String dt = "";		// 일자
	private String total = "";		// 전체건수
	private String appr = "";		// 완료건
	private String cancel = "";		// 취소건
	private String error = "";		// 오류건
	private String apprPrc = "";	// 성공금액
	private String cancelPrc = "";	// 취소금액
	private String errorPrc = "";	// 오류금액
	private String fees = "";		// 수수료

	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getAppr() {
		return appr;
	}
	public void setAppr(String appr) {
		this.appr = appr;
	}
	public String getCancel() {
		return cancel;
	}
	public void setCancel(String cancel) {
		this.cancel = cancel;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getApprPrc() {
		return apprPrc;
	}
	public void setApprPrc(String apprPrc) {
		this.apprPrc = apprPrc;
	}
	public String getFees() {
		return fees;
	}
	public void setFees(String fees) {
		this.fees = fees;
	}
	public String getCancelPrc() {
		return cancelPrc;
	}
	public void setCancelPrc(String cancelPrc) {
		this.cancelPrc = cancelPrc;
	}
	public String getErrorPrc() {
		return errorPrc;
	}
	public void setErrorPrc(String errorPrc) {
		this.errorPrc = errorPrc;
	}
}
