package kr.maxerve.admin.activity.vo;

import org.hibernate.validator.constraints.NotEmpty;

import kr.maxerve.dto.EventDTO;

public class EventUVO extends EventDTO{
	
	@NotEmpty
	private String evtIdx = "";			// int(11) NOT NULL AUTOINCREMENT COMMENT '행사 인덱스'
	@NotEmpty
	private String useYn = "";			// VARCHAR(1) NOT NULL DEFAULT 'Y' COMMENT '사용여부'
	
	public String getEvtIdx() {
		return evtIdx;
	}
	public void setEvtIdx(String evtIdx) {
		this.evtIdx = evtIdx;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
}
