package kr.maxerve.dto;

public class NewsLetterDTO {
	private String newsLttrIdx = "";			// INT(11) NOT NULL AUTO_INCREMENT COMMENT '뉴스레터 인덱스',
	private String newsLttrNo = "";			// VARCHAR(50) NOT NULL COMMENT '뉴스레터 호',
	private String mngrMbrIdx = "";			// INT(11) NOT NULL COMMENT '등록자',
	private String modMbrIdx = "";			// INT(11) NOT NULL DEFAULT '0' COMMENT '수정자',
	private String titl = "";				// VARCHAR(50) NOT NULL COMMENT '제목',
	private String subTitl1 = "";			// VARCHAR(50) NOT NULL COMMENT '소제목1',
	private String subTitl2 = "";			// VARCHAR(50) NOT NULL COMMENT '소제목2',
	private String subTitl3 = "";			// VARCHAR(50) NOT NULL COMMENT '소제목3',
	private String body = "";				// MEDIUMTEXT NOT NULL COMMENT '내용',
	private String pcYn = "";				// VARCHAR(1) NOT NULL COMMENT 'PC여부',
	private String mblYn = "";				// VARCHAR(1) NOT NULL COMMENT '모바일여부',
	private String hitCnt = "";				// INT(11) NOT NULL DEFAULT '0' COMMENT '조회수',
	private String useYn = "";				// VARCHAR(1) NOT NULL COMMENT '사용여부',
	private String delYn = "";				// VARCHAR(1) NOT NULL DEFAULT 'N' COMMENT '삭제여부',
	private String creDttm = "";				// DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시',
	private String modDttm = "";				// DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',

	public String getNewsLttrIdx() {
		return newsLttrIdx;
	}
	public void setNewsLttrIdx(String newsLttrIdx) {
		this.newsLttrIdx = newsLttrIdx;
	}
	public String getNewsLttrNo() {
		return newsLttrNo;
	}
	public void setNewsLttrNo(String newsLttrNo) {
		this.newsLttrNo = newsLttrNo;
	}
	public String getMngrMbrIdx() {
		return mngrMbrIdx;
	}
	public void setMngrMbrIdx(String mngrMbrIdx) {
		this.mngrMbrIdx = mngrMbrIdx;
	}
	public String getModMbrIdx() {
		return modMbrIdx;
	}
	public void setModMbrIdx(String modMbrIdx) {
		this.modMbrIdx = modMbrIdx;
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
	public String getPcYn() {
		return pcYn;
	}
	public void setPcYn(String pcYn) {
		this.pcYn = pcYn;
	}
	public String getMblYn() {
		return mblYn;
	}
	public void setMblYn(String mblYn) {
		this.mblYn = mblYn;
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
	public String getSubTitl1() {
		return subTitl1;
	}
	public void setSubTitl1(String subTitl1) {
		this.subTitl1 = subTitl1;
	}
	public String getSubTitl2() {
		return subTitl2;
	}
	public void setSubTitl2(String subTitl2) {
		this.subTitl2 = subTitl2;
	}
	public String getSubTitl3() {
		return subTitl3;
	}
	public void setSubTitl3(String subTitl3) {
		this.subTitl3 = subTitl3;
	}
}
