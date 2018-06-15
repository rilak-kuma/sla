package kr.maxerve.dto;

public class ProposeDTO {
	private String prpIdx = "";			// int(11) NOT NULL AUTOINCREMENT COMMENT '제안 인덱스'
	private String mbrIdx = "";			// int(11) NOT NULL COMMENT '회원 인덱스'
	private String mngrMbrIdx = "";		// INT(11) NOT NULL DEFAULT '0' COMMENT '수정자',
	private String titl = "";			// varchar(50) NOT NULL COMMENT '제목'
	private String body = "";			// mediumtext NOT NULL COMMENT '본문'
	private String srtDt = "";			// VARCHAR(10) NOT NULL COMMENT '기간시작',
	private String endDt = "";			// VARCHAR(10) NOT NULL COMMENT '기간종료',
	private String filePath = "";		// varchar(200) NOT NULL COMMENT '섬네일 이미지 패스'
	private String useYn = "";			// VARCHAR(1) NOT NULL DEFAULT 'Y' COMMENT '사용여부',
	private String hitCnt = "";			// INT(11) NOT NULL DEFAULT '0' COMMENT '조회수',
	private String delYn = "";			// varchar(1) NOT NULL DEFAULT 'N' COMMENT '삭제여부'
	private String creDttm = "";			// datetime NOT NULL DEFAULT CURRENTTIMESTAMP COMMENT '생성일시'
	private String modDttm = "";			// datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시'

	public String getPrpIdx() {
		return prpIdx;
	}
	public void setPrpIdx(String prpIdx) {
		this.prpIdx = prpIdx;
	}
	public String getMbrIdx() {
		return mbrIdx;
	}
	public void setMbrIdx(String mbrIdx) {
		this.mbrIdx = mbrIdx;
	}
	public String getTitl() {
		return titl;
	}
	public void setTitl(String titl) {
		this.titl = titl;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
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
	public String getMngrMbrIdx() {
		return mngrMbrIdx;
	}
	public void setMngrMbrIdx(String mngrMbrIdx) {
		this.mngrMbrIdx = mngrMbrIdx;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getHitCnt() {
		return hitCnt;
	}
	public void setHitCnt(String hitCnt) {
		this.hitCnt = hitCnt;
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
}
