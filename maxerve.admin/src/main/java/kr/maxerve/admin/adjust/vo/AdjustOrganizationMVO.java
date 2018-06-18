package kr.maxerve.admin.adjust.vo;

import kr.maxerve.dto.AdjustOrganizationDTO;

/**
* AdjustOrganizationMVO
* @author LEEC.J
* @since 2018.06.14
* @version 1.0
* @see
*
* <pre>
* 정산단체
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.14     LEEC.J        최초 생성
* </pre>
*/
public class AdjustOrganizationMVO extends AdjustOrganizationDTO {
	private String oztnNm = "";		// 단체명
	private String ceoNm = "";		// 대표자명
	private String ceoEmil = "";		// 이메일
	private String crn = "";	// 사업자번호
	private String ceoPhn = "";		// 휴대번호

	public String getCeoNm() {
		return ceoNm;
	}
	public void setCeoNm(String ceoNm) {
		this.ceoNm = ceoNm;
	}
	public String getCeoEmil() {
		return ceoEmil;
	}
	public void setCeoEmil(String ceoEmil) {
		this.ceoEmil = ceoEmil;
	}
	public String getCrn() {
		return crn;
	}
	public void setCrn(String crn) {
		this.crn = crn;
	}
	public String getCeoPhn() {
		return ceoPhn;
	}
	public void setCeoPhn(String ceoPhn) {
		this.ceoPhn = ceoPhn;
	}
	public String getOztnNm() {
		return oztnNm;
	}
	public void setOztnNm(String oztnNm) {
		this.oztnNm = oztnNm;
	}
}
