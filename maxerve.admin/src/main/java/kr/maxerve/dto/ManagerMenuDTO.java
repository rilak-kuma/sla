package kr.maxerve.dto;

/**
* ManagerMenuDTO
* @author LEEC.J
* @since 2018.06.17
* @version 1.0
* @see
*
* <pre>
* 관리자 메뉴권한
* TBL_MNGR_MBR_MNU
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.17     LEEC.J        최초 생성
* </pre>
*/
public class ManagerMenuDTO {
	private String mngrMbrIdx = "";		// INT(11) NOT NULL COMMENT '관리자 인덱스'
	private String mnuCd = "";			// VARCHAR(50) NOT NULL COMMENT '관리자메뉴 공통코드(023)'
	private String wrtYn = "";			// 쓰기가능여부

	public String getMngrMbrIdx() {
		return mngrMbrIdx;
	}
	public void setMngrMbrIdx(String mngrMbrIdx) {
		this.mngrMbrIdx = mngrMbrIdx;
	}
	public String getMnuCd() {
		return mnuCd;
	}
	public void setMnuCd(String mnuCd) {
		this.mnuCd = mnuCd;
	}
	public String getWrtYn() {
		return wrtYn;
	}
	public void setWrtYn(String wrtYn) {
		this.wrtYn = wrtYn;
	}

}
