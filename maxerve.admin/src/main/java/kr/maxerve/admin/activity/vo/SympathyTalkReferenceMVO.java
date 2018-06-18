package kr.maxerve.admin.activity.vo;

import kr.maxerve.dto.SympathyTalkReferenceDTO;

public class SympathyTalkReferenceMVO extends SympathyTalkReferenceDTO{
	
	private String locTypCdNm = ""; //소재지 공통코드 명
	private String titl = "";		//제목
	private String ceoNm = "";		//등록자(대표자)
	private String oztnNm = "";		//단체명
	private String cmmtCnt = "";	//댓글수
	
	public String getLocTypCdNm() {
		return locTypCdNm;
	}
	public void setLocTypCdNm(String locTypCdNm) {
		this.locTypCdNm = locTypCdNm;
	}
	public String getTitl() {
		return titl;
	}
	public void setTitl(String titl) {
		this.titl = titl;
	}
	public String getCeoNm() {
		return ceoNm;
	}
	public void setCeoNm(String ceoNm) {
		this.ceoNm = ceoNm;
	}
	public String getOztnNm() {
		return oztnNm;
	}
	public void setOztnNm(String oztnNm) {
		this.oztnNm = oztnNm;
	}
	public String getCmmtCnt() {
		return cmmtCnt;
	}
	public void setCmmtCnt(String cmmtCnt) {
		this.cmmtCnt = cmmtCnt;
	}
}
