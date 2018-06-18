package kr.maxerve.dto;

/**
* PaymentDTO
* @author LEEC.J
* @since 2018.06.10
* @version 1.0
* @see
*
* <pre>
* 월회원
* TBL_FCT_MBR
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.10     LEEC.J        최초 생성
* </pre>
*/
public class FacilitiesMemberDTO {
	private String fctMbrIdx = "";	// int(11) NOT NULL AUTO_INCREMENT COMMENT '월회원 인덱스'
	private String mbrIdx = "";		// int(11) Not Null Comment '회원인덱스'
	private String locTypCd = "";	// varchar(50) Not Null Comment '월회원 장소공통코드(002)'
	private String actMth = "";		// varchar(7) Not Null Comment '가입년월'
	private String prc = "";			// varchar(10) NOT NULL COMMENT '회원비'
	private String useYn = "";		//  varchar(1) NOT NULL DEFAULT 'N' COMMENT '유효여부',
	private String cnlYn = "";		//  varchar(1) NOT NULL DEFAULT 'N' COMMENT '취소여부',
	private String creDttm = "";	// datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시'

	public String getFctMbrIdx() {
		return fctMbrIdx;
	}
	public void setFctMbrIdx(String fctMbrIdx) {
		this.fctMbrIdx = fctMbrIdx;
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
	public String getActMth() {
		return actMth;
	}
	public void setActMth(String actMth) {
		this.actMth = actMth;
	}
	public String getPrc() {
		return prc;
	}
	public void setPrc(String prc) {
		this.prc = prc;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getCnlYn() {
		return cnlYn;
	}
	public void setCnlYn(String cnlYn) {
		this.cnlYn = cnlYn;
	}
	public String getCreDttm() {
		return creDttm;
	}
	public void setCreDttm(String creDttm) {
		this.creDttm = creDttm;
	}
}
