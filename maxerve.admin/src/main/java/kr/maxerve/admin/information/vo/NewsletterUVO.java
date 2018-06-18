package kr.maxerve.admin.information.vo;

import org.springmodules.validation.bean.conf.loader.annotation.handler.NotEmpty;

import kr.maxerve.dto.NewsLetterDTO;

public class NewsletterUVO extends NewsLetterDTO{
	
	@NotEmpty
	private String newsLttrIdx = "";			// INT(11) NOT NULL AUTO_INCREMENT COMMENT '뉴스레터 인덱스'
	@NotEmpty
	private String newsLttrNo = "";			// VARCHAR(50) NOT NULL COMMENT '뉴스레터 호'
	@NotEmpty
	private String titl = "";				// VARCHAR(50) NOT NULL COMMENT '제목'
	@NotEmpty
	private String body = "";				// MEDIUMTEXT NOT NULL COMMENT '내용'
	
	public String getNewsLttrIdx() {
		return newsLttrIdx;
	}
	public void setNewsLttrIdx(String newsLttrIdx) {
		this.newsLttrIdx = newsLttrIdx;
	}
	public String getNewsLttrNo() {
		return newsLttrNo;
	}
	public void setNewsLttrNo(String newsLttrNo) {
		this.newsLttrNo = newsLttrNo;
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
}
