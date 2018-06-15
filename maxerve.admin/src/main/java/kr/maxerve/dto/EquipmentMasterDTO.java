package kr.maxerve.dto;

/**
* EquipmentMasterDTO.java
* @author LEEC.J
* @since 2018.06.15
* @version 1.0
* @see
*
* <pre>
* 파일
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.15      LEEC.J         최초 생성
* </pre>
*/
public class EquipmentMasterDTO {
	private String eqpMstIdx;		// INT(11) NOT NULL AUTO_INCREMENT COMMENT '장비 인덱스'
	private String eqpNm;			// VARCHAR(50) NOT NULL COMMENT '장비명'
	private String eqpHourPrc;		// INT(11) NOT NULL COMMENT '장비시간당가격'
	private String eqpHalfHourPrc;	// INT(11) NOT NULL COMMENT '장비30분당가격'
	private String eqpTcnt;		 	// INT(11) NOT NULL COMMENT '수량'
	private String useYn;			// VARCHAR(1) NOT NULL DEFAULT 'N' COMMENT '사용여부'
	private String delYn;			// VARCHAR(1) NOT NULL DEFAULT 'N' COMMENT '삭제여부'
	private String creDttm;		 	// DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시'
	private String modDttm;		 	// DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시'
	public String getEqpMstIdx() {
		return eqpMstIdx;
	}
	public void setEqpMstIdx(String eqpMstIdx) {
		this.eqpMstIdx = eqpMstIdx;
	}
	public String getEqpNm() {
		return eqpNm;
	}
	public void setEqpNm(String eqpNm) {
		this.eqpNm = eqpNm;
	}
	public String getEqpHourPrc() {
		return eqpHourPrc;
	}
	public void setEqpHourPrc(String eqpHourPrc) {
		this.eqpHourPrc = eqpHourPrc;
	}
	public String getEqpHalfHourPrc() {
		return eqpHalfHourPrc;
	}
	public void setEqpHalfHourPrc(String eqpHalfHourPrc) {
		this.eqpHalfHourPrc = eqpHalfHourPrc;
	}
	public String getEqpTcnt() {
		return eqpTcnt;
	}
	public void setEqpTcnt(String eqpTcnt) {
		this.eqpTcnt = eqpTcnt;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public String getCreDttm() {
		return creDttm;
	}
	public void setCreDttm(String creDttm) {
		this.creDttm = creDttm;
	}
	public String getModDttm() {
		return modDttm;
	}
	public void setModDttm(String modDttm) {
		this.modDttm = modDttm;
	}
}
