package kr.maxerve.dto;

/**
* QuestionDTO
* @author LEEC.J
* 2018.06.22
* @version 1.0
* @see
*
* <pre>
* 문의
* TBL_QSTN
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.22     LEEC.J        최초 생성
* </pre>
*/
public class QuestionDTO {
	private String qstnIdx = "";			// int(11) NOT NULL AUTOINCREMENT COMMENT '문의 인덱스'
	private String qstnTypCd = "";		// varchar(50) NOT NULL COMMENT '문의유형 공통코드'
	private String mbrIdx = "";			// INT(11) NOT NULL COMMENT '회원인덱스(비로그인0)',
	private String titl = "";			// varchar(50) NOT NULL COMMENT '제목'
	private String body = "";			// mediumtext NOT NULL COMMENT '내용'
	private String emil = "";			// varchar(200) NOT NULL COMMENT '이메일'
	private String delYn = "";			// varchar(1) NOT NULL DEFAULT 'N' COMMENT '삭제여부'
	private String creDttm = "";			// datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시'

	public String getQstnIdx() {
		return qstnIdx;
	}
	public void setQstnIdx(String qstnIdx) {
		this.qstnIdx = qstnIdx;
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
	public String getEmil() {
		return emil;
	}
	public void setEmil(String emil) {
		this.emil = emil;
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
	public String getMbrIdx() {
		return mbrIdx;
	}
	public void setMbrIdx(String mbrIdx) {
		this.mbrIdx = mbrIdx;
	}
}
