package kr.maxerve.dto;

/**
* ManagerMemberDTO
* @author LEEC.J
* @since 2018.06.11
* @version 1.0
* @see
*
* <pre>
* 관리자계정
* TBL_MNGR_MBR
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.11     LEEC.J        최초 생성
* </pre>
*/
public class ManagerMemberDTO {
	private String mngrMbrIdx = "";		// INT(11) NOT NULL AUTO_INCREMENT COMMENT '관리자계정 인덱스'
	private String mngrMbrId = "";		// VARCHAR(50) NOT NULL COMMENT '관리자계정 아이디'
	private String mngrMbrPwd = "";		// VARCHAR(50) NOT NULL COMMENT '관리자계정 비밀번호'
	private String mngrMbrNm = "";		// VARCHAR(50) NOT NULL COMMENT '이름'
	private String mngrMbrDpt = "";		// 부서
	private String emil = "";			// VARCHAR(50) NOT NULL COMMENT '이메일'
	private String phn = "";				// VARCHAR(50) NOT NULL COMMENT '연락처'
	private String useYn = "";			// 사용여부
	private String delYn = "";			// VARCHAR(1) NOT NULL DEFAULT 'N' COMMENT '삭제여부'
	private String creDttm = "";			// DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시'

	public String getMngrMbrIdx() {
		return mngrMbrIdx;
	}
	public void setMngrMbrIdx(String mngrMbrIdx) {
		this.mngrMbrIdx = mngrMbrIdx;
	}
	public String getMngrMbrId() {
		return mngrMbrId;
	}
	public void setMngrMbrId(String mngrMbrId) {
		this.mngrMbrId = mngrMbrId;
	}
	public String getMngrMbrPwd() {
		return mngrMbrPwd;
	}
	public void setMngrMbrPwd(String mngrMbrPwd) {
		this.mngrMbrPwd = mngrMbrPwd;
	}
	public String getMngrMbrNm() {
		return mngrMbrNm;
	}
	public void setMngrMbrNm(String mngrMbrNm) {
		this.mngrMbrNm = mngrMbrNm;
	}
	public String getEmil() {
		return emil;
	}
	public void setEmil(String emil) {
		this.emil = emil;
	}
	public String getPhn() {
		return phn;
	}
	public void setPhn(String phn) {
		this.phn = phn;
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
	public String getMngrMbrDpt() {
		return mngrMbrDpt;
	}
	public void setMngrMbrDpt(String mngrMbrDpt) {
		this.mngrMbrDpt = mngrMbrDpt;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
}
