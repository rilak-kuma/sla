package kr.maxerve.admin.reserve.vo;

import org.springmodules.validation.bean.conf.loader.annotation.handler.NotEmpty;

import kr.maxerve.dto.TourApplyDTO;

public class ParkTourUVO extends TourApplyDTO{

	@NotEmpty
	private String tourAplyIdx = "";	// int(11) NOT NULL AUTOINCREMENT COMMENT '투어 예약 인덱스'
	@NotEmpty
	private String tourPgr = "";		// VARCHAR(50) NOT NULL COMMENT '예약진행상태 공통코드(020)'

	public String getTourAplyIdx() {
		return tourAplyIdx;
	}
	public void setTourAplyIdx(String tourAplyIdx) {
		this.tourAplyIdx = tourAplyIdx;
	}
	public String getTourPgr() {
		return tourPgr;
	}
	public void setTourPgr(String tourPgr) {
		this.tourPgr = tourPgr;
	}
}
