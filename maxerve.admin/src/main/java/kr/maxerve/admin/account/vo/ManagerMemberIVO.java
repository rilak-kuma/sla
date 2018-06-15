package kr.maxerve.admin.account.vo;

import org.hibernate.validator.constraints.NotEmpty;

import kr.maxerve.dto.ManagerMemberDTO;

/**
* ManagerMemberIVO.java
* @author LEEC.J
* @since 2018.06.15
* @version 1.0
* @see
*
* <pre>
* 파일
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.15      LEEC.J         최초 생성
* </pre>
*/
public class ManagerMemberIVO extends ManagerMemberDTO {
	@NotEmpty
	private String mngrMbrId = "";		// VARCHAR(50) NOT NULL COMMENT '관리자계정 아이디'
	@NotEmpty
	private String mngrMbrPwd = "";		// VARCHAR(50) NOT NULL COMMENT '관리자계정 비밀번호'
	@NotEmpty
	private String mngrMbrNm = "";		// VARCHAR(50) NOT NULL COMMENT '이름'
	@NotEmpty
	private String useYn = "";			// 사용여부

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
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
}
