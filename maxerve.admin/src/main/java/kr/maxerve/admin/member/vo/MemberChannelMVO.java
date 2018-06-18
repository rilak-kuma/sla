package kr.maxerve.admin.member.vo;

import kr.maxerve.dto.MemberChannelDTO;

public class MemberChannelMVO extends MemberChannelDTO {

	private String cnlTypCdNm;		//채널구분명 공통코드(017)

	public String getCnlTypCdNm() {
		return cnlTypCdNm;
	}

	public void setCnlTypCdNm(String cnlTypCdNm) {
		this.cnlTypCdNm = cnlTypCdNm;
	}
	
}
