package kr.maxerve.admin.account.vo;

import org.hibernate.validator.constraints.NotEmpty;

import kr.maxerve.dto.ManagerMemberDTO;

/**
* ManagerMemberUVO.java
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
public class ManagerMemberUVO extends ManagerMemberDTO {
	@NotEmpty
	private String mngrMbrIdx = "";		// INT(11) NOT NULL AUTO_INCREMENT COMMENT '관리자계정 인덱스'
	@NotEmpty
	private String useYn = "";			// 사용여부

	public String getMngrMbrIdx() {
		return mngrMbrIdx;
	}
	public void setMngrMbrIdx(String mngrMbrIdx) {
		this.mngrMbrIdx = mngrMbrIdx;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
}
