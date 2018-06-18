package kr.maxerve.admin.reserve.vo;

import kr.maxerve.dto.FacilitiesReserveDTO;

public class FacilitiesReserveMVO extends FacilitiesReserveDTO {

	private String mbrId;				//회원아이디
	private String fctNm;				//시설명
	private String mbrTypCdNm;			//멤버구분
	private String ceoPhn;				//대표휴대전화
	private String useHour;				//사용시간
	private String rsvtPlcCd;			//예약장소 공통코드(002)
	private String rsvtPgrNm;			//예약진행상태명(020)
	
	public String getMbrId() {
		return mbrId;
	}
	public void setMbrId(String mbrId) {
		this.mbrId = mbrId;
	}
	public String getFctNm() {
		return fctNm;
	}
	public void setFctNm(String fctNm) {
		this.fctNm = fctNm;
	}
	public String getMbrTypCdNm() {
		return mbrTypCdNm;
	}
	public void setMbrTypCdNm(String mbrTypCdNm) {
		this.mbrTypCdNm = mbrTypCdNm;
	}
	public String getCeoPhn() {
		return ceoPhn;
	}
	public void setCeoPhn(String ceoPhn) {
		this.ceoPhn = ceoPhn;
	}
	public String getUseHour() {
		return useHour;
	}
	public void setUseHour(String useHour) {
		this.useHour = useHour;
	}
	public String getRsvtPlcCd() {
		return rsvtPlcCd;
	}
	public void setRsvtPlcCd(String rsvtPlcCd) {
		this.rsvtPlcCd = rsvtPlcCd;
	}
	public String getRsvtPgrNm() {
		return rsvtPgrNm;
	}
	public void setRsvtPgrNm(String rsvtPgrNm) {
		this.rsvtPgrNm = rsvtPgrNm;
	}
}
