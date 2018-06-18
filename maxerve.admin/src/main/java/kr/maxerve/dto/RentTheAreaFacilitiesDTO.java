package kr.maxerve.dto;

/**
* RentTheAreaFacilitiesDTO
* @author LEEC.J
* @since 2018.06.07
* @version 1.0
* @see
*
* <pre>
* 대관시설
* TBL_RTA_FCT
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.07     LEEC.J        최초 생성
* </pre>
*/
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
