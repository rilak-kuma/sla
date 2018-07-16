package kr.maxerve.dto;

/**
* MemberCategoryDTO
* @author LEEC.J
* 2018.06.22
* @version 1.0
* @see
*
* <pre>
* 혁신멤버 카테고리
* TBL_MBR_CTGR
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.22     LEEC.J        최초 생성
* </pre>
*/
public class MemberCategoryDTO {
	private String mbrIdx;		// int(11) NOT NULL COMMENT '회원 인덱스'
	private String ctgrIdx;		// INT(11) NOT NULL COMMENT '혁신멤버 카테고리 인덱스'

	public String getMbrIdx() {
		return mbrIdx;
	}
	public void setMbrIdx(String mbrIdx) {
		this.mbrIdx = mbrIdx;
	}
	public String getCtgrIdx() {
		return ctgrIdx;
	}
	public void setCtgrIdx(String ctgrIdx) {
		this.ctgrIdx = ctgrIdx;
	}
}
