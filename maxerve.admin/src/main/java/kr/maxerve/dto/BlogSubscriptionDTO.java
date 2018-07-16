package kr.maxerve.dto;

/**
* BlogSubscriptionDTO
* @author LEEC.J
* @since 2018.06.13
* @version 1.0
* @see
*
* <pre>
* 블로그메뉴
* TBL_BLOG_SCB
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.13     LEEC.J        최초 생성
* </pre>
*/

public class BlogSubscriptionDTO {

	private String mbrIdx = "";			// int(11) NOT NULL COMMENT '구독자'
	private String scbMbrIdx = "";		// int(11) NOT NULL COMMENT '혁신멤버'

	public String getMbrIdx() {
		return mbrIdx;
	}
	public void setMbrIdx(String mbrIdx) {
		this.mbrIdx = mbrIdx;
	}
	public String getScbMbrIdx() {
		return scbMbrIdx;
	}
	public void setScbMbrIdx(String scbMbrIdx) {
		this.scbMbrIdx = scbMbrIdx;
	}
}
