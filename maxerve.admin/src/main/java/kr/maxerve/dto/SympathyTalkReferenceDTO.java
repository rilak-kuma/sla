package kr.maxerve.dto;

public class SympathyTalkReferenceDTO {
	private String fswTalkIdx = "";			// INT(11) NOT NULL COMMENT '공감토크인덱스',
	private String locTypCd = "";			// VARCHAR(50) NOT NULL COMMENT '소재지 공통코드(010)',
	private String locIdx = "";				// INT(11) NOT NULL COMMENT '소재지 인덱스',

	public String getFswTalkIdx() {
		return fswTalkIdx;
	}
	public void setFswTalkIdx(String fswTalkIdx) {
		this.fswTalkIdx = fswTalkIdx;
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
