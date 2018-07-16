package kr.maxerve.dto;

/**
* PaymentFeesDTO
* @author LEEC.J
* 2018.06.22
* @version 1.0
* @see
*
* <pre>
* 수수료
* TBL_PAY_FEES
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.22     LEEC.J        최초 생성
* </pre>
*/
public class PaymentFeesDTO {
	private String payMthd = "";		// VARCHAR(10) NOT NULL COMMENT '결제수단(KAKAOPAY, CARD, BANK, VBANK, CELLPHONE)',
	private String fees = "";			// VARCHAR(10) NOT NULL COMMENT '수수료 퍼센트',

	public String getPayMthd() {
		return payMthd;
	}
	public void setPayMthd(String payMthd) {
		this.payMthd = payMthd;
	}
	public String getFees() {
		return fees;
	}
	public void setFees(String fees) {
		this.fees = fees;
	}
}
