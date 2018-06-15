package kr.maxerve.dto;

/**
* InterestCategoryDTO.java
* @author LEEC.J
* @since 2018.06.15
* @version 1.0
* @see
*
* <pre>
* 파일
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.15      LEEC.J         최초 생성
* </pre>
*/
public class InterestCategoryDTO {
	private String mbrIdx;		// INT(11) NOT NULL COMMENT '혁신멤버 인덱스'
	private String trgMbrIdx;	// INT(11) NOT NULL COMMENT '관심업체 인덱스'

	public String getMbrIdx() {
		return mbrIdx;
	}
	public void setMbrIdx(String mbrIdx) {
		this.mbrIdx = mbrIdx;
	}
	public String getTrgMbrIdx() {
		return trgMbrIdx;
	}
	public void setTrgMbrIdx(String trgMbrIdx) {
		this.trgMbrIdx = trgMbrIdx;
	}
}
