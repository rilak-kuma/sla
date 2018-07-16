package kr.maxerve.dto;

/**
* NoticeDTO
* @author LEEC.J
* 2018.06.22
* @version 1.0
* @see
*
* <pre>
* 공지
* TBL_NTC
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.22     LEEC.J        최초 생성
* </pre>
*/
public class NoticeDTO {
	private String ntcIdx = "";       	// INT(11) NOT NULL AUTO_INCREMENT COMMENT '공지 인덱스'
	private String mngrMbrIdx = "";   	// INT(11) NOT NULL COMMENT '등록관리자 인덱스'
	private String modMngrMbrIdx = "";	// INT(11) NOT NULL COMMENT '수정관리자 인덱스'
	private String titl = "";         	// VARCHAR(50) NOT NULL COMMENT '제목'
	private String body = "";         	// MEDIUMTEXT NOT NULL COMMENT '내용'
	private String hitCnt = "";       	// INT(11) NOT NULL DEFAULT '0' COMMENT '조회수'
	private String useYn = "";        	// VARCHAR(1) NOT NULL COMMENT '사용여부'
	private String topYn = "";        	// VARCHAR(1) NOT NULL COMMENT '상단노출여부'
	private String pcYn = "";         	// VARCHAR(1) NOT NULL COMMENT 'PC여부'
	private String mblYn = "";        	// VARCHAR(1) NOT NULL COMMENT '모바일여부'
	private String delYn = "";        	// VARCHAR(1) NOT NULL DEFAULT 'N' COMMENT '삭제여부'
	private String creDttm = "";      	// DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시'
	private String modDttm = "";      	// DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시'

	public String getNtcIdx() {
		return ntcIdx;
	}
	public void setNtcIdx(String ntcIdx) {
		this.ntcIdx = ntcIdx;
	}
	public String getMngrMbrIdx() {
		return mngrMbrIdx;
	}
	public void setMngrMbrIdx(String mngrMbrIdx) {
		this.mngrMbrIdx = mngrMbrIdx;
	}
	public String getModMngrMbrIdx() {
		return modMngrMbrIdx;
	}
	public void setModMngrMbrIdx(String modMngrMbrIdx) {
		this.modMngrMbrIdx = modMngrMbrIdx;
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
	public String getTopYn() {
		return topYn;
	}
	public void setTopYn(String topYn) {
		this.topYn = topYn;
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
}
