package kr.maxerve.dto;

public class RentTheAreaFacilitiesDTO {
	private String rtaIdx;		// INT(11) NOT NULL COMMENT '대관 인덱스'
	private String fctMstIdx;	// INT(11) NOT NULL COMMENT '시설 인덱스'
	public String getRtaIdx() {
		return rtaIdx;
	}
	public void setRtaIdx(String rtaIdx) {
		this.rtaIdx = rtaIdx;
	}
	public String getFctMstIdx() {
		return fctMstIdx;
	}
	public void setFctMstIdx(String fctMstIdx) {
		this.fctMstIdx = fctMstIdx;
	}
}
