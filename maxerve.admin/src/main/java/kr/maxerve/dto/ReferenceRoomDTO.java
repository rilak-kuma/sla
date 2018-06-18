package kr.maxerve.dto;

/**
* ReferenceRoomDTO
* @author LEEC.J
* 2018.06.22
* @version 1.0
* @see
*
* <pre>
* 자료실
* TBL_REF_ROOM
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.22     LEEC.J        최초 생성
* </pre>
*/
public class ReferenceRoomDTO {
	private String refRoomIdx = "";		// int(11) nOT NULL AUTOINCREMENT COMMENT '자료실 인덱스'
	private String mbrIdx = "";			// int(11) nOT NULL COMMENT '회원 인덱스'
	private String mngrMbrIdx = "";		// INT(11) NULL DEFAULT NULL COMMENT '수정자 인덱스',
	private String ctgrIdx = "";			// INT(11) NOT NULL COMMENT '카테고리 인덱스(009004)',
	private String yutbYn = "";			// varchar(1) NOT NULL COMMENT '유튜브여부'
	private String titl = "";			// varchar(50) NOT NULL COMMENT '제목'
	private String body = "";			// MEDIUMTEXT NOT NULL COMMENT '내용',
	private String hitCnt = "";			// INT(11) NOT NULL DEFAULT '0' COMMENT '조회수',
	private String useYn = "";			// VARCHAR(1) NOT NULL DEFAULT 'Y' COMMENT '사용여부',
	private String delYn = "";			// varchar(1) NOT NULL DEFAULT 'N' COMMENT '삭제여부'
	private String creDttm = "";			// datetime NOT NULL DEFAULT CURRENTTIMESTAMP COMMENT '생성일'
	private String modDttm = "";			// datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'

	public String getRefRoomIdx() {
		return refRoomIdx;
	}
	public void setRefRoomIdx(String refRoomIdx) {
		this.refRoomIdx = refRoomIdx;
	}
	public String getMbrIdx() {
		return mbrIdx;
	}
	public void setMbrIdx(String mbrIdx) {
		this.mbrIdx = mbrIdx;
	}
	public String getYutbYn() {
		return yutbYn;
	}
	public void setYutbYn(String yutbYn) {
		this.yutbYn = yutbYn;
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
	public String getCtgrIdx() {
		return ctgrIdx;
	}
	public void setCtgrIdx(String ctgrIdx) {
		this.ctgrIdx = ctgrIdx;
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
