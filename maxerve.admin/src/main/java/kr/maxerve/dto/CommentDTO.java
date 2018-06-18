package kr.maxerve.dto;

/**
* CommentDTO
* @author LEEC.J
* 2018.06.22
* @version 1.0
* @see
*
* <pre>
* 댓글
* TBL_CMMT
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.22     LEEC.J        최초 생성
* </pre>
*/
public class CommentDTO {
	private String cmmtIdx		= "";	// varchar(50) NOT NULL COMMENT '댓글 인덱스'
	private String cmmtLocCd	= "";	// varchar(50) NOT NULL COMMENT '댓글소재지 공통코드(010)'
	private String yonTypCd = "";		// VARCHAR(50) NOT NULL DEFAULT '000' COMMENT '찬반종류 공통코드(029)',
	private String tblIdx		= "";	// varchar(50) NOT NULL COMMENT '테이블 인덱스'
	private String mbrIdx		= "";	// int(11) nOT NULL COMMENT '회원 인덱스'
	private String body			= "";	// varchar(500) NOT NULL COMMENT '내용'
	private String filePath		= "";	// varchar(200) NOT NULL COMMENT '첨부이미지'
	private String delYn		= "";	// varchar(1) NOT NULL DEFAULT 'N' COMMENT '삭제여부'
	private String creDttm		= "";	// datetime NOT NULL DEFAULT CURRENTTIMESTAMP COMMENT '등록일시'
	private String modDttm		= "";	// datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시'

	public String getCmmtIdx() {
		return cmmtIdx;
	}
	public void setCmmtIdx(String cmmtIdx) {
		this.cmmtIdx = cmmtIdx;
	}
	public String getCmmtLocCd() {
		return cmmtLocCd;
	}
	public void setCmmtLocCd(String cmmtLocCd) {
		this.cmmtLocCd = cmmtLocCd;
	}
	public String getTblIdx() {
		return tblIdx;
	}
	public void setTblIdx(String tblIdx) {
		this.tblIdx = tblIdx;
	}
	public String getMbrIdx() {
		return mbrIdx;
	}
	public void setMbrIdx(String mbrIdx) {
		this.mbrIdx = mbrIdx;
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
	public String getYonTypCd() {
		return yonTypCd;
	}
	public void setYonTypCd(String yonTypCd) {
		this.yonTypCd = yonTypCd;
	}
}
