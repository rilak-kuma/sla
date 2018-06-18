package kr.maxerve.admin.adjust.vo;

import kr.maxerve.dto.AdjustHistoryDTO;

/**
* AdjustHistoryMVO
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
public class AdjustHistoryMVO extends AdjustHistoryDTO {
	private String oztnNm = "";		// 단체명
	private String ceoNm = "";		// 대표자명
	private String count = "";		// 건수
	private String prc = "";		// 결제금액
	private String fees = "";		// 수수료
	private String ajmDt = "";		// 정산일
	private String ajmYn = "";		// 정산여부

	public String getOztnNm() {
		return oztnNm;
	}
	public void setOztnNm(String oztnNm) {
		this.oztnNm = oztnNm;
	}
	public String getCeoNm() {
		return ceoNm;
	}
	public void setCeoNm(String ceoNm) {
		this.ceoNm = ceoNm;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getPrc() {
		return prc;
	}
	public void setPrc(String prc) {
		this.prc = prc;
	}
	public String getFees() {
		return fees;
	}
	public void setFees(String fees) {
		this.fees = fees;
	}
	public String getAjmDt() {
		return ajmDt;
	}
	public void setAjmDt(String ajmDt) {
		this.ajmDt = ajmDt;
	}
	public String getAjmYn() {
		return ajmYn;
	}
	public void setAjmYn(String ajmYn) {
		this.ajmYn = ajmYn;
	}
}
