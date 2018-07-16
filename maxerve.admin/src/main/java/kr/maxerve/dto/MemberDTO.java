package kr.maxerve.dto;

/**
* MemberDTO
* @author LEEC.J
* 2018.06.22
* @version 1.0
* @see
*
* <pre>
* 멤버
* TBL_MBR
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2010.10.17     LEEC.J        최초 생성
* </pre>
*/
public class MemberDTO {
	private String mbrIdx = "";			// int(11) Not Null AutoIncrement Comment '회원 인덱스'
	private String mbrId = "";			// varchar(50) Not Null Comment '회원 아이디'
	private String mbrPwd = "";			// varchar(100) Not Null Comment '비밀번호'
	private String mbrTypCd = "";		// varchar(50) Not Null Comment '혁신멤버 구분 공통코드(015)'
	private String sclTypCd = "";		// varchar(50) Not Null Comment '소셜 종류 공통코드(006)'
	private String oztnNm = "";			// varchar(50) Not Null Comment '단체명'
	private String ceoNm = "";			// varchar(50) Not Null Comment '대표자명'
	private String ofcPhn = "";			// varchar(50) Not Null Comment '대표전화'
	private String ceoPhn = "";			// varchar(50) Not Null Comment '대표휴대전화'
	private String oztnAddr = "";		// varchar(100) Not Null Comment '단체주소'
	private String blog = "";			// varchar(10) NOT NULL COMMENT '블로그'
	private String oztnImgPath = "";	// varchar(200) Not Null Comment '대표이미지'
	private String oztnImgInfo = "";	// varchar(100) Not Null Comment '대표이미지 설명'
	private String blogCovImgPath = "";	// varchar(200) not null comment '블로그커버 이미지'
	private String blogCovImgInfo = "";	// varchar(100) not null comment '블로그커버 이미지 설명'
	private String blogMblCovImgPath;	// varchar(200) not null comment '블로그커버 이미지(모바일)',
	private String blogMblCovImgInfo;	// varchar(100) not null comment '블로그커버 이미지 설명(모바일)',
	private String oztnItdc = "";		// varchar(1000) Not Null Comment '업체소개'
	private String locTypCd = "";		// varchar(50) Not Null Comment '위치정보종류 공통코드(016)'
	private String locLat = "";			// varchar(15) Not Null Comment '위도'
	private String locLng = "";			// varchar(15) Not Null Comment '경도'
	private String locImgPath = "";		// varchar(200) Not Null Comment '위치이미지'
	private String locImgInfo = "";		// varchar(100) Not Null Comment '위치이미지 정보'
	private String oztnSlg = "";		// varchar(50) Not Null Comment '단체슬로건',
	private String spoYn = "";			// varchar(1) Not Null Comment '후원여부',
	private String mbrBth = "";			// varchar(10) NOT NULL COMMENT '생년월일',
	private String mbrGndCd = "";		// varchar(50) NOT NULL COMMENT '성별 공통코드(026)',
	private String topClorTypCd = "";	// varchar(50) NOT NULL DEFAULT '000' COMMENT '상단컬러 공통코드(034)',
	private String bgrdFilePath = "";	// varchar(200) NOT NULL DEFAULT '' COMMENT '블로그 소개 배경 이미지',
	private String oprSprtYn = "";		// VARCHAR(1) NOT NULL DEFAULT 'N' COMMENT '경영지원 상담업체여부',
	private String wdwYn = "";			// varchar(1) Not Null Default 'N' Comment '탈퇴여부'
	private String creDttm = "";		// datetime Not Null Default CurrentTimestamp Comment '가입일시'
	private String modDttm = "";		// datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시'
	public String getMbrIdx() {
		return mbrIdx;
	}
	public void setMbrIdx(String mbrIdx) {
		this.mbrIdx = mbrIdx;
	}
	public String getMbrId() {
		return mbrId;
	}
	public void setMbrId(String mbrId) {
		this.mbrId = mbrId;
	}
	public String getMbrPwd() {
		return mbrPwd;
	}
	public void setMbrPwd(String mbrPwd) {
		this.mbrPwd = mbrPwd;
	}
	public String getMbrTypCd() {
		return mbrTypCd;
	}
	public void setMbrTypCd(String mbrTypCd) {
		this.mbrTypCd = mbrTypCd;
	}
	public String getSclTypCd() {
		return sclTypCd;
	}
	public void setSclTypCd(String sclTypCd) {
		this.sclTypCd = sclTypCd;
	}
	public String getOztnNm() {
		return oztnNm;
	}
	public void setOztnNm(String oztnNm) {
		this.oztnNm = oztnNm;
	}
	public String getCeoNm() {
		return ceoNm;
	}
	public void setCeoNm(String ceoNm) {
		this.ceoNm = ceoNm;
	}
	public String getOfcPhn() {
		return ofcPhn;
	}
	public void setOfcPhn(String ofcPhn) {
		this.ofcPhn = ofcPhn;
	}
	public String getCeoPhn() {
		return ceoPhn;
	}
	public void setCeoPhn(String ceoPhn) {
		this.ceoPhn = ceoPhn;
	}
	public String getOztnAddr() {
		return oztnAddr;
	}
	public void setOztnAddr(String oztnAddr) {
		this.oztnAddr = oztnAddr;
	}
	public String getBlog() {
		return blog;
	}
	public void setBlog(String blog) {
		this.blog = blog;
	}
	public String getOztnImgPath() {
		return oztnImgPath;
	}
	public void setOztnImgPath(String oztnImgPath) {
		this.oztnImgPath = oztnImgPath;
	}
	public String getOztnImgInfo() {
		return oztnImgInfo;
	}
	public void setOztnImgInfo(String oztnImgInfo) {
		this.oztnImgInfo = oztnImgInfo;
	}
	public String getBlogCovImgPath() {
		return blogCovImgPath;
	}
	public void setBlogCovImgPath(String blogCovImgPath) {
		this.blogCovImgPath = blogCovImgPath;
	}
	public String getBlogCovImgInfo() {
		return blogCovImgInfo;
	}
	public void setBlogCovImgInfo(String blogCovImgInfo) {
		this.blogCovImgInfo = blogCovImgInfo;
	}
	public String getBlogMblCovImgPath() {
		return blogMblCovImgPath;
	}
	public void setBlogMblCovImgPath(String blogMblCovImgPath) {
		this.blogMblCovImgPath = blogMblCovImgPath;
	}
	public String getBlogMblCovImgInfo() {
		return blogMblCovImgInfo;
	}
	public void setBlogMblCovImgInfo(String blogMblCovImgInfo) {
		this.blogMblCovImgInfo = blogMblCovImgInfo;
	}
	public String getOztnItdc() {
		return oztnItdc;
	}
	public void setOztnItdc(String oztnItdc) {
		this.oztnItdc = oztnItdc;
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
	public String getOztnSlg() {
		return oztnSlg;
	}
	public void setOztnSlg(String oztnSlg) {
		this.oztnSlg = oztnSlg;
	}
	public String getSpoYn() {
		return spoYn;
	}
	public void setSpoYn(String spoYn) {
		this.spoYn = spoYn;
	}
	public String getMbrBth() {
		return mbrBth;
	}
	public void setMbrBth(String mbrBth) {
		this.mbrBth = mbrBth;
	}
	public String getMbrGndCd() {
		return mbrGndCd;
	}
	public void setMbrGndCd(String mbrGndCd) {
		this.mbrGndCd = mbrGndCd;
	}
	public String getTopClorTypCd() {
		return topClorTypCd;
	}
	public void setTopClorTypCd(String topClorTypCd) {
		this.topClorTypCd = topClorTypCd;
	}
	public String getBgrdFilePath() {
		return bgrdFilePath;
	}
	public void setBgrdFilePath(String bgrdFilePath) {
		this.bgrdFilePath = bgrdFilePath;
	}
	public String getOprSprtYn() {
		return oprSprtYn;
	}
	public void setOprSprtYn(String oprSprtYn) {
		this.oprSprtYn = oprSprtYn;
	}
	public String getWdwYn() {
		return wdwYn;
	}
	public void setWdwYn(String wdwYn) {
		this.wdwYn = wdwYn;
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
