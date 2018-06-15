package kr.maxerve.dto;

public class OperationSupportDTO {
	private String oprSprtIdx = "";		// int(11) NOT NULL AUTOINCREMENT COMMENT '경영지원 인덱스'
	private String grpIdx = "";			// INT(11) NOT NULL COMMENT '그룹인덱스(댓글아니면 0)',
	private String mbrIdx = "";			// int(11) NOT NULL COMMENT '회원 인덱스'
	private String qstnTypCd = "";		// varchar(50) NOT NULL COMMENT '문의유형 공통코드'
	private String titl = "";			// varchar(50) NOT NULL COMMENT '제목'
	private String body = "";			// mediumtext NOT NULL COMMENT '내용'
	private String delYn = "";			// varchar(1) NOT NULL DEFAULT 'N' COMMENT '삭제여부'
	private String creDttm = "";			// datetime NOT NULL DEFAULT CURRENTTIMESTAMP COMMENT '생성일시'
	private String modDttm = "";			// datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시'

	public String getOprSprtIdx() {
		return oprSprtIdx;
	}
	public void setOprSprtIdx(String oprSprtIdx) {
		this.oprSprtIdx = oprSprtIdx;
	}
	public String getMbrIdx() {
		return mbrIdx;
	}
	public void setMbrIdx(String mbrIdx) {
		this.mbrIdx = mbrIdx;
	}
	public String getQstnTypCd() {
		return qstnTypCd;
	}
	public void setQstnTypCd(String qstnTypCd) {
		this.qstnTypCd = qstnTypCd;
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
	public String getGrpIdx() {
		return grpIdx;
	}
	public void setGrpIdx(String grpIdx) {
		this.grpIdx = grpIdx;
	}
}
