package kr.maxerve.dto;

/**
* FacilitiesMasterDTO
* @author LEEC.J
* 2018.06.22
* @version 1.0
* @see
*
* <pre>
* 시설마스터
* TBL_FCT_MST
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.22     LEEC.J        최초 생성
* </pre>
*/
public class FacilitiesMasterDTO {
	private String fctMstIdx = "";			// INT(11) NOT NULL AUTO_INCREMENT COMMENT '시설 인덱스'
	private String rsvtPlcCd = "";			// VARCHAR(50) NOT NULL COMMENT '예약장소 공통코드(002)'
	private String fctNm = "";				// VARCHAR(50) NOT NULL COMMENT '시설명'
	private String ord = "";					// INT(11) NOT NULL COMMENT '순번'
	private String minTm = "";				// TINYINT(4) NOT NULL DEFAULT '0' COMMENT '최소예약시간(분)'
	private String rsvtUnit = "";			// TINYINT(4) NOT NULL DEFAULT '0' COMMENT '예약단위(분)'
	private String dftPrc = "";				// INT(11) NOT NULL COMMENT '기본사용료'
	private String addPrc = "";				// INT(11) NOT NULL COMMENT '추가사용료'
	private String useSrtDt = "";			// VARCHAR(10) NOT NULL COMMENT '적용시작일'
	private String useEndDt = "";			// VARCHAR(10) NOT NULL COMMENT '적용종료일'
	private String oprTmInfo = "";			// VARCHAR(50) NOT NULL COMMENT '운영시간정보'
	private String rsvtMbrInfo = "";			// VARCHAR(50) NOT NULL COMMENT '예약가능회원정보'
	private String psblMinPsct = "";			// TINYINT(4) NOT NULL DEFAULT '0' COMMENT '사용가능최소인원'
	private String psblMaxPsct = "";			// TINYINT(4) NOT NULL DEFAULT '0' COMMENT '사용가능최대인원'
	private String fctFoa = "";				// TINYINT(4) NOT NULL DEFAULT '0' COMMENT '면적'
	private String tnm = "";					// VARCHAR(50) NOT NULL COMMENT '기자재'
	private String maxOtpSize = "";			// VARCHAR(50) NOT NULL COMMENT '최대출력사이즈'
	private String prcInfo = "";				// VARCHAR(50) NOT NULL COMMENT '사용료정보'
	private String mngrNm = "";				// VARCHAR(50) NOT NULL COMMENT '담당자명',
	private String rmk = "";				// VARCHAR(1000) NOT NULL COMMENT '특이사항',
	private String rtaYn = "";				// VARCHAR(1) NOT NULL DEFAULT 'N' COMMENT '대관선태가능여부',
	private String useYn = "";				// VARCHAR(1) NOT NULL DEFAULT 'N' COMMENT '사용여부'
	private String delYn = "";				// VARCHAR(1) NOT NULL DEFAULT 'N' COMMENT '삭제여부'
	private String creDttm = "";				// DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시'
	private String modDttm = "";				// DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시'

	public String getFctMstIdx() {
		return fctMstIdx;
	}
	public void setFctMstIdx(String fctMstIdx) {
		this.fctMstIdx = fctMstIdx;
	}
	public String getRsvtPlcCd() {
		return rsvtPlcCd;
	}
	public void setRsvtPlcCd(String rsvtPlcCd) {
		this.rsvtPlcCd = rsvtPlcCd;
	}
	public String getFctNm() {
		return fctNm;
	}
	public void setFctNm(String fctNm) {
		this.fctNm = fctNm;
	}
	public String getOrd() {
		return ord;
	}
	public void setOrd(String ord) {
		this.ord = ord;
	}
	public String getMinTm() {
		return minTm;
	}
	public void setMinTm(String minTm) {
		this.minTm = minTm;
	}
	public String getRsvtUnit() {
		return rsvtUnit;
	}
	public void setRsvtUnit(String rsvtUnit) {
		this.rsvtUnit = rsvtUnit;
	}
	public String getDftPrc() {
		return dftPrc;
	}
	public void setDftPrc(String dftPrc) {
		this.dftPrc = dftPrc;
	}
	public String getAddPrc() {
		return addPrc;
	}
	public void setAddPrc(String addPrc) {
		this.addPrc = addPrc;
	}
	public String getUseSrtDt() {
		return useSrtDt;
	}
	public void setUseSrtDt(String useSrtDt) {
		this.useSrtDt = useSrtDt;
	}
	public String getUseEndDt() {
		return useEndDt;
	}
	public void setUseEndDt(String useEndDt) {
		this.useEndDt = useEndDt;
	}
	public String getOprTmInfo() {
		return oprTmInfo;
	}
	public void setOprTmInfo(String oprTmInfo) {
		this.oprTmInfo = oprTmInfo;
	}
	public String getRsvtMbrInfo() {
		return rsvtMbrInfo;
	}
	public void setRsvtMbrInfo(String rsvtMbrInfo) {
		this.rsvtMbrInfo = rsvtMbrInfo;
	}
	public String getPsblMinPsct() {
		return psblMinPsct;
	}
	public void setPsblMinPsct(String psblMinPsct) {
		this.psblMinPsct = psblMinPsct;
	}
	public String getPsblMaxPsct() {
		return psblMaxPsct;
	}
	public void setPsblMaxPsct(String psblMaxPsct) {
		this.psblMaxPsct = psblMaxPsct;
	}
	public String getFctFoa() {
		return fctFoa;
	}
	public void setFctFoa(String fctFoa) {
		this.fctFoa = fctFoa;
	}
	public String getTnm() {
		return tnm;
	}
	public void setTnm(String tnm) {
		this.tnm = tnm;
	}
	public String getMaxOtpSize() {
		return maxOtpSize;
	}
	public void setMaxOtpSize(String maxOtpSize) {
		this.maxOtpSize = maxOtpSize;
	}
	public String getPrcInfo() {
		return prcInfo;
	}
	public void setPrcInfo(String prcInfo) {
		this.prcInfo = prcInfo;
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
	public String getRtaYn() {
		return rtaYn;
	}
	public void setRtaYn(String rtaYn) {
		this.rtaYn = rtaYn;
	}
	public String getMngrNm() {
		return mngrNm;
	}
	public void setMngrNm(String mngrNm) {
		this.mngrNm = mngrNm;
	}
	public String getRmk() {
		return rmk;
	}
	public void setRmk(String rmk) {
		this.rmk = rmk;
	}
}
