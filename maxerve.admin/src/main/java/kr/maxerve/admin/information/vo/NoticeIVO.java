package kr.maxerve.admin.information.vo;

import org.springmodules.validation.bean.conf.loader.annotation.handler.NotEmpty;

import kr.maxerve.dto.NoticeDTO;

public class NoticeIVO extends NoticeDTO{

	@NotEmpty
	private String titl = "";         	// VARCHAR(50) NOT NULL COMMENT '제목'
	@NotEmpty
	private String body = "";         	// MEDIUMTEXT NOT NULL COMMENT '내용'
	@NotEmpty
	private String useYn = "";        	// VARCHAR(1) NOT NULL COMMENT '사용여부'
	
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
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
}
