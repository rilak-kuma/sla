package kr.maxerve.dto;

public class PayFeesDTO {
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
