package kr.maxerve.admin.reserve.vo;

import kr.maxerve.dto.TourApplyDTO;

public class ParkTourMVO extends TourApplyDTO{

	private String tourPgrNm;		//투어 신청 상태 코드명
	private String dom;				//요일
	private String tourPrpsCdNm;	//방문목적 공통코드(018) 명
	private String oztnTypCdNm;		//단체형태 공통코드(033) 명

	public String getTourPgrNm() {
		return tourPgrNm;
	}
	public void setTourPgrNm(String tourPgrNm) {
		this.tourPgrNm = tourPgrNm;
	}
	public String getDom() {
		return dom;
	}
	public void setDom(String dom) {
		this.dom = dom;
	}
	public String getTourPrpsCdNm() {
		return tourPrpsCdNm;
	}
	public void setTourPrpsCdNm(String tourPrpsCdNm) {
		this.tourPrpsCdNm = tourPrpsCdNm;
	}
	public String getOztnTypCdNm() {
		return oztnTypCdNm;
	}
	public void setOztnTypCdNm(String oztnTypCdNm) {
		this.oztnTypCdNm = oztnTypCdNm;
	}
}
