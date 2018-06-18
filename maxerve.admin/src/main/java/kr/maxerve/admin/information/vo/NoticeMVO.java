package kr.maxerve.admin.information.vo;

import kr.maxerve.dto.NoticeDTO;

public class NoticeMVO extends NoticeDTO{

	private String mngrMbrNm;		//등록자이름(관리자)
	private String mngrMbrDpt;		//등록자 부서
	
	public String getMngrMbrNm() {
		return mngrMbrNm;
	}
	public void setMngrMbrNm(String mngrMbrNm) {
		this.mngrMbrNm = mngrMbrNm;
	}
	public String getMngrMbrDpt() {
		return mngrMbrDpt;
	}
	public void setMngrMbrDpt(String mngrMbrDpt) {
		this.mngrMbrDpt = mngrMbrDpt;
	}
}
