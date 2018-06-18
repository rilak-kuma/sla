package kr.maxerve.admin.information.vo;

import org.springmodules.validation.bean.conf.loader.annotation.handler.NotEmpty;

import kr.maxerve.dto.RecruitRoomDTO;

public class RecruitUVO extends RecruitRoomDTO{
	
	@NotEmpty
	private String rcrtRoomIdx = "";		// int(11) NOT NULL AUTOINCREMENT COMMENT '구인 인덱스'
	@NotEmpty
	private String useYn = "";			// VARCHAR(1) NOT NULL DEFAULT 'Y' COMMENT '사용여부'
	
	public String getRcrtRoomIdx() {
		return rcrtRoomIdx;
	}
	public void setRcrtRoomIdx(String rcrtRoomIdx) {
		this.rcrtRoomIdx = rcrtRoomIdx;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
}
