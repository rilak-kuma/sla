package kr.maxerve.admin.reserve.vo;

import kr.maxerve.dto.MakerspaceClassGroupDTO;

/**
* MakerspaceClassReserveMVO
* @author LEEC.J
* @since 2018.06.22
* @version 1.0
* @see
*
* <pre>
* 메이커스페이스 클래스 예약 현황 상세
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.22     LEEC.J        최초 생성
* </pre>
*/
public class MakerspaceClassReserveInfoMVO extends MakerspaceClassGroupDTO {
	private String mkspClsRsvCnt;	// 메이커스페이스 예약인원수
	private String ceoNm;			// 성명(대표자명)
	private String mbrId;			// 회원아이디(이메일)
	private String ceoPhn;			// 휴대전화
	private String creDttm;			// 신청일
	
	
	public String getMkspClsRsvCnt() {
		return mkspClsRsvCnt;
	}
	public void setMkspClsRsvCnt(String mkspClsRsvCnt) {
		this.mkspClsRsvCnt = mkspClsRsvCnt;
	}
	public String getCeoNm() {
		return ceoNm;
	}
	public void setCeoNm(String ceoNm) {
		this.ceoNm = ceoNm;
	}
	public String getMbrId() {
		return mbrId;
	}
	public void setMbrId(String mbrId) {
		this.mbrId = mbrId;
	}
	public String getCeoPhn() {
		return ceoPhn;
	}
	public void setCeoPhn(String ceoPhn) {
		this.ceoPhn = ceoPhn;
	}
	public String getCreDttm() {
		return creDttm;
	}
	public void setCreDttm(String creDttm) {
		this.creDttm = creDttm;
	}
}
