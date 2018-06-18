package kr.maxerve.admin.reserve.vo;

import kr.maxerve.dto.FacilitiesMemberDTO;

public class FacilitiesMemberMVO extends FacilitiesMemberDTO{

	private String recruitmentPrd;	//모집기간
	private String memberCnt;		//회원수
	private String useYn;			//진행상태
	private String lmtMbCnt;		//모집정원
    private String mbrId = "";			// varchar(50) Not Null Comment '회원 아이디'
    private String oztnNm = "";			// varchar(50) Not Null Comment '단체명'
    private String ceoNm = "";			// varchar(50) Not Null Comment '대표자명'
    private String ceoPhn = "";			// varchar(50) Not Null Comment '대표휴대전화'
    private String mbrTypCdNm = "";		// varchar(50) Not Null Comment '혁신멤버 구분 공통코드(015) 명'
    
	public String getRecruitmentPrd() {
		return recruitmentPrd;
	}
	public void setRecruitmentPrd(String recruitmentPrd) {
		this.recruitmentPrd = recruitmentPrd;
	}
	public String getMemberCnt() {
		return memberCnt;
	}
	public void setMemberCnt(String memberCnt) {
		this.memberCnt = memberCnt;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getLmtMbCnt() {
		return lmtMbCnt;
	}
	public void setLmtMbCnt(String lmtMbCnt) {
		this.lmtMbCnt = lmtMbCnt;
	}
	public String getMbrId() {
		return mbrId;
	}
	public void setMbrId(String mbrId) {
		this.mbrId = mbrId;
	}
	public String getOztnNm() {
		return oztnNm;
	}
	public void setOztnNm(String oztnNm) {
		this.oztnNm = oztnNm;
	}
	public String getCeoNm() {
		return ceoNm;
	}
	public void setCeoNm(String ceoNm) {
		this.ceoNm = ceoNm;
	}
	public String getCeoPhn() {
		return ceoPhn;
	}
	public void setCeoPhn(String ceoPhn) {
		this.ceoPhn = ceoPhn;
	}
	public String getMbrTypCdNm() {
		return mbrTypCdNm;
	}
	public void setMbrTypCdNm(String mbrTypCdNm) {
		this.mbrTypCdNm = mbrTypCdNm;
	}
}
