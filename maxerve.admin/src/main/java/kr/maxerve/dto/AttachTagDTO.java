package kr.maxerve.dto;

/**
* AttachTagDTO
* @author LEEC.J
* 2018.06.22
* @version 1.0
* @see
*
* <pre>
* 등록태그
* TBL_ATC_TAG
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.22     LEEC.J        최초 생성
* </pre>
*/
public class AttachTagDTO {
	private String tagLocCd;		// varchar(50) NOT NULL COMMENT '태그소재지 공통코드(010)'
	private String tagLocIdx;		// int(11) NOT NULL COMMENT '태그소재지 인덱스'
	private String ord;				// int(11) NOT NULL COMMENT '순서'
	private String tagNm;			// varchar(50) NOT NULL COMMENT '태그명'

	public String getTagLocCd() {
		return tagLocCd;
	}
	public void setTagLocCd(String tagLocCd) {
		this.tagLocCd = tagLocCd;
	}
	public String getTagLocIdx() {
		return tagLocIdx;
	}
	public void setTagLocIdx(String tagLocIdx) {
		this.tagLocIdx = tagLocIdx;
	}
	public String getOrd() {
		return ord;
	}
	public void setOrd(String ord) {
		this.ord = ord;
	}
	public String getTagNm() {
		return tagNm;
	}
	public void setTagNm(String tagNm) {
		this.tagNm = tagNm;
	}
}
