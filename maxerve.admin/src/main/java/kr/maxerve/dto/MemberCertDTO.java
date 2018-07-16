package kr.maxerve.dto;

/**
* MemberCertDTO
* @author LEEC.J
* 2018.06.22
* @version 1.0
* @see
*
* <pre>
* 회원인증발송
* TBL_MBR_CCE
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.22     LEEC.J        최초 생성
* </pre>
*/
public class MemberCertDTO {
	private String emil;			// varchar(200) NOT NULL COMMENT '이메일'
	private String cceCd;			// varchar(50) NOT NULL COMMENT '인증코드'
	private String creDttm;			// datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시'

	public String getEmil() {
		return emil;
	}
	public void setEmil(String emil) {
		this.emil = emil;
	}
	public String getCceCd() {
		return cceCd;
	}
	public void setCceCd(String cceCd) {
		this.cceCd = cceCd;
	}
	public String getCreDttm() {
		return creDttm;
	}
	public void setCreDttm(String creDttm) {
		this.creDttm = creDttm;
	}
}
