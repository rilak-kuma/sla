package kr.maxerve.dto;

public class TourCarDTO {
	private String tourAplyIdx;			// int(11) NOT NULL COMMENT '투어 신청 인덱스'
	private String ord;					// int(11) NOT NULL COMMENT '순번'
	private String carTypCd;			// varchar(50) NOT NULL COMMENT '차량종류 공통코드(001)'
	private String carCnt;				// varchar(10) NOT NULL COMMENT '차량수'

	public String getTourAplyIdx() {
		return tourAplyIdx;
	}
	public void setTourAplyIdx(String tourAplyIdx) {
		this.tourAplyIdx = tourAplyIdx;
	}
	public String getOrd() {
		return ord;
	}
	public void setOrd(String ord) {
		this.ord = ord;
	}
	public String getCarTypCd() {
		return carTypCd;
	}
	public void setCarTypCd(String carTypCd) {
		this.carTypCd = carTypCd;
	}
	public String getCarCnt() {
		return carCnt;
	}
	public void setCarCnt(String carCnt) {
		this.carCnt = carCnt;
	}
}
