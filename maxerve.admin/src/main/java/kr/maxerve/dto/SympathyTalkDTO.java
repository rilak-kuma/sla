package kr.maxerve.dto;

/**
* SympathyTalkDTO
* @author LEEC.J
* 2018.06.22
* @version 1.0
* @see
*
* <pre>
* 공감토크
* TBL_FSW_TALK
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.22     LEEC.J        최초 생성
* </pre>
*/
public class SympathyTalkDTO {
	private String fswTalkIdx = "";		// int(11) NOT NULL AUTOINCREMENT COMMENT '공감토크 인덱스'
	private String mngrMbrIdx = "";		// INT(11) NOT NULL COMMENT '관리자 인덱스',
	private String titl = "";			// varchar(50) NOT NULL COMMENT '제목'
	private String body = "";			// mediumtext NOT NULL COMMENT '본문'
	private String ord = "";				// INT(11) NOT NULL COMMENT '노출순서',
	private String srtDt = "";			// VARCHAR(10) NOT NULL COMMENT '시작일',
	private String endDt = "";			// varchar(10) NOT NULL COMMENT '종료일'
	private String useYn = "";			// VARCHAR(1) NOT NULL COMMENT '사용여부',
	private String delYn = "";			// varchar(1) NOT NULL COMMENT '삭제여부'
	private String creDttm = "";			// datetime NOT NULL DEFAULT CURRENTTIMESTAMP COMMENT '생성일시'
	private String modDttm = "";			// datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시'

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
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
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
	public String getOrd() {
		return ord;
	}
	public void setOrd(String ord) {
		this.ord = ord;
	}
	public String getSrtDt() {
		return srtDt;
	}
	public void setSrtDt(String srtDt) {
		this.srtDt = srtDt;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getFswTalkIdx() {
		return fswTalkIdx;
	}
	public void setFswTalkIdx(String fswTalkIdx) {
		this.fswTalkIdx = fswTalkIdx;
	}
}
