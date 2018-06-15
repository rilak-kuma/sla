package kr.maxerve.dto;

public class SponsorDTO {
	private String spoIdx = "";			// int(11) NOT NULL AUTOINCREMENT COMMENT '후원 인덱스'
	private String mbrIdx = "";			// int(11) NOT NULL COMMENT '혁신멤버 인덱스'
	private String locTypCd = "";		// VARCHAR(50) NOT NULL COMMENT '소재지 공통코드(010)',
	private String locIdx = "";			//INT(11) NOT NULL COMMENT '소재지 인덱스',
	private String spoNm = "";			// varchar(50) NOT NULL COMMENT '이름'
	private String spoPhn = "";			// varchar(50) NOT NULL COMMENT '전화'
	private String spoEmil = "";			// varchar(200) NOT NULL COMMENT '이메일'
	private String spoAom = "";			// varchar(10) NOT NULL COMMENT '금액'
	private String creDttm = "";			// datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시'

	public String getSpoIdx() {
		return spoIdx;
	}
	public void setSpoIdx(String spoIdx) {
		this.spoIdx = spoIdx;
	}
	public String getMbrIdx() {
		return mbrIdx;
	}
	public void setMbrIdx(String mbrIdx) {
		this.mbrIdx = mbrIdx;
	}
	public String getSpoNm() {
		return spoNm;
	}
	public void setSpoNm(String spoNm) {
		this.spoNm = spoNm;
	}
	public String getSpoPhn() {
		return spoPhn;
	}
	public void setSpoPhn(String spoPhn) {
		this.spoPhn = spoPhn;
	}
	public String getSpoEmil() {
		return spoEmil;
	}
	public void setSpoEmil(String spoEmil) {
		this.spoEmil = spoEmil;
	}
	public String getSpoAom() {
		return spoAom;
	}
	public void setSpoAom(String spoAom) {
		this.spoAom = spoAom;
	}
	public String getCreDttm() {
		return creDttm;
	}
	public void setCreDttm(String creDttm) {
		this.creDttm = creDttm;
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
}
