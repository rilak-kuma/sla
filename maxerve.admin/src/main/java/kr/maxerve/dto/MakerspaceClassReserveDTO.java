package kr.maxerve.dto;

/**
* MakerspaceClassReserveDTO
* @author LEEC.J
* @since 2018.06.02
* @version 1.0
* @see
*
* <pre>
* 메이커스페이스 클래스 예약
* TBL_MKSP_CLS_RSVT
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.22     LEEC.J        최초 생성
* </pre>
*/
public class MakerspaceClassReserveDTO {

	private String fctRsvtIdx = "";  		// int(11) NOT NULL COMMENT '예약 인덱스',
	private String mkspClsGrpIdx = "";  	// int(11) NOT NULL COMMENT '메이커스페이스 클래스 반 인덱스',

	public String getFctRsvtIdx() {
		return fctRsvtIdx;
	}
	public void setFctRsvtIdx(String fctRsvtIdx) {
		this.fctRsvtIdx = fctRsvtIdx;
	}
	public String getMkspClsGrpIdx() {
		return mkspClsGrpIdx;
	}
	public void setMkspClsGrpIdx(String mkspClsGrpIdx) {
		this.mkspClsGrpIdx = mkspClsGrpIdx;
	}
}
