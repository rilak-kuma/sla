package kr.maxerve.dto;

/**
* BlogBoardDTO
* @author LEEC.J
* @since 2018.06.13
* @version 1.0
* @see
*
* <pre>
* 블로그 게시물
* TBL_BLOG_BRD
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.13     LEEC.J        최초 생성
* </pre>
*/
public class BlogBoardDTO {

	private String blogBrdIdx = "";		// int(11) Not Null Comment '블로그 게시물 인덱스',
	private String mbrIdx = "";			// int(11) Not Null Comment '작성자',
	private String blogMnuIdx = "";		// int(11) Not Null Comment '블로그 메뉴 인덱스',
	private String titl = "";			// varchar(50) NOT NULL COMMENT '제목',
	private String body = "";			// mediumtext NOT NULL COMMENT '본문',
	private String filePath = "";		// varchar(200) Not Null Comment '섬네일',
	private String openYn = "";			// varchar(1) Not Null Comment '공개여부',
	private String pubYn = "";			// varchar(1) Not Null Comment '채널 게시 여부',
	private String delYn = "";			// varchar(1) Not Null Default 'N' Comment '삭제여부',
	private String creDttm = "";		// datetime Not Null Default CurrentTimestamp Comment '등록일시',
	private String modDttm = "";		// datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',

	public String getBlogBrdIdx() {
		return blogBrdIdx;
	}
	public void setBlogBrdIdx(String blogBrdIdx) {
		this.blogBrdIdx = blogBrdIdx;
	}
	public String getMbrIdx() {
		return mbrIdx;
	}
	public void setMbrIdx(String mbrIdx) {
		this.mbrIdx = mbrIdx;
	}
	public String getBlogMnuIdx() {
		return blogMnuIdx;
	}
	public void setBlogMnuIdx(String blogMnuIdx) {
		this.blogMnuIdx = blogMnuIdx;
	}
	public String getTitl() {
		return titl;
	}
	public void setTitl(String titl) {
		this.titl = titl;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getOpenYn() {
		return openYn;
	}
	public void setOpenYn(String openYn) {
		this.openYn = openYn;
	}
	public String getPubYn() {
		return pubYn;
	}
	public void setPubYn(String pubYn) {
		this.pubYn = pubYn;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public String getCreDttm() {
		return creDttm;
	}
	public void setCreDttm(String creDttm) {
		this.creDttm = creDttm;
	}
	public String getModDttm() {
		return modDttm;
	}
	public void setModDttm(String modDttm) {
		this.modDttm = modDttm;
	}
}
