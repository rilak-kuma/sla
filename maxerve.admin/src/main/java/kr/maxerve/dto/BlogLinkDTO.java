package kr.maxerve.dto;

/**
* BlogLinkDTO
* @author LEEC.J
* @since 2018.06.13
* @version 1.0
* @see
*
* <pre>
* 블로그링크
* TBL_BLOG_LINK
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.13     LEEC.J        최초 생성
* </pre>
*/
public class BlogLinkDTO {
	private String blogLinkIdx;		// INT(11) NOT NULL AUTO_INCREMENT COMMENT '블로그 링크 인덱스'
	private String mbrIdx;			// INT(11) NOT NULL COMMENT '혁신멤버 인덱스'
	private String titl;			// VARCHAR(50) NOT NULL COMMENT '제목'
	private String url;				// VARCHAR(200) NOT NULL COMMENT 'URL'

	public String getBlogLinkIdx() {
		return blogLinkIdx;
	}
	public void setBlogLinkIdx(String blogLinkIdx) {
		this.blogLinkIdx = blogLinkIdx;
	}
	public String getMbrIdx() {
		return mbrIdx;
	}
	public void setMbrIdx(String mbrIdx) {
		this.mbrIdx = mbrIdx;
	}
	public String getTitl() {
		return titl;
	}
	public void setTitl(String titl) {
		this.titl = titl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
