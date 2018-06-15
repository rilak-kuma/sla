package kr.maxerve.dto;

/**
* AdjustOrganizationDTO.java
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
public class AdjustOrganizationDTO {
	private String locTypCd = "";			// VARCHAR(50) NOT NULL COMMENT '정산종류 공통코드(002)',
	private String mvinAplyIdx = "";		// INT(11) NOT NULL COMMENT '입주신청 인덱스',
	private String useYn = "";				// VARCHAR(1) NOT NULL COMMENT '사용여부',
	private String srtDt = "";				// VARCHAR(10) NOT NULL COMMENT '관리기간 시작일',
	private String endDt = "";				// VARCHAR(10) NOT NULL COMMENT '관리기간 종료일',
	private String creDttm = "";			// DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시',
	private String modDttm = "";			// DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',

	public String getLocTypCd() {
		return locTypCd;
	}
	public void setLocTypCd(String locTypCd) {
		this.locTypCd = locTypCd;
	}
	public String getMvinAplyIdx() {
		return mvinAplyIdx;
	}
	public void setMvinAplyIdx(String mvinAplyIdx) {
		this.mvinAplyIdx = mvinAplyIdx;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getSrtDt() {
		return srtDt;
	}
	public void setSrtDt(String srtDt) {
		this.srtDt = srtDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
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
