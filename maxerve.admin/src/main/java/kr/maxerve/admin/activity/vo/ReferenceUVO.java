package kr.maxerve.admin.activity.vo;

import org.hibernate.validator.constraints.NotEmpty;

import kr.maxerve.dto.ReferenceRoomDTO;

public class ReferenceUVO extends ReferenceRoomDTO{
	
	@NotEmpty
	private String refRoomIdx = "";		// int(11) nOT NULL AUTOINCREMENT COMMENT '자료실 인덱스'
	@NotEmpty
	private String useYn = "";			// VARCHAR(1) NOT NULL DEFAULT 'Y' COMMENT '사용여부'
	
	public String getRefRoomIdx() {
		return refRoomIdx;
	}
	public void setRefRoomIdx(String refRoomIdx) {
		this.refRoomIdx = refRoomIdx;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
}