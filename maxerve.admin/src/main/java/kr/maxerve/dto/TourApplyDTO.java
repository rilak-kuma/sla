package kr.maxerve.dto;

public class TourApplyDTO {
	private String tourAplyIdx = "";	// int(11) NOT NULL AUTOINCREMENT COMMENT '투어 예약 인덱스'
	private String mbrIdx = "";			// int(11) NOT NULL COMMENT '회원 인덱스'
	private String tourDttm = "";		// datetime NOT NULL COMMENT '투어 예약 일시'
	private String oztnNm = "";			// varchar(50) NOT NULL COMMENT '단체명'
	private String oztnTypCd = "";		// varchar(50) NOT NULL COMMENT '단체형태 공통코드(033)'
	private String psct = "";			// int(11) NOT NULL COMMENT '인원'
	private String appyNm = "";			// varchar(50) NOT NULL COMMENT '신청자명'
	private String appyOztnNm = "";		// VARCHAR(50) NOT NULL COMMENT '신청자 단체명',
	private String appyPhnNmbr = "";	// varchar(50) NOT NULL COMMENT '신청자 연락처'
	private String appyEmil = "";		// varchar(50) NOT NULL COMMENT '신청자 이메일'
	private String ldrNm = "";			// varchar(50) NOT NULL COMMENT '인솔자명'
	private String ldrOztnNm = "";		// VARCHAR(50) NOT NULL COMMENT '인솔자 단체명',
	private String ldrPhnNmbr = "";		// varchar(50) NOT NULL COMMENT '인솔자 연락처'
	private String ldrEmil = "";		// varchar(50) NOT NULL COMMENT '인솔자 이메일'
	private String appyOztnItdc = "";	// VARCHAR(1000) NOT NULL COMMENT '신청기관 소개',
	private String tourPrpsCd = "";		// varchar(1000) NOT NULL COMMENT '방문 목적'
	private String tourPrpsEtc = "";	// varchar(50) NOT NULL COMMENT '방문 목적 기타'
	private String etc = "";			// varchar(1000) NOT NULL COMMENT '기타'
	private String expYn = "";			// VARCHAR(1) NOT NULL COMMENT '방문경험여부',
	private String cmmt = "";			// VARCHAR(1000) NOT NULL COMMENT '관리자 코멘트',
	private String tourDsdDttm = "";	// DATETIME NOT NULL COMMENT '투어확정일시',
	private String tourPgr = "";		// VARCHAR(50) NOT NULL COMMENT '예약진행상태 공통코드(020)'
	private String delYn = "";			// varchar(1) NOT NULL DEFAULT 'N' COMMENT '삭제여부'
	private String creDttm = "";		// datetime NOT NULL DEFAULT CURRENTTIMESTAMP COMMENT '생성일시'

	public String getTourAplyIdx() {
		return tourAplyIdx;
	}
	public void setTourAplyIdx(String tourAplyIdx) {
		this.tourAplyIdx = tourAplyIdx;
	}
	public String getMbrIdx() {
		return mbrIdx;
	}
	public void setMbrIdx(String mbrIdx) {
		this.mbrIdx = mbrIdx;
	}
	public String getTourDttm() {
		return tourDttm;
	}
	public void setTourDttm(String tourDttm) {
		this.tourDttm = tourDttm;
	}
	public String getOztnNm() {
		return oztnNm;
	}
	public void setOztnNm(String oztnNm) {
		this.oztnNm = oztnNm;
	}
	public String getOztnTypCd() {
		return oztnTypCd;
	}
	public void setOztnTypCd(String oztnTypCd) {
		this.oztnTypCd = oztnTypCd;
	}
	public String getPsct() {
		return psct;
	}
	public void setPsct(String psct) {
		this.psct = psct;
	}
	public String getAppyNm() {
		return appyNm;
	}
	public void setAppyNm(String appyNm) {
		this.appyNm = appyNm;
	}
	public String getAppyPhnNmbr() {
		return appyPhnNmbr;
	}
	public void setAppyPhnNmbr(String appyPhnNmbr) {
		this.appyPhnNmbr = appyPhnNmbr;
	}
	public String getAppyEmil() {
		return appyEmil;
	}
	public void setAppyEmil(String appyEmil) {
		this.appyEmil = appyEmil;
	}
	public String getLdrNm() {
		return ldrNm;
	}
	public void setLdrNm(String ldrNm) {
		this.ldrNm = ldrNm;
	}
	public String getLdrPhnNmbr() {
		return ldrPhnNmbr;
	}
	public void setLdrPhnNmbr(String ldrPhnNmbr) {
		this.ldrPhnNmbr = ldrPhnNmbr;
	}
	public String getLdrEmil() {
		return ldrEmil;
	}
	public void setLdrEmil(String ldrEmil) {
		this.ldrEmil = ldrEmil;
	}
	public String getTourPrpsCd() {
		return tourPrpsCd;
	}
	public void setTourPrpsCd(String tourPrpsCd) {
		this.tourPrpsCd = tourPrpsCd;
	}
	public String getTourPrpsEtc() {
		return tourPrpsEtc;
	}
	public void setTourPrpsEtc(String tourPrpsEtc) {
		this.tourPrpsEtc = tourPrpsEtc;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	public String getTourPgr() {
		return tourPgr;
	}
	public void setTourPgr(String tourPgr) {
		this.tourPgr = tourPgr;
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
	public String getCmmt() {
		return cmmt;
	}
	public void setCmmt(String cmmt) {
		this.cmmt = cmmt;
	}
	public String getTourDsdDttm() {
		return tourDsdDttm;
	}
	public void setTourDsdDttm(String tourDsdDttm) {
		this.tourDsdDttm = tourDsdDttm;
	}
	public String getAppyOztnNm() {
		return appyOztnNm;
	}
	public void setAppyOztnNm(String appyOztnNm) {
		this.appyOztnNm = appyOztnNm;
	}
	public String getLdrOztnNm() {
		return ldrOztnNm;
	}
	public void setLdrOztnNm(String ldrOztnNm) {
		this.ldrOztnNm = ldrOztnNm;
	}
	public String getAppyOztnItdc() {
		return appyOztnItdc;
	}
	public void setAppyOztnItdc(String appyOztnItdc) {
		this.appyOztnItdc = appyOztnItdc;
	}
	public String getExpYn() {
		return expYn;
	}
	public void setExpYn(String expYn) {
		this.expYn = expYn;
	}
}
