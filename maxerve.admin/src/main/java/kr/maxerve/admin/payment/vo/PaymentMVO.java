package kr.maxerve.admin.payment.vo;

import kr.maxerve.dto.PaymentDTO;

/**
* PaymentMVO
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
public class PaymentMVO extends PaymentDTO {
	private String locTitl = "";	// 결제항목
	private String titl = "";	// 결제제목
	private String oztnNm = "";		// 단체명
	private String mbrId = "";		// 아이디
	private String fees = "";		// 수수료

	public String getLocTitl() {
		return locTitl;
	}
	public void setLocTitl(String locTitl) {
		this.locTitl = locTitl;
	}
	public String getTitl() {
		return titl;
	}
	public void setTitl(String titl) {
		this.titl = titl;
	}
	public String getOztnNm() {
		return oztnNm;
	}
	public void setOztnNm(String oztnNm) {
		this.oztnNm = oztnNm;
	}
	public String getMbrId() {
		return mbrId;
	}
	public void setMbrId(String mbrId) {
		this.mbrId = mbrId;
	}
	public String getFees() {
		return fees;
	}
	public void setFees(String fees) {
		this.fees = fees;
	}
}
