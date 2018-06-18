package kr.maxerve.admin.reserve.vo;

import org.springmodules.validation.bean.conf.loader.annotation.handler.NotEmpty;

import kr.maxerve.dto.FacilitiesReserveDTO;

public class FacilitiesReserveUVO extends FacilitiesReserveDTO {
	
	@NotEmpty
	private String fctRsvtIdx = "";		// int(11) Not Null AutoIncrement Comment '시설예약 인덱스'
	@NotEmpty
	private String rsvtPgr = "";		// varchar(50) Not Null Comment '예약진행상태 공통코드(020)'
	
	public String getFctRsvtIdx() {
		return fctRsvtIdx;
	}
	public void setFctRsvtIdx(String fctRsvtIdx) {
		this.fctRsvtIdx = fctRsvtIdx;
	}
	public String getRsvtPgr() {
		return rsvtPgr;
	}
	public void setRsvtPgr(String rsvtPgr) {
		this.rsvtPgr = rsvtPgr;
	}
}
