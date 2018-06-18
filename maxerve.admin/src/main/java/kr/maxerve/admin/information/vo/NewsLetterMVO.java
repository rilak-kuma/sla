package kr.maxerve.admin.information.vo;

import kr.maxerve.dto.NewsLetterDTO;

public class NewsLetterMVO extends NewsLetterDTO{
	
	private String mngrMbrNm;		//등록자

	public String getMngrMbrNm() {
		return mngrMbrNm;
	}
	public void setMngrMbrNm(String mngrMbrNm) {
		this.mngrMbrNm = mngrMbrNm;
	}
}
