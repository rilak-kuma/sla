package kr.maxerve.dto;

/**
* FacilitiesEquipmentDTO
* @author LEEC.J
* 2018.06.22
* @version 1.0
* @see
*
* <pre>
* 시설별장비
* TBL_FCT_EQP
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.22     LEEC.J        최초 생성
* </pre>
*/
public class FacilitiesEquipmentDTO {
	private String fctMstIdx;			// int(11) NOT NULL COMMENT '시설 인덱스'
	private String eqpMstIdx;			// int(11) NOT NULL COMMENT '장비 인덱스'

	public String getFctMstIdx() {
		return fctMstIdx;
	}
	public void setFctMstIdx(String fctMstIdx) {
		this.fctMstIdx = fctMstIdx;
	}
	public String getEqpMstIdx() {
		return eqpMstIdx;
	}
	public void setEqpMstIdx(String eqpMstIdx) {
		this.eqpMstIdx = eqpMstIdx;
	}



}
