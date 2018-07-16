package kr.maxerve.dto;

/**
* SimpleApplyDTO
* @author LEEC.J
* @since 2018.06.04
* @version 1.0
* @see
*
* <pre>
* 단순신청
* TBL_SIPL_APLY
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.04     LEEC.J        최초 생성
* </pre>
*/
public class SimpleApplyDTO {
	private String siplAplyIdx = "";			// INT(11) NOT NULL AUTO_INCREMENT COMMENT '신청 인덱스',
	private String mbrIdx = "";				// INT(11) NOT NULL COMMENT '회원인덱스',
	private String locTypCd = "";			// VARCHAR(50) NOT NULL COMMENT '소재지 공통코드(010)',
	private String locIdx = "";				// INT(11) NOT NULL COMMENT '소재지 인덱스',
	private String aplyNm = "";				// VARCHAR(50) NOT NULL COMMENT '이름',
	private String aplyPhn = "";				// VARCHAR(50) NOT NULL COMMENT '전화',
	private String aplyEmil = "";			// VARCHAR(200) NOT NULL COMMENT '이메일',
	private String aplyAom = "";			// VARCHAR(10) NOT NULL COMMENT '결제금액',
	private String cnclYn = "";				// VARCHAR(1) NOT NULL DEFAULT 'N' COMMENT '취소여부',
	private String creDttm = "";				// DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시',
	private String modDttm = "";				// DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',

	public String getSiplAplyIdx() {
		return siplAplyIdx;
	}
	public void setSiplAplyIdx(String siplAplyIdx) {
		this.siplAplyIdx = siplAplyIdx;
	}
	public String getMbrIdx() {
		return mbrIdx;
	}
	public void setMbrIdx(String mbrIdx) {
		this.mbrIdx = mbrIdx;
	}
	public String getLocTypCd() {
		return locTypCd;
	}
	public void setLocTypCd(String locTypCd) {
		this.locTypCd = locTypCd;
	}
	public String getLocIdx() {
		return locIdx;
	}
	public void setLocIdx(String locIdx) {
		this.locIdx = locIdx;
	}
	public String getAplyNm() {
		return aplyNm;
	}
	public void setAplyNm(String aplyNm) {
		this.aplyNm = aplyNm;
	}
	public String getAplyPhn() {
		return aplyPhn;
	}
	public void setAplyPhn(String aplyPhn) {
		this.aplyPhn = aplyPhn;
	}
	public String getAplyEmil() {
		return aplyEmil;
	}
	public void setAplyEmil(String aplyEmil) {
		this.aplyEmil = aplyEmil;
	}
	public String getCnclYn() {
		return cnclYn;
	}
	public void setCnclYn(String cnclYn) {
		this.cnclYn = cnclYn;
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
	public String getAplyAom() {
		return aplyAom;
	}
	public void setAplyAom(String aplyAom) {
		this.aplyAom = aplyAom;
	}
}
