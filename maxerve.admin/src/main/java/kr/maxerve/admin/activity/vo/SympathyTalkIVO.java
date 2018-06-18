package kr.maxerve.admin.activity.vo;

import java.util.ArrayList;
import java.util.List;

import org.springmodules.validation.bean.conf.loader.annotation.handler.NotEmpty;

import kr.maxerve.dto.SympathyTalkDTO;
import kr.maxerve.dto.SympathyTalkReferenceDTO;

public class SympathyTalkIVO extends SympathyTalkDTO{

	@NotEmpty
	private String titl = "";			// varchar(50) NOT NULL COMMENT '제목'
	@NotEmpty
	private String body = "";			// mediumtext NOT NULL COMMENT '본문'
	@NotEmpty
	private String ord = "";			// INT(11) NOT NULL COMMENT '노출순서'
	@NotEmpty
	private String srtDt = "";			// VARCHAR(10) NOT NULL COMMENT '시작일'
	@NotEmpty
	private String endDt = "";			// varchar(10) NOT NULL COMMENT '종료일'
	@NotEmpty
	private String useYn = "";			// VARCHAR(1) NOT NULL COMMENT '사용여부'
	@NotEmpty
	private List<SympathyTalkReferenceDTO> referenceList = new ArrayList<SympathyTalkReferenceDTO>();
	private String tagNm = "";
	private String filePath = "";
	
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
	public String getOrd() {
		return ord;
	}
	public void setOrd(String ord) {
		this.ord = ord;
	}
	public String getSrtDt() {
		return srtDt;
	}
	public void setSrtDt(String srtDt) {
		this.srtDt = srtDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public List<SympathyTalkReferenceDTO> getReferenceList() {
		return referenceList;
	}
	public void setReferenceList(List<SympathyTalkReferenceDTO> referenceList) {
		this.referenceList = referenceList;
	}
	public String getTagNm() {
		return tagNm;
	}
	public void setTagNm(String tagNm) {
		this.tagNm = tagNm;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
