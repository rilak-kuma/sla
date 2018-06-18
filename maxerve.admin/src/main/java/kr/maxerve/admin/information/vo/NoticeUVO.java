package kr.maxerve.admin.information.vo;

import kr.maxerve.dto.NoticeDTO;

public class NoticeUVO extends NoticeDTO{

	private String ntcIdx = "";       	// INT(11) NOT NULL AUTO_INCREMENT COMMENT '공지 인덱스'
	private String titl = "";         	// VARCHAR(50) NOT NULL COMMENT '제목'
	private String body = "";         	// MEDIUMTEXT NOT NULL COMMENT '내용'
	private String useYn = "";        	// VARCHAR(1) NOT NULL COMMENT '사용여부'
	
	public String getNtcIdx() {
		return ntcIdx;
	}
	public void setNtcIdx(String ntcIdx) {
		this.ntcIdx = ntcIdx;
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
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
}
