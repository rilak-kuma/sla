package kr.maxerve.admin.information.vo;

import kr.maxerve.dto.NewsLetterSubscribeDTO;

public class SubscriberMVO extends NewsLetterSubscribeDTO{
	
	private String korSubscribCnt;		//국문 구독자 수
	private String engSubscribCnt;		//영문 구독자 수
	private String scbLocCdNm;			//신청구분명
	
	public String getKorSubscribCnt() {
		return korSubscribCnt;
	}
	public void setKorSubscribCnt(String korSubscribCnt) {
		this.korSubscribCnt = korSubscribCnt;
	}
	public String getEngSubscribCnt() {
		return engSubscribCnt;
	}
	public void setEngSubscribCnt(String engSubscribCnt) {
		this.engSubscribCnt = engSubscribCnt;
	}
	public String getScbLocCdNm() {
		return scbLocCdNm;
	}
	public void setScbLocCdNm(String scbLocCdNm) {
		this.scbLocCdNm = scbLocCdNm;
	}
}
