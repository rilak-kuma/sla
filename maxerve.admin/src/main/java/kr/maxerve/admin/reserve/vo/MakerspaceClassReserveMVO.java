package kr.maxerve.admin.reserve.vo;

import kr.maxerve.dto.MakerspaceClassDTO;

/**
* MakerspaceClassReserveMVO
* @author LEEC.J
* @since 2018.06.22
* @version 1.0
* @see
*
* <pre>
* 메이커스페이스 클래스 예약 현황
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.22     LEEC.J        최초 생성
* </pre>
*/
public class MakerspaceClassReserveMVO extends MakerspaceClassDTO {
	private String mkspClsRsvCnt;	// 메이커스페이스 예약인원수

	public String getMkspClsRsvCnt() {
		return mkspClsRsvCnt;
	}
	public void setMkspClsRsvCnt(String mkspClsRsvCnt) {
		this.mkspClsRsvCnt = mkspClsRsvCnt;
	}
}
