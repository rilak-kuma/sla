package kr.maxerve.dto;

/**
* SympathyTalkReferenceDTO
* @author LEEC.J
* 2018.06.22
* @version 1.0
* @see
*
* <pre>
* 공감토크연관컨텐츠
* TBL_FSW_TALK_REL
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.22     LEEC.J        최초 생성
* </pre>
*/
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
