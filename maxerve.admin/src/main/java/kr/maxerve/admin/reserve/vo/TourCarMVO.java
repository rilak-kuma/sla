package kr.maxerve.admin.reserve.vo;

import kr.maxerve.dto.TourCarDTO;

public class TourCarMVO extends TourCarDTO{

	private String carTypCdNm;		//차량종류 공통코드(001) 명

	public String getCarTypCdNm() {
		return carTypCdNm;
	}

	public void setCarTypCdNm(String carTypCdNm) {
		this.carTypCdNm = carTypCdNm;
	}
}
