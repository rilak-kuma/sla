package kr.maxerve.dto;

/**
* WoodparkClassDTO
* @author LEEC.J
* @since 2018.06.04
* @version 1.0
* @see
*
* <pre>
* 우드파크 클래스
* TBL_WDPK_CLS
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.04     LEEC.J        최초 생성
* </pre>
*/
public class WoodparkClassDTO {
	private String wdpkClsIdx = "";		// INT(11) NOT NULL AUTO_INCREMENT COMMENT '클래스 인덱스',
	private String clsNm = "";				// VARCHAR(50) NOT NULL COMMENT '클래스명',
	private String usePrcTypCd = "";		// VARCHAR(50) NOT NULL COMMENT '사용료구분 공통코드(028)',
	private String usePrc = "";				// VARCHAR(10) NOT NULL COMMENT '사용료',
	private String mvinYn = "";				// VARCHAR(1) NOT NULL COMMENT '입주멤버 사용료 여부',
	private String mvinPrc = "";			// VARCHAR(10) NOT NULL COMMENT '입주멤버 사용료',
	private String useYn = "";				// VARCHAR(1) NOT NULL COMMENT '사용여부',
	private String delYn = "";				// VARCHAR(1) NOT NULL DEFAULT 'N' COMMENT '삭제여부',
	private String creDttm = "";			// DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시',
	private String modDttm = "";			// DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',

	public String getWdpkClsIdx() {
		return wdpkClsIdx;
	}
	public void setWdpkClsIdx(String wdpkClsIdx) {
		this.wdpkClsIdx = wdpkClsIdx;
	}
	public String getClsNm() {
		return clsNm;
	}
	public void setClsNm(String clsNm) {
		this.clsNm = clsNm;
	}
	public String getUsePrcTypCd() {
		return usePrcTypCd;
	}
	public void setUsePrcTypCd(String usePrcTypCd) {
		this.usePrcTypCd = usePrcTypCd;
	}
	public String getUsePrc() {
		return usePrc;
	}
	public void setUsePrc(String usePrc) {
		this.usePrc = usePrc;
	}
	public String getMvinYn() {
		return mvinYn;
	}
	public void setMvinYn(String mvinYn) {
		this.mvinYn = mvinYn;
	}
	public String getMvinPrc() {
		return mvinPrc;
	}
	public void setMvinPrc(String mvinPrc) {
		this.mvinPrc = mvinPrc;
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
