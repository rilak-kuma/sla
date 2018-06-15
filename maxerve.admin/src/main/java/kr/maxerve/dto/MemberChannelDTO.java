package kr.maxerve.dto;

public class MemberChannelDTO {

	private int mbrIdx;				/*회원인덱스	int(11)*/
	private String cnlTypCd;		/*채널구분 공통코드(017)	varchar(50)*/
	private String cnlUrl;			/*채널주소	varchar(50)*/
	
	public int getMbrIdx() {
		return mbrIdx;
	}
	public void setMbrIdx(int mbrIdx) {
		this.mbrIdx = mbrIdx;
	}
	public String getCnlTypCd() {
		return cnlTypCd;
	}
	public void setCnlTypCd(String cnlTypCd) {
		this.cnlTypCd = cnlTypCd;
	}
	public String getCnlUrl() {
		return cnlUrl;
	}
	public void setCnlUrl(String cnlUrl) {
		this.cnlUrl = cnlUrl;
	}
}
