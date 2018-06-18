package kr.maxerve.admin.activity.vo;

import org.hibernate.validator.constraints.NotEmpty;

import kr.maxerve.dto.ProposeDTO;

public class ProposeUVO extends ProposeDTO{

	@NotEmpty
	private String prpIdx = "";			// int(11) NOT NULL AUTOINCREMENT COMMENT '제안 인덱스'
	@NotEmpty
	private String useYn = "";			// VARCHAR(1) NOT NULL DEFAULT 'Y' COMMENT '사용여부'
	
	public String getPrpIdx() {
		return prpIdx;
	}
	public void setPrpIdx(String prpIdx) {
		this.prpIdx = prpIdx;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
}
