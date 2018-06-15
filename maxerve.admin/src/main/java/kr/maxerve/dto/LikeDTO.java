package kr.maxerve.dto;

/**
* LikeDTO.java
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
public class LikeDTO {
	private String mbrIdx;			// int(11) NOT NULL COMMENT '회원인덱스'
	private String likeLocCd;		// varchar(50) NOT NULL COMMENT '좋아요소재지 공통코드(010)'
	private String likeLocIdx;		// int(11) NOT NULL COMMENT '좋아요소재지 인덱스'
	private String creDttm;			// datetime NOT NULL COMMENT '생성일시'

	public String getMbrIdx() {
		return mbrIdx;
	}
	public void setMbrIdx(String mbrIdx) {
		this.mbrIdx = mbrIdx;
	}
	public String getLikeLocCd() {
		return likeLocCd;
	}
	public void setLikeLocCd(String likeLocCd) {
		this.likeLocCd = likeLocCd;
	}
	public String getLikeLocIdx() {
		return likeLocIdx;
	}
	public void setLikeLocIdx(String likeLocIdx) {
		this.likeLocIdx = likeLocIdx;
	}
	public String getCreDttm() {
		return creDttm;
	}
	public void setCreDttm(String creDttm) {
		this.creDttm = creDttm;
	}


}
