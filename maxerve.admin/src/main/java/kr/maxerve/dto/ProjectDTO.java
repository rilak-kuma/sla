package kr.maxerve.dto;

public class ProjectDTO {
	private String pjtIdx	="";			// int(11) NOT NULL AUTOINCREMENT COMMENT '프로젝트 인덱스'
	private String mbrIdx	="";			// int(11) NOT NULL COMMENT '혁신멤버 인덱스'
	private String mngrMbrIdx = "";			// INT(11) NOT NULL DEFAULT '0' COMMENT '수정자',
	private String ctgrIdx	="";			// INT(11) NOT NULL COMMENT '카테고리 인덱스(009002)',
	private String pjtTitl	="";			// varchar(50) NOT NULL COMMENT '프로젝트 제목'
	private String tmnPath	="";			// varchar(200) NOT NULL COMMENT '섬네일경로'
	private String pjtInfo	="";			// varchar(1000) NOT NULL COMMENT '정보'
	private String body		="";			// mediumtext NOT NULL COMMENT '본문'
	private String spoAom	="";			// VARCHAR(50) NOT NULL COMMENT '후원금액',
	private String srtDttm	="";			// DATETIME NOT NULL COMMENT '신청시작일시',
	private String endDttm	="";			// DATETIME NOT NULL COMMENT '신청종료일시',
	private String locTypCd = "";		// varchar(50) Not Null Comment '위치정보종류 공통코드(016)'
	private String locLat = "";			// varchar(15) Not Null Comment '위도'
	private String locLng = "";			// varchar(15) Not Null Comment '경도'
	private String locImgPath = "";		// varchar(200) Not Null Comment '위치이미지'
	private String locImgInfo = "";		// varchar(100) Not Null Comment '위치이미지 정보'
	private String hitCnt = "";			// INT(11) NOT NULL DEFAULT '0' COMMENT '조회수',
	private String useYn = "";			// VARCHAR(1) NOT NULL DEFAULT 'Y' COMMENT '사용여부',
	private String delYn	="";			// varchar(1) NOT NULL DEFAULT 'N' COMMENT '삭제여부'
	private String creDttm	="";			// datetime NOT NULL DEFAULT CURRENTTIMESTAMP COMMENT '생성일시'
	private String modDttm	="";			// datetime NOT NULL DEFAULT CURRENTTIMESTAMP ON UPDATE CURRENTTIMESTAMP COMMENT '수정일시'

	public String getPjtIdx() {
		return pjtIdx;
	}
	public void setPjtIdx(String pjtIdx) {
		this.pjtIdx = pjtIdx;
	}
	public String getMbrIdx() {
		return mbrIdx;
	}
	public void setMbrIdx(String mbrIdx) {
		this.mbrIdx = mbrIdx;
	}
	public String getPjtTitl() {
		return pjtTitl;
	}
	public void setPjtTitl(String pjtTitl) {
		this.pjtTitl = pjtTitl;
	}
	public String getTmnPath() {
		return tmnPath;
	}
	public void setTmnPath(String tmnPath) {
		this.tmnPath = tmnPath;
	}
	public String getPjtInfo() {
		return pjtInfo;
	}
	public void setPjtInfo(String pjtInfo) {
		this.pjtInfo = pjtInfo;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
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
	public String getCtgrIdx() {
		return ctgrIdx;
	}
	public void setCtgrIdx(String ctgrIdx) {
		this.ctgrIdx = ctgrIdx;
	}
	public String getSrtDttm() {
		return srtDttm;
	}
	public void setSrtDttm(String srtDttm) {
		this.srtDttm = srtDttm;
	}
	public String getEndDttm() {
		return endDttm;
	}
	public void setEndDttm(String endDttm) {
		this.endDttm = endDttm;
	}
	public String getLocTypCd() {
		return locTypCd;
	}
	public void setLocTypCd(String locTypCd) {
		this.locTypCd = locTypCd;
	}
	public String getLocLat() {
		return locLat;
	}
	public void setLocLat(String locLat) {
		this.locLat = locLat;
	}
	public String getLocLng() {
		return locLng;
	}
	public void setLocLng(String locLng) {
		this.locLng = locLng;
	}
	public String getLocImgPath() {
		return locImgPath;
	}
	public void setLocImgPath(String locImgPath) {
		this.locImgPath = locImgPath;
	}
	public String getLocImgInfo() {
		return locImgInfo;
	}
	public void setLocImgInfo(String locImgInfo) {
		this.locImgInfo = locImgInfo;
	}
	public String getSpoAom() {
		return spoAom;
	}
	public void setSpoAom(String spoAom) {
		this.spoAom = spoAom;
	}
	public String getMngrMbrIdx() {
		return mngrMbrIdx;
	}
	public void setMngrMbrIdx(String mngrMbrIdx) {
		this.mngrMbrIdx = mngrMbrIdx;
	}
	public String getHitCnt() {
		return hitCnt;
	}
	public void setHitCnt(String hitCnt) {
		this.hitCnt = hitCnt;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
}
